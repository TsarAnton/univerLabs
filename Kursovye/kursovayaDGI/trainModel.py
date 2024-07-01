import os
import random
import spacy
from spacy.util import minibatch, compounding 
from evaluateModel import evaluate_model

def train_model(
    training_data: list,
    test_data: list,
    iterations: int = 20) -> None:
    # Строим конвейер
    nlp = spacy.load("en_core_web_sm")
    if "textcat" not in nlp.pipe_names:
        textcat = nlp.create_pipe(
            "textcat", config={"architecture": "simple_cnn"}
        )
        nlp.add_pipe(textcat, last=True)
    else:
        textcat = nlp.get_pipe("textcat")

    textcat.add_label("pos")
    textcat.add_label("neg")

    # Обучаем только textcat
    training_excluded_pipes = [
        pipe for pipe in nlp.pipe_names if pipe != "textcat"
    ]
    with nlp.disable_pipes(training_excluded_pipes):
        optimizer = nlp.begin_training()
        # Training loop
        print("Начинаем обучение")
        print("Loss\t\tPrec.\tRec.\tF-score")          
        batch_sizes = compounding(
            4.0, 32.0, 1.001
        )
        for i in range(iterations):
            loss = {}
            random.shuffle(training_data)
            batches = minibatch(training_data, size=batch_sizes)
            for batch in batches:
                text, labels = zip(*batch)
                nlp.update(
                    text,
                    labels,
                    drop=0.2,
                    sgd=optimizer,
                    losses=loss
                )
            with textcat.model.use_params(optimizer.averages):
                evaluation_results = evaluate_model(   
                    tokenizer=nlp.tokenizer,           
                    textcat=textcat,                   
                    test_data=test_data                
                )                                      
                print(f"{loss['textcat']:9.6f}\t\
                {evaluation_results['precision']:.3f}\t\
                {evaluation_results['recall']:.3f}\t\
                {evaluation_results['f-score']:.3f}")
                
    # Сохраняем модель                                 #
    with nlp.use_params(optimizer.averages):           
        nlp.to_disk("model")                 