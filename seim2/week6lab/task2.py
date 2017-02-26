#
# Program to simulate an ATM
#
# The main data structure is a dictionary with account numbers
# (represented by strings) as keys, and account details as values.
#
# Details of an account are stored in a dictionary with the following
# keys and values:
#
# "pin" : the PIN, as a string
# "balance" : the account balance, as a floating point number
# "transactions" : a list of the last 6 transactions
#
# One transaction is stored as a dictionary with the following
# keys and values:
#
# "date" : the date and time, as a string
# "nature" : a string, "w" for withdrawal, "d" for deposit
# "amount" : the amount of withdrawal or deposit, as a floating point number
import pickle
import date

def getOption():
    print "Options:  w: withdrawal"
    print "          d: deposit"
    print "          b: display balance"
    print "          m: mini-statement"
    print "          c: cancel request"
    return raw_input("Choose option: ")

def withdraw(account, amount):
    try:
        amount = float(amount)
    except ValueError:
        return "Invalid Amount"
    if account["balance"] >= amount:
        account["balance"] = account["balance"] - amount
        account["transactions"] = updateHistory(account["transactions"], "w", amount)
        return "{:.2f} withdrawn".format(amount)
    else:
        return "Insufficient Funds"

def deposit(account, amount):
    try:
        amount = float(amount)
    except ValueError:
        return "Invalid Amount"
    account["balance"] = account["balance"] + amount
    account["transactions"] = updateHistory(account["transactions"], "d", amount)
    return "{:.2f} deposited".format(amount)

def balance(account):
    return "Current Balance: {:.2f}".format(account["balance"])

def statement(account):
    statement = []
    for transaction in account["transactions"]:
        line = []
	line.append(transaction["date"] + ",")
        if transaction["nature"] == "w":
	    line.append("withdrawl")
	elif transaction["nature"] == "d":
	    line.append("deposit")
	else:
	    line.append("unknown")
	line.append("{:.2f}".format(transaction["amount"]))
	statement.append(" ".join(line))
    return "\n".join(statement)

def cancel(account):
    return "Transaction Cancelled"

def updateHistory(history, nature, amount):
    now = date.getDate()
    history.append({"date": now,
                    "nature": nature,
                    "amount": amount})
    if len(history) > 6:
        return history[-6:]
    else:
        return history

# load account details
def loadAccounts(path="Lab Files/accounts.pck"):
    with open(path) as f:
        return pickle.load(f)

def dumpAccounts(accounts):
    with open("new_accounts.pck", "w") as f:
        pickle.dump(accounts, f)

ACCOUNTS = loadAccounts()
COMMANDS = {"w": withdraw,
            "d": deposit,
            "b": balance,
            "m": statement,
            "c": cancel}

shouldExit = False
while not shouldExit:
    accountNum = raw_input("Enter account number:").strip()
    if accountNum == "0":
        shouldExit = True
        continue
    if accountNum in ACCOUNTS:
        account = ACCOUNTS[accountNum]
        pin = raw_input("Enter PIN:").strip()
        if pin == account["pin"]:
            option = getOption().split()
            if option[0] in COMMANDS:
	    	try:
                    print COMMANDS[option[0]](account, *option[1:])
		except TypeError:
		    print "Invalid Arguments"
            else:
                print "Invalid command"
        else:
            print "Incorrect PIN"
    else:
        print "Invalid Account Number"

dumpAccounts(ACCOUNTS)
