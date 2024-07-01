def Gauss(matrix):
    n = len(matrix)

    for i in range(n):
        max = 0
        maxLine = 0

        for j in range(i, n):
            if(abs(matrix[j][i]) > max):
                max = abs(matrix[j][i])
                maxLine = j

        buff = matrix[i]
        matrix[i] = matrix[maxLine]
        matrix[maxLine] = buff
        a = matrix[i][i]

        k = i
        while(k <= n):
            matrix[i][k] /= a
            k += 1

        for j in range(i + 1, n):
            a = -matrix[j][i]

            k = 0
            while(k <= n):
                matrix[j][k] = matrix[i][k] * a + matrix[j][k]
                k += 1

    x = [0 for _ in range(n)]
    x[n - 1] = matrix[n - 1][n]

    i = n - 2
    while(i >= 0):
        sum = 0

        for j in range(i + 1, n):
            sum += matrix[i][j] * x[j]

        x[i] = matrix[i][n] - sum
        i -= 1

    return x

# import numpy as np
# def matrix_max_row(matrix, n):
#     max_element = matrix[n][n]
#     max_row = n
#     for i in range(n + 1, len(matrix)):
#         if abs(matrix[i][n]) > abs(max_element):
#             max_element = matrix[i][n]
#             max_row = i
#     if max_row != n:
#         matrix[n], matrix[max_row] = matrix[max_row], matrix[n]
        
# def Gauss(matrix):
#     for k in range(len(matrix)):
#         # print(np.array(matrix),'\n')
#         matrix_max_row(matrix, k)
#         # print(np.array(matrix),'\n')
#         div1=matrix[k][k]
#         for a in range(k,len(matrix[k])):
#             matrix[k][a]/=div1
#         # print(np.array(matrix),'\n')
#         for j in range(k+1,len(matrix)):
#             div=matrix[k][k]*matrix[j][k]
#             for i in range(k,len(matrix[k])):
#                 matrix[j][i]-=matrix[k][i]*div
#     x=np.zeros(len(matrix))
#     for k in range(len(matrix) - 1, -1, -1):
#         x[k] = (matrix[k][-1] - sum([matrix[k][j] * x[j] for j in range(k + 1, len(matrix)) ])) / matrix[k][k]
#     return x