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
print(f_decorate(f)(1, 2, 3, 4, 5))