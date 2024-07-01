import numpy as np
from scipy.optimize import fsolve

A = np.array([[2, 1.2, -1, 1], [1.2, 0.5, 2, -1], [-1, 2, -1.5, 0.2], [1, -1, 0.2, 1.5]])
#A = np.array([[2.2, 1, 0.5, 2], [1, 1.3, 2, 1], [0.5, 2, 0.5, 1.6], [2, 1, 1.6, 2]])
print('Начальная матрица:')
print(A)
n = 4

for i in range(n - 1, 0, -1):
    M = np.empty((n, n))
    for j in range(0, n):
        if j == i - 1:
            for k in range(0, n):
                if k == i - 1:
                    M[j][k] = 1 / A[i][i - 1]
                else:
                    M[j][k] = -A[i][k] / A[i][i - 1]
        else:
            for k in range(0, n):
                if k == j:
                    M[j][k] = 1
                else:
                    M[j][k] = 0
    MO = np.empty((n, n))
    for j in range(0, n):
        if j == i - 1:
            for k in range(0, n):
                MO[j][k] = A[i][k]
        else:
            for k in range(0, n):
                if k == j:
                    MO[j][k] = 1
                else:
                    MO[j][k] = 0
    A = (MO.dot(A)).dot(M)
    if i == n - 1:
        S = M
    else:
        S = S.dot(M)
print('Матрица в форме Фробениуса:')
print(A)

f = lambda x: x**4 - A[0][0] * x**3 - A[0][1] * x**2 - A[0][2] * x - A[0][3]
g = np.array(fsolve(f, [3, 2, 0, -3]))
#g = np.array(fsolve(f, [-1.4, 0, 1.5, 5.6]))

print('Собственные значения:')
print(g)

y = np.empty((n, n))
for i in range(0, n):
    for k in range(0, n):
        y[i][k] = g[i] ** k
#print(y)

print('Собственные векторы:')
for i in range(0, n):
    yC = np.empty(n)
    for j, k in zip(range(0, n), range(n - 1, -1, -1)):
        yC[j] = y[i][k]
    print(S.dot(yC))