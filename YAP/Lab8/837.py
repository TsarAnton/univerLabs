n = int(input('Введите кол-во элементов списка'))
if n > 0 and n % 2 == 0:
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
    para = []
    for_up = []
    D = {}
    for i in range(int(n / 2)):
        k = 2 * i
        para.append(arr[k])
        para.append(arr[k + 1])
        for_up.append(tuple(para))
        para = []
    D.update(for_up)
    print(f'Список: {arr}')
    print(f'Словарь: {D}')
else:
    print('Error')