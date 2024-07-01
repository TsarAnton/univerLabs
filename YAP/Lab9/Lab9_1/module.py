def evklid_algorithm(a, b):
    a = abs(a)
    b = abs(b)
    if a<b:
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
    return (b)

def evklid_algorithm_mn(*args):
    arr = list(args)
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

def f(a, b):
    r = a * b
    return(r)
def f_decorate(funk):
    def decorate(*args):
        if len(args) == 0:
            return
        if len(args) == 1:
            return args[0]
        buff = funk(args[0], args[1])
        for i in range (2, len(args)):
            buff = funk(args[i], buff)
        return(buff)
    return(decorate)