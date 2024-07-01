from PIL import Image, ImageDraw
from math import sqrt, pi, cos, sin

def len(x1, y1, x2, y2):
    return sqrt((y2 - y1) ** 2 + (x2 - x1) ** 2)

def line(x1, y1, x2, y2, alpha, deep, draw):
    x3 = (x1 + 0.5 * x2) / 1.5
    y3 = (y1 + 0.5 * y2) / 1.5
    x4 = (x1 + 2 * x2) / 3
    y4 = (y1 + 2 * y2) / 3
    x5 = x3 + cos(alpha + pi / 3) * len(x3, y3, x4, y4)
    y5 = y3 + sin(alpha + pi / 3) * len(x3, y3, x4, y4)
    draw.line((x3, y3, x5, y5))
    draw.line((x4, y4, x5, y5))
    draw.line((x3, y3, x4, y4), fill='black', width = 3)
    if deep == 1:
        return
    line(x1, y1, x3, y3, alpha, deep - 1, draw)
    line(x3, y3, x5, y5, alpha + pi / 3, deep - 1, draw)
    line(x5, y5, x4, y4, alpha - pi / 3, deep - 1, draw)
    line(x4, y4, x2, y2, alpha, deep - 1, draw)


im = Image.new('RGB', (1000, 800), (0, 0, 0))

draw = ImageDraw.Draw(im)

x1 = 150
y1 = 550
x2 = 750
y2 = 550
x3 = x1 + cos(-pi / 3) * len(x1, y1, x2, y2)
y3 = y1 + sin(-pi / 3) * len(x1, y1, x2, y2)
deep = 3

draw.line((x1, y1, x2, y2))
# draw.line((x1, y1, x3, y3))
# draw.line((x3, y3, x2, y2))

line(x1, y1, x2, y2, 0, deep, draw)
# line(x3, y3, x1, y1, 2 * pi / 3, deep, draw)
# line(x2, y2, x3, y3, -2 * pi / 3, deep, draw)

im.show()