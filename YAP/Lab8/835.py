D = {}
n = int(input('Введите кол-во элементов словаря'))
if n > 0:
    print('Вводите пары: ключ:значение')
    para = []
    for_update = []
    for i in range(n*2):
        x = input()
        try:
            x = int(x)
        except ValueError:
            try:
                x = float(x)
            except ValueError:
                x = x
        para.append(x)
        if i % 2 != 0:
            for_update.append(tuple(para))
            para = []
    D.update(for_update)
    print(f'Словарь: {D}')
    new_arr = [0, 0]
    for i in range(n):
        b = D.popitem()
        if (type(b[0]) is int or type(b[0]) is float) and (type(b[1]) is int or type(b[1]) is float):
            new_arr[0] += b[0]
            new_arr[1] += b[1]
    print(f'Список: {new_arr}')
else:
    print('Error')