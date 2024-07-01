n = int(input('Введите кол-во элементов списка'))
if n > 0:
    arr = []
    print('Вводите элементы массива')
    for i in range(n):
        x = input()
        try:
            x = int(x)
        except ValueError:
            try:
                x = float(x)
            except ValueError:
                x = x
        arr.append(x)
    new_arr = [x for x in arr if (type(x) is int or type(x) is float) and x >= 0]
    print(f'Начальный список: {arr}')
    print(f'Новый список: {new_arr}')
else:
    print('Error')