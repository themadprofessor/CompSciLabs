import Canvas
import itertools

COLOURS = ["black", "white", "red", "blue", "green", "yellow", "cyan", "magenta"]

def barchart(xOrgin, yOrigin, xSize, ySize, labels, data):
    """print "Data Size:", len(data)
    print "Bar Width:", calc_width(xSize, len(data))
    print "Largest Data Item:", get_largest(data)
    print "Scale Factor:", calc_scale_fac(ySize, get_largest(data))"""

    scale_value = calc_scale_fac(ySize, get_largest(data))

    for label, value in itertools.izip(labels, data):
        

def calc_width(size, num):
    return size/num

def calc_height(scale_fac, item):
    return scale_fac * item

def calc_scale_fac(size, largest):
    return size/largest

def get_largest(data):
    return max(data)

barchart(0, 0, 10, 12, ["One", "Two", "Three", "Four", "Five"], [1,4,2,7,3])
