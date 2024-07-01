def f1(a,b):
    A=[]
    for i in range(a,b+1):
        if type(i-1%7) is int:
            A.append(i)
    return A
print(f1(4,8))

