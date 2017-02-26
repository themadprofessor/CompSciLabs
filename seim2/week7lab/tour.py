from Tkinter import *

def load_cities(path="Lab Files/Cities.txt"):
    cities = {}
    with open(path) as f:
        for i, line in enumerate(f):
            split = line.strip().split()
            try:
                x, y = int(split[0]), int(split[1])
            except ValueError:
                print "Invalid number on line {}".format(i+1)
            cities[split[2]] = (x,y)
    return cities

def draw_cities(canvas, cities_dict):
    for name, coords in cities_dict.iteritems():
        x,y = coords
        canvas.create_oval(x-1, y-1, x+1, y+1, fill='black')
        canvas.create_text(x, y+10, text=name)

def find_closest(start, search, dists):
    start_x, start_y = dists[start]
    curr_dist = float("inf")
    curr_name = None

    for name in search:
        x,y = dists[name]
        dist = ((start_x - x)**2 + (start_y - y)**2)**(0.5)
        if dist < curr_dist:
            #print "    {} is closer to {} than {}".format(name, start, curr_name)
            curr_dist = dist
            curr_name = name
    print "{} is the closest unvisiteded city to {}".format(curr_name, start)
    return curr_name

def gen_tour(tour, dists):
    for i in xrange(len(tour)-1):
        city = tour[i]
        closest = find_closest(city, tour[i+1:], dists)
        index = tour.index(closest)
        tour[i+1], tour[index] = tour[index], tour[i+1]
    return tour

def draw_tour(canvas, tour, dists):
    for i in xrange(len(tour)-1):
        start_x, start_y = dists[tour[i]]
        end_x, end_y = dists[tour[i+1]]
        canvas.create_line(start_x, start_y, end_x, end_y)
    last_x, last_y = dists[tour[len(tour)-1]]
    first_x, first_y = dists[tour[0]]
    canvas.create_line(first_x, first_y, last_x, last_y)
