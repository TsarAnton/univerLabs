import numpy as np
import math
from gauss import *
from constants import *    

def f(x, y, s):
    return s

def g1(x, y, s):
    return (-2 * x * math.pow(math.e, x)) + 2 * s + y
    #return (- 4 * x) / (x * x + 1) * s + 1 / (x * x + 1) * y - 3 / math.pow(x * x + 1, 2)

def g2(x, y, s):
    return 2 * s + y
    #return (- 4 * x) / (x * x + 1) * s + 1 / (x * x + 1) * y

def y(x):
    return x * math.pow(math.e, x)
    #return 1 / (x * x + 1)


alpha1 = 1
alpha2 = 1
beta1 = 0
beta2 = 0
gama1 = 0
gama2 = math.e

x = []
for i in range(n):
    x.append(a + h * i)

Y0 = [0 for _ in range(n)] 
S0 = [0 for _ in range(n)]
Y1 = [0 for _ in range(n)]
S1 = [0 for _ in range(n)]
Y2 = [0 for _ in range(n)]
S2 = [0 for _ in range(n)]

Y0[0] = 0
S0[0] = 0
Y1[0] = 1
S1[0] = 0
Y2[0] = 0
S2[0] = 1

for i in range(n-1):
    k1 = f(x[i], Y0[i], S0[i])
    l1 = g1(x[i], Y0[i], S0[i])
    k2 = f(x[i] + h / 2, Y0[i] + (h / 2) * k1, S0[i] + (h / 2) * l1)
    l2 = g1(x[i] + h / 2, Y0[i] + (h / 2) * k1, S0[i] + (h / 2) * l1)
    k3 = f(x[i] + h / 2, Y0[i] + (h / 2) * k2, S0[i] + (h / 2) * l2)
    l3 = g1(x[i] + h / 2, Y0[i] + (h / 2) * k2, S0[i] + (h / 2) * l2)
    k4 = f(x[i] + h, Y0[i] + h * k3, S0[i] + h * l3)
    l4 = g1(x[i] + h, Y0[i] + h * k3, S0[i] + h * l3)
    Y0[i + 1] = Y0[i] + (h / 6) * (k1 + 2 * k2 + 2 * k3 + k4)
    S0[i + 1] = S0[i] + (h / 6) * (l1 + 2 * l2 + 2 * l3 + l4)

for i in range(n-1):
    k1 = f(x[i], Y1[i], S1[i])
    l1 = g2(x[i], Y1[i], S1[i])
    k2 = f(x[i] + h / 2, Y1[i] + (h / 2) * k1, S1[i] + (h / 2) * l1)
    l2 = g2(x[i] + h / 2, Y1[i] + (h / 2) * k1, S1[i] + (h / 2) * l1)
    k3 = f(x[i] + h / 2, Y1[i] + (h / 2) * k2, S1[i] + (h / 2) * l2)
    l3 = g2(x[i] + h / 2, Y1[i] + (h / 2) * k2, S1[i] + (h / 2) * l2)
    k4 = f(x[i] + h, Y1[i] + h * k3, S1[i] + h * l3)
    l4 = g2(x[i] + h, Y1[i] + h * k3, S1[i] + h * l3)
    Y1[i + 1] = Y1[i] + (h / 6) * (k1 + 2 * k2 + 2 * k3 + k4)
    S1[i + 1] = S1[i] + (h / 6) * (l1 + 2 * l2 + 2 * l3 + l4)

for i in range(n-1):
    k1 = f(x[i], Y2[i], S2[i])
    l1 = g2(x[i], Y2[i], S2[i])
    k2 = f(x[i] + h / 2, Y2[i] + (h / 2) * k1, S2[i] + (h / 2) * l1)
    l2 = g2(x[i] + h / 2, Y2[i] + (h / 2) * k1, S2[i] + (h / 2) * l1)
    k3 = f(x[i] + h / 2, Y2[i] + (h / 2) * k2, S2[i] + (h / 2) * l2)
    l3 = g2(x[i] + h / 2, Y2[i] + (h / 2) * k2, S2[i] + (h / 2) * l2)
    k4 = f(x[i] + h, Y2[i] + h * k3, S2[i] + h * l3)
    l4 = g2(x[i] + h, Y2[i] + h * k3, S2[i] + h * l3)
    Y2[i + 1] = Y2[i] + (h / 6) * (k1 + 2 * k2 + 2 * k3 + k4)
    S2[i + 1] = S2[i] + (h / 6) * (l1 + 2 * l2 + 2 * l3 + l4)

print(np.array(Y0))
print()
print(np.array(S0))
print()
print(np.array(Y1))
print()
print(np.array(S1))
print()
print(np.array(Y2))
print()
print(np.array(S2))
print()

matrix = [[alpha1, beta1, gama1],
          [alpha2 * Y1[n - 1] + beta2 * S1[n - 1],
            alpha2 * Y2[n - 1] + beta2 * S2[n - 1],
            gama2 - alpha2 * Y0[n - 1] - beta2 * S0[n - 1]]]
c = Gauss(matrix)
c1 = c[0]
c2 = c[1]
print("c1 = ",c1 , ", c2 = ",c2)
Y = []

for i in range(n-1):
    Y.append(Y0[i] + c1 * Y1[i] + c2 * Y2[i])
Y.append(math.e)

realY = []
for i in range(n):
    realY.append(y(x[i]))

print("Y: \n", np.array(Y))
print("\n\nRealY: \n", np.array(realY))