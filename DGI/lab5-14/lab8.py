from PIL import Image, ImageDraw

def triangle(x1, y1, x2, y2, x3, y3, deep, draw):
    x4 = (x1 + x2) / 2
    y4 = (y1 + y2) / 2
    x5 = (x2 + x3) / 2
    y5 = (y2 + y3) / 2
    x6 = (x1 + x3) / 2
    y6 = (y1 + y3) / 2
    draw.polygon(((x4, y4), (x5, y5), (x6, y6)), fill = "white")
    if deep == 1:
        return
    triangle(x1, y1, x4, y4, x6, y6, deep - 1, draw)
    triangle(x2, y2, x5, y5, x4, y4, deep - 1, draw)
    triangle(x3, y3, x5, y5, x6, y6, deep - 1, draw)

im = Image.new('RGB', (1200, 1200), (0, 0, 0))

draw = ImageDraw.Draw(im)

deep = 5

draw.polygon(((100, 100), (600, 970), (1100, 100)))
triangle(100, 100, 600, 970, 1100, 100, deep, draw)

im.show()
