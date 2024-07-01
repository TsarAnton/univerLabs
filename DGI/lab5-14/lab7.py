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
    draw.line(((x + x1) / 2, (y + y1) / 2, (x2 + x3) / 2, (y2 + y3) / 2), fill=(255 - 10 * deep, 255 - 20 * deep, 255 - 10 * deep))
    if deep == 1:
        return
    draw.line(((x3 + x2) / 2, (y3 + y2) / 2, (x2 + x4) / 2, (y2 + y4) / 2), fill=(255 - 10 * deep, 255 - 20 * deep, 255 - 10 * deep))
    draw.line(((x3 + x2) / 2, (y3 + y2) / 2, (x3 + x4) / 2, (y3 + y4) / 2), fill=(255 - 10 * deep, 255 - 20 * deep, 255 - 10 * deep))
    tree(x4, y4, side * sin(alfa), fi - alfa + pi / 2, alfa, deep - 1, draw)
    tree(x3, y3, side * cos(alfa), fi - alfa, alfa, deep - 1, draw)


im = Image.new('RGB', (800, 500), (0, 0, 0))

draw = ImageDraw.Draw(im)

x = 450
y = 450
side = 80
deep = 13
alfa = pi / 4

tree(x - 100, y - 100, side, pi / 2, alfa, deep, draw)

im.show()