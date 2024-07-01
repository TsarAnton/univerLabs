from tkinter import *
from tkinter import messagebox
from tkinter.scrolledtext import ScrolledText
from tkinter import ttk
from testModel import test_model

def get_answer():
    text = answer_tf.get("1.0", END)
    prediction, score = test_model(input_data=text)
    messagebox.showinfo("Отзыв", f"Отзыв: {prediction} \nУверенность: {round(score, 3) * 100}%")
 
window = Tk()
window.title("Оценка отзыва")
window.geometry('400x300')

ttk.Style().theme_use("classic")

frame = Frame(
    window,
    padx = 10,
    pady = 10
)
frame.pack(expand=True)

answer_lb = Label(
    frame,
    text="Введите текст отзыва:"
)
answer_lb.grid(row=1, column=1)

answer_tf = ScrolledText(
    frame,
    width=40,
    height=10
)
answer_tf.grid(row=2, column=1)

answer_btn = Button(
    frame,
    text='Подтвердить',
    command=get_answer
)
answer_btn.grid(row=4, column=1)

window.mainloop()
