from PIL import Image, ImageDraw
from math import sin, cos, pi
def tree(x, y, side, fi, alfa, deep, draw):
    dx = side * sin(fi)
    dy = side * cos(fi)
    x1 = x + dx
    y1 = y - dy
    x2 = x + dx - dy
    y2 = y - dy - dx
    x3 = x - dy
    y3 = y - dx
    x4 = x3 + side * cos(alfa) * sin(fi - alfa)
    y4 = y3 - side * cos(alfa) * cos(fi - alfa)
    draw.polygon(((x, y), (x1, y1), (x2, y2), (x3, y3)))
    if deep == 1:
        return
    tree(x4, y4, side * sin(alfa), fi - alfa + pi / 2, alfa, deep - 1, draw)
    tree(x3, y3, side * cos(alfa), fi - alfa, alfa, deep - 1, draw)


im = Image.new('RGB', (800, 500), (0, 0, 0))

draw = ImageDraw.Draw(im)

x = 350
y = 350
side = 80
deep = 6
alfa = pi / 4

tree(x, y, side, pi / 2, alfa, deep, draw)

im.show()