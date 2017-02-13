from interp import parse_iter
import Tkinter, tkFileDialog

def open_file():
    filename = tkFileDialog.askopenfilename(title="Select file")
    print filename
    child = Tkinter.Toplevel()
    child.title("Parsed: " + filename)
    canvas = Tkinter.Canvas(child)
    with open(filename) as f:
        parse_iter(f, canvas)
	canvas.pack()

top = Tkinter.Tk()
openButton = Tkinter.Button(top, text="Open File", command=open_file)
openButton.grid(row=0, column=0)

Tkinter.mainloop()
