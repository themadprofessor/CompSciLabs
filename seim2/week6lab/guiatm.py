import atm
import Tkinter as tk
import tkMessageBox as alert

def go():
    global ACCOUNTS
    accountNo = accountVar.get()
    if accountNo not in ACCOUNTS:
        alert.showwarning("Warning", "The given account number is not known!")
    else:
        account = ACCOUNTS[accountNo]
	pin = pinVar.get()
	if not pin == account["pin"]:
	    alert.showwarning("Warning", "Incorrect PIN!")
	else:
            info = OPTIONS[optionVar.get()](account)
	    alert.showinfo("Result", info)
    pinVar.set("")

def withdraw(account):
    return atm.withdraw(account, amountVar.get())

def deposit(account):
    return atm.deposit(account, ammoutVar,get())
	    
ACCOUNTS = atm.loadAccounts()
OPTIONS = {"w": withdraw,
           "d": deposit,
	   "b": atm.balance,
	   "m": atm.statement}

top = tk.Tk()

optionGroup = tk.LabelFrame(top, text="Option")
optionVar = tk.StringVar()
optionVar.set("b")
withdrawRadio = tk.Radiobutton(optionGroup, text="Withdraw", value="w", variable=optionVar, anchor=tk.W)
depositRadio = tk.Radiobutton(optionGroup, text="Deposit", value="d", variable=optionVar, anchor=tk.W)
balenceRadio = tk.Radiobutton(optionGroup, text="Balence", value="b", variable=optionVar, anchor=tk.W)
statementRadio = tk.Radiobutton(optionGroup, text="Mini-Statement", value="m", variable=optionVar, anchor=tk.W)
withdrawRadio.grid(row=0)
depositRadio.grid(row=1)
balenceRadio.grid(row=2)
statementRadio.grid(row=3)

amountVar = tk.DoubleVar()
amountEntry = tk.Entry(top, textvariable=amountVar)
amountLabel = tk.Label(top, text="Amount")

accountVar = tk.StringVar()
pinVar = tk.StringVar()
accountEntry = tk.Entry(top, textvariable=accountVar)
accountLabel = tk.Label(top, text="Account No")
pinEntry = tk.Entry(top, textvariable=pinVar, show="*")
pinLabel = tk.Label(top, text="PIN")

goButton = tk.Button(top, text="Go", command=go)

accountLabel.grid(row=0, column=0)
accountEntry.grid(row=0, column=1)
pinLabel.grid(row=1, column=0)
pinEntry.grid(row=1, column=1)
amountLabel.grid(row=2, column=0)
amountEntry.grid(row=2, column=1)
goButton.grid(row=3, column=0)
optionGroup.grid(row=0, column=2, rowspan=4)

tk.mainloop()
