from PIL import Image, ImageDraw
from random import randint

def dragon(x1, y1, x2, y2, deep, draw):
    if deep > 0:
        xn = (x1 + x2) / 2 + (y2 - y1) / 2
        yn = (y1 + y2) / 2 - (x2 - x1) / 2
        dragon(x2, y2, xn, yn, deep - 1, draw)
        dragon(x1, y1, xn, yn, deep - 1, draw)
    if deep == 0:
        draw.line((x1, y1, x2, y2), fill = (255 - 10 * deep, 255 - 20 * deep, 255 - 10 * deep))

im = Image.new('RGB', (640, 480), (0, 0, 0))

draw = ImageDraw.Draw(im)

x1 = 200
y1 = 300
x2 = 500
y2 = 300
num = 9

dragon(x1, y1, x2, y2, num, draw)
    
im.show()