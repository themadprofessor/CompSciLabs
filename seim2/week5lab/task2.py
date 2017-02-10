
import Tkinter

def display():
    name = textVar.get()
    messageLabel.configure(text="Hello "+name)

top = Tkinter.Tk()

top.title("Task 2")
top.configure(bg="grey")

textVar = Tkinter.StringVar("")
textEntry = Tkinter.Entry(top,textvariable=textVar,width=12)
textEntry.grid(row=0,column=0)
textEntry.configure(bg="grey")

messageLabel = Tkinter.Label(top,text="",width=12)
messageLabel.grid(row=1,column=0)
messageLabel.configure(bg="grey")

showButton = Tkinter.Button(top,text="Show",command=display)
showButton.grid(row=1,column=1)
showButton.configure(activebackground="grey")

quitButton = Tkinter.Button(top,text="Quit",command=top.destroy)
quitButton.grid(row=1,column=2)
quitButton.configure(activebackground="grey")

Tkinter.mainloop()
