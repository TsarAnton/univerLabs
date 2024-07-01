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
    new_arr = []
    arr_s = []
    for i in range(n):
        for k in range(n):
            if arr[k] > arr[i]:
                arr_s.append(arr[i])
                arr_s.append(arr[k])
                new_arr.append(tuple(arr_s))
                arr_s = []
    print(f'Начальный список: {arr}')
    print(f'Новый список: {new_arr}')
else:
    print('Error')