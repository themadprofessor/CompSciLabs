# coding: utf-8 

import Tkinter

GBP_RATES={"GBP": {
            "sym": "£", "rate": 1.0
        }, "USD": {
            "sym": "$", "rate": 1.25
        }, "EUR": {
            "sym": "€", "rate": 1.17
        }}

def display():
    try:
        money = float(textVar.get())
        fro = fromChoice.get()
        to = toChoice.get()
        message = "{}{:.2f}".format(GBP_RATES[to]["sym"],
            (money/GBP_RATES[fro]["rate"]) * GBP_RATES[to]["rate"])
        messageLabel.configure(text=message)
    except ValueError:
        messageLabel.configure(text="Invalid number!")

top = Tkinter.Tk()

fileMenuButton = Tkinter.Menubutton(top,text="File")
fileMenuButton.grid(row=0,column=0)
fileMenu = Tkinter.Menu(fileMenuButton,tearoff=0)
fileMenuButton.configure(menu=fileMenu)
fileMenu.add_command(label="Exit",command=top.destroy)

fromMenuButton = Tkinter.Menubutton(top, text="From")
fromMenuButton.grid(row=0, column=1)
fromMenu = Tkinter.Menu(fromMenuButton, tearoff=0)
fromMenuButton.configure(menu=fromMenu)
toMenuButton = Tkinter.Menubutton(top, text="To")
toMenuButton.grid(row=0, column=2)
toMenu = Tkinter.Menu(toMenuButton, tearoff=0)
toMenuButton.configure(menu=toMenu)

fromChoice = Tkinter.StringVar()
fromChoice.set("GBP")
toChoice = Tkinter.StringVar()
toChoice.set("GBP")

fromMenu.add_radiobutton(variable=fromChoice, value="GBP", label="From GBP")
fromMenu.add_radiobutton(variable=fromChoice, value="USD", label="From USD")
fromMenu.add_radiobutton(variable=fromChoice, value="EUR", label="From EUR")
toMenu.add_radiobutton(variable=toChoice, value="GBP", label="To GBP")
toMenu.add_radiobutton(variable=toChoice, value="USD", label="To USD")
toMenu.add_radiobutton(variable=toChoice, value="EUR", label="To EUR")

textVar = Tkinter.StringVar("")
textEntry = Tkinter.Entry(top,textvariable=textVar,width=12)
textEntry.grid(row=1,column=0)

messageLabel = Tkinter.Label(top,text="",width=12)
messageLabel.grid(row=2,column=0)

helloButton = Tkinter.Button(top,text="Calculate",command=display)
helloButton.grid(row=1,column=1)

quitButton = Tkinter.Button(top,text="Quit",command=top.destroy)
quitButton.grid(row=1,column=3)

Tkinter.mainloop()
