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

textVar = Tkinter.StringVar("")
textEntry = Tkinter.Entry(top,textvariable=textVar,width=12)
textEntry.grid(row=0,column=0)

messageLabel = Tkinter.Label(top,text="",width=12)
messageLabel.grid(row=1,column=0)

fromChoice = Tkinter.StringVar()
fromChoice.set("GBP")
gbpRadio = Tkinter.Radiobutton(top, variable=fromChoice, value="GBP", text="From GBP")
gbpRadio.grid(row=1, column=4)

usdRadio = Tkinter.Radiobutton(top, variable=fromChoice, value="USD", text="From USD")
usdRadio.grid(row=2, column=4)

eurRadio = Tkinter.Radiobutton(top, variable=fromChoice, value="EUR", text="From EUR")
eurRadio.grid(row=3, column=4)

toChoice = Tkinter.StringVar()
toChoice.set("GBP")
gbpRadio = Tkinter.Radiobutton(top, variable=toChoice, value="GBP", text="To GBP")
gbpRadio.grid(row=1, column=5)

usdRadio = Tkinter.Radiobutton(top, variable=toChoice, value="USD", text="To USD")
usdRadio.grid(row=2, column=5)

eurRadio = Tkinter.Radiobutton(top, variable=toChoice, value="EUR", text="To EUR")
eurRadio.grid(row=3, column=5)

helloButton = Tkinter.Button(top,text="Calculate",command=display)
helloButton.grid(row=1,column=1)

quitButton = Tkinter.Button(top,text="Quit",command=top.destroy)
quitButton.grid(row=1,column=3)

Tkinter.mainloop()
