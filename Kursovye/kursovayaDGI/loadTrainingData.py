import os
import random
import codecs

def load_training_data(
    data_directory: str = "Datasets/movies/train",
    split: float = 0.8,
    limit: int = 0
) -> tuple:
    # Загрузка данных из файлов
    reviews = []
    for label in ["pos", "neg"]:
        labeled_directory = data_directory + "/" + label
        for review in os.listdir(labeled_directory):
            if review.endswith(".txt"):
                with open(labeled_directory + "/" + review, "r", encoding="utf_8_sig") as f:
                    text = f.read()
                    text = text.replace("<br />", "\n\n")
                    if text.strip():
                        spacy_label = {
                            "cats": {
                                "pos": "pos" == label,
                                "neg": "neg" == label}
                        }
                        reviews.append((text, spacy_label))
    random.shuffle(reviews)                    

    if limit:                                  
        reviews = reviews[:limit]              
    split = int(len(reviews) * split)          
    return reviews[:split], reviews[split:]    

load_training_data()