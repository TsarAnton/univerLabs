import numpy as np
from lab1Extesions import *

x1 = imageRead(r'D:\labs\DGI\lab1\data\\0.jpg')
x2 = imageRead(r'D:\labs\DGI\lab1\data\\1.jpg')
x3 = imageRead(r'D:\labs\DGI\lab1\data\\2.jpg')
x4 = imageRead(r'D:\labs\DGI\lab1\data\\3.jpg')
x5 = imageRead(r'D:\labs\DGI\lab1\data\\4.jpg')
x6 = imageRead(r'D:\labs\DGI\lab1\data\\5.jpg')
x7 = imageRead(r'D:\labs\DGI\lab1\data\\6.jpg')
x8 = imageRead(r'D:\labs\DGI\lab1\data\\7.jpg')
x9 = imageRead(r'D:\labs\DGI\lab1\data\\8.jpg')
x10 = imageRead(r'D:\labs\DGI\lab1\data\\9.jpg')

x1_t = x1.transpose()
x2_t = x2.transpose()
x3_t = x3.transpose()
x4_t = x4.transpose()
x5_t = x5.transpose()
x6_t = x6.transpose()
x7_t = x7.transpose()
x8_t = x8.transpose()
x9_t = x9.transpose()
x10_t = x10.transpose()

weight = 0.1
#весовая матрица
W = (x1_t.dot(x1) + x2_t.dot(x2) + x3_t.dot(x3) + x4_t.dot(x4) + x5_t.dot(x5) + x6_t.dot(x6) + x7_t.dot(x7) + x8_t.dot(x8) + x9_t.dot(x9) + x10_t.dot(x10))*weight
print ('WeightMatrix\n', W,'\n')

Y = imageRead(r'D:\labs\DGI\lab1\data\\test.jpg')
#выходной вектор
Y0 = Y*W
print ('Выходной вектор\n',Y0, '\n')

Y0Norm = np.sign(Y0)
print ('Нормированный выходной вектор\n',Y0Norm,'\n')


dest1 = np.sum(np.sqrt(pow((x1-Y0Norm),2)))
dest2 = np.sum(np.sqrt(pow((x2-Y0Norm),2)))
dest3 = np.sum(np.sqrt(pow((x3-Y0Norm),2)))
dest4 = np.sum(np.sqrt(pow((x4-Y0Norm),2)))
dest5 = np.sum(np.sqrt(pow((x5-Y0Norm),2)))
dest6 = np.sum(np.sqrt(pow((x6-Y0Norm),2)))
dest7 = np.sum(np.sqrt(pow((x7-Y0Norm),2)))
dest8 = np.sum(np.sqrt(pow((x8-Y0Norm),2)))
dest9 = np.sum(np.sqrt(pow((x9-Y0Norm),2)))
dest10 = np.sum(np.sqrt(pow((x10-Y0Norm),2)))
dest = np.array([dest1, 
                 dest2,
                 dest3,
                 dest4,
                 dest5,
                 dest6,
                 dest7,
                 dest8,
                 dest9,
                 dest10])

print ('Расстояния\n', dest,'\n')
print ('Минимальное расстояние', min(dest), '\n')
if (min(dest) < 50):
    print("Это 5")
else:
    print("Это не 5")
