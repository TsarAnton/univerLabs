def funk(a):
    arr = []
    for i in range(1, int(a/2) + 1):
        if a % i == 0:
            r = 0
            prove = True
            for k in range(1, int(i / 2) + 1):
                if i % k == 0:
                    r += 1
                if r > 1:
                    prove = False
            if prove:
                arr.append(i)
    if len(arr) == 1:
        arr.append(a)
    return arr
print(funk(9690))

