
class Number:

    def __init__(self, arg):
        if type(arg) is int:
            arr = []
            buff = arg
            for i in range(int(buff ** 0.5)):
                if i != 0 and i != 1:
                    while buff % i == 0:
                        arr.append(i)
                        buff /= i
            if buff != 1:
                arr.append(int(buff))
            if len(arr) == 0:
                arr.append(arg)
            arr_new = []
            for i in range(len(arr)):
                if i == 0:
                    buff = arr[i]
                    arr_new.append(arr[i])
                    cout = 1
                else:
                    if arr[i] == buff:
                        cout += 1
                    else:
                        arr_new.append(cout)
                        cout = 1
                        buff = arr[i]
                        arr_new.append(arr[i])
            arr_new.append(cout)
            arr = []
            for_up = []
            d = {}
            for i in range(int(len(arr_new) / 2)):
                k = 2 * i
                arr.append(arr_new[k])
                arr.append(arr_new[k + 1])
                for_up.append(tuple(arr))
                arr = []
            d.update(for_up)
            self.data = d
        else:
            if type(arg) is dict:
                self.data = arg

    def int(self):
            ret = 1
            d = self.data.copy()
            for i in range(len(d)):
                arr = d.popitem()
                ret *= arr[0] ** arr[1]
            return(ret)

    def __iter__(self):
        self.i = 0
        self.d = self.data.copy()
        return self

    def __next__(self):
        if self.i < len(self.data):
            result = self.d.popitem()
            self.i += 1
            return result
        else:
            raise StopIteration

    def __add__(self, other):
        if type(other) is int:
            ret = Number(self.int() + other)
            return ret
        else:
            ret = Number(self.int() + other.int())
            return ret

    def __sub__(self, other):
        if type(other) is int:
            ret = Number(self.int() - other)
            return ret
        else:
            ret = Number(self.int() - other.int())
            return ret

    def __mul__(self, other):
        if type(other) is int:
            ret = Number(self.int() * other)
            return ret
        else:
            ret = Number(self.int() * other.int())
            return ret

    def __floordiv__(self, other):
        if type(other) is int:
            ret = Number(self.int() // other)
            return ret
        else:
            ret = Number(self.int() // other.int())
            return ret

    def __str__(self):
        d = self.data.copy()
        str = ''
        for i in range(len(self.data)):
            arr = d.popitem()
            str += f'({arr[0]} ** {arr[1]})'
            if i != len(self.data) - 1:
                str += " * "
        return str

    def __repr__(self):
        return(f'{self.data}')