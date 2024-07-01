import spacy
from googletrans import Translator

def test_model(input_data: str):
    # Загружаем сохраненную модель
    translator = Translator()

    translation = translator.translate(text=input_data, src="ru", dest="en")
    # print(translation.text)
  
    loaded_model = spacy.load("model")
    parsed_text = loaded_model(translation.text)
    
    print(parsed_text.cats["pos"])
    print(parsed_text.cats["neg"])
    pred_pos = parsed_text.cats["pos"]
    pred_neg = parsed_text.cats["neg"]
    if parsed_text.cats["pos"] > parsed_text.cats["neg"]:
        prediction = "Положительный отзыв"
        score = parsed_text.cats["pos"]
    else:
        prediction = "Негативный отзыв"
        score = parsed_text.cats["neg"]
        
    #return f"{prediction} : {score:.3f}"
    return prediction, score

# train, test = load_training_data(limit=5000)
# train_model(train, test, iterations=10)