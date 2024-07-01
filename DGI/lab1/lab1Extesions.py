import numpy as np
import cv2 as cv

def imageRead(path):
    image = cv.imread(path,cv.IMREAD_GRAYSCALE)
    _, ThesholdImage = cv.threshold(image,250,255,cv.THRESH_BINARY)
    
    ThesholdImage = np.array(ThesholdImage,dtype=np.int16)

    for i in range(len(ThesholdImage)):
        for j in range(len(ThesholdImage[i])):
            if (ThesholdImage[i][j]>127):
                ThesholdImage[i][j]=1
            else:
                ThesholdImage[i][j]=-1
    return ThesholdImage
