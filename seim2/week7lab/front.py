import tour
import Tkinter as tk

top = tk.Tk()
canvas = tk.Canvas(top)
canvas.pack(fill=tk.BOTH, expand=1)

cities = tour.load_cities()
path = cities.keys()

path.insert(0, path.pop(path.index("Stockholm")))
print path

tour.draw_cities(canvas, cities)
path = tour.gen_tour(path, cities)
tour.draw_tour(canvas, path, cities)

tk.mainloop()
