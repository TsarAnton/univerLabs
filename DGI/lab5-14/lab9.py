from PIL import Image, ImageDraw
from random import randint

im = Image.new('RGB', (1200, 1200), (0, 0, 0))

draw = ImageDraw.Draw(im)

num = 10000

x = randint(0, 1200)
y = randint(0, 1200)
draw.ellipse((x, y, x + 1, y + 1))

for i in range(num):
    n = randint(1, 3)
    xA = 100
    yA = 100
    if n == 2:
        xA = 600
        yA = 970
    if n == 3:
        xA = 1100
    x = (x + xA) / 2
    y = (y + yA) / 2

    draw.ellipse((x, y, x + 1, y + 1), fill = (255, 255, 255))
    
im.show()