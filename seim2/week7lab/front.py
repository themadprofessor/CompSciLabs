import tour
import Tkinter as tk
import tkFileDialog

top = tk.Tk()

file_path = tkFileDialog.askopenfilename()
cities = tour.load_cities(file_path)

canvas = tk.Canvas(top)
canvas.pack(fill=tk.BOTH, expand=1)

tour.draw_cities(canvas, cities)

shortest_path = None
shortest_dist = float("inf")

for start in cities.keys():
    path = cities.keys()
    path.insert(0, path.pop(path.index(start)))
    path = tour.gen_tour(path, cities)
    length = tour.calc_len(path, cities)
    if length < shortest_dist:
        shortest_path = path[:]
	shortest_dist = length

tour.draw_tour(canvas, shortest_path, cities)

lowest_y = sorted(map(lambda coord: coord[1], cities.values()), reverse=True)[0]
largest_x = sorted(map(lambda coord: coord[0], cities.values()), reverse=True)[0]
text="Total distance starting at {}: {:.2f}".format(shortest_path[0] ,shortest_dist)
canvas.create_text(largest_x/2, lowest_y + 20, text=text)

tk.mainloop()
