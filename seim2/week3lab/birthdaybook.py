from birthdays import *

def exit():
    import sys
    sys.exit()

def read():
    global BIRTHDAY_BOOK
    BIRTHDAY_BOOK = load_book(raw_input("File> "))

def add():
    name = raw_input("Name> ")
    month = raw_input("Month> ")
    day = int(raw_input("Day> "))
    global BIRTHDAY_BOOK
    BIRTHDAY_BOOK[name] = {"month":month, "day":day}

def showall():
    for name in BIRTHDAY_BOOK:
        print_bday_person(BIRTHDAY_BOOK, name)

def show():
    name = raw_input("Name> ")
    print_bday_person(BIRTHDAY_BOOK, name)

def showmonth():
    month = raw_input("Month> ")
    print_bday_month(BIRTHDAY_BOOK, month)

def showweek():
    month = raw_input("Month> ")
    day = int(raw_input("Day> "))
    print_bday_week(BIRTHDAY_BOOK, month, day)

def rm():
    del BIRTHDAY_BOOK[raw_input("Name> ")]

BIRTHDAY_BOOK = {}
CMDS = {"read": read,
        "exit": exit,
        "add": add,
        "showall": showall,
        "show": show,
        "showmonth": showmonth,
        "showweek": showweek,
        "set": add,
        "rm": rm}

while True:
    command = raw_input("> ")
    if command not in CMDS:
        print command, "is not a command!"
        print "Please use:", CMDS.keys()
    else:
        CMDS[command]()
