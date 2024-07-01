def evklid_algorithm(*args):
    arr = list(args)
    if len(args) == 0:
        return
    if len(args) == 1:
        return args[0]
    for i in range(len(arr) - 1):
        a = abs(arr[i])
        b = abs(arr[i + 1])
        if a < b:
            buff = a
            a = b
            b = buff
        if b == 0:
            b = a
            a = 0
        ost = a % b
        while ost != 0:
            a = b
            b = ost
            ost = a % b
        arr[i + 1] = b
    return(b)
print(f'NOD is {evklid_algorithm(105, 120, 60, 45)}')