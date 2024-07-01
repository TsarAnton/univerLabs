arr = []
n = int(input('Введите кол-во элементов списка'))
if n>0:
    print('Вводите элементы списка')
    for i in range(n):
        x = int(input())
        arr.append(x)
    new_arr = []
    new_arr_small = []
    m = int(input('Введите число m'))
    print(f'Начальный список: {arr}')
    for i in range(n):
        if arr[i] != 'a':
            new_arr_small.append(arr[i])
            for k in range(i + 1, n):
                if arr[k] != 'a' and arr[k] % m == arr[i] % m:
                    new_arr_small.append(arr[k])
                    arr[k] = 'a'
            new_arr.append(new_arr_small)
            new_arr_small = []
    print(f'Новый список: {new_arr}')
else:
    print('Error')
