from Canvas import *
import itertools

COLOURS = ("black", "white", "red", "blue", "green", "yellow", "cyan", "magenta")

def barchart(xOrigin, yOrigin, xSize, ySize, labels, data):
    """print "Data Size:", len(data)
    print "Bar Width:", calc_width(xSize, len(data))
    print "Largest Data Item:", get_largest(data)
    print "Scale Factor:", calc_scale_fac(ySize, get_largest(data))"""

    scale_value = calc_scale_fac(ySize, get_largest(data))
    width = calc_width(xSize, len(data))
    create_line(xOrigin, convert_coord(yOrigin, ySize), xSize, convert_coord(yOrigin, ySize))
    create_line(xOrigin, convert_coord(yOrigin, ySize), xOrigin, convert_coord(ySize, ySize))

    y_inc_offset = (ySize - yOrigin)/get_largest(data)
    for i in range(0, get_largest(data) + 1):
        y = convert_coord((i * y_inc_offset) + 20, ySize)
        create_line(15, y, 20, y)
        create_text(10, y, text = str(i))

    for i, (label, value) in enumerate(itertools.izip(labels, data)):
        print yOrigin + calc_height(scale_value, value)

        create_text(((xOrigin + width * i) + (xOrigin + width * (i+1)))/2,
                    ySize - 10,
                    text = label)
        
        create_rectangle(xOrigin + width * i,
                         convert_coord(yOrigin, ySize),
                         xOrigin + width * (i+1),
                         convert_coord(yOrigin + calc_height(scale_value, value), ySize),
                         fill = COLOURS[i % 8])

    complete()
        

def calc_width(size, num):
    return size/num

def calc_height(scale_fac, item):
    return scale_fac * item

def calc_scale_fac(size, largest):
    return size/largest

def get_largest(data):
    return max(data)

def convert_coord(y, ySize):
    return ySize - y

barchart(20, 20, 300, 250, ["One", "Two", "Three", "Four", "Five"], [1,4,2,6,3])
