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
    arr_new = []
    for i in range(n):
        if i % 2 == 0:
            arr_new.append(arr[i])
    print(f'Начальный список: {arr}')
    print(f'Новый список: {arr_new}')
else:
    print('Error')