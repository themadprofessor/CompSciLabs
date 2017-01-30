from birthdays import *

print "Imported"
def is_int(num):
    if num[0] in ('-', '+'):
        return num[1:].isdigit()
    return num.isdigit()

print "Is int"
def exit():
    import sys
    sys.exit()

print "Exit"
def read():
    global BIRTHDAY_BOOK
    path = raw_input("File> ")

    if not os.path.exists(path):
    	print path, "does not exist!"
    elif not os.path.isfile(path):
        print path, "is not a file!"
	else:
		try:
			BIRTHDAY_BOOK = load_book(raw_input("File> "))
		except IOError:
			print "Could not open", path, "!"

def add():
    global BIRTHDAY_BOOK
    name = raw_input("Name> ")

    month = raw_input("Month> ")
    if month not in MONTHS:
        print "Invalid month! Please use: ", MONTHS.keys()
    else:
        day = raw_input("Day> ")
        if not is_int(day):
            print "Please specify a valid integer!"
        else: 
            day = int(day)
            month_num = MONTHS[month]
			if (month_num == 2 and day >= 29) or (month_num % 2 == 0 and day >= 30) or month_num >= 31:
				print "Please specify a valid day!"
			else:
				BIRTHDAY_BOOK[name] = {"month":month, "day":day}

def showall():
    for name in BIRTHDAY_BOOK:
        print_bday_person(BIRTHDAY_BOOK, name)

def show():
    if len(BIRTHDAYS_BOOK == 0):
        print "No known birthdays!"
    else:
    	name = raw_input("Name> ")
		if name not in BIRTHDAY_BOOK:
			print name, "is not a known name!"
		else:
    	    print_bday_person(BIRTHDAY_BOOK, name)

def showmonth():
    if len(BIRTHDAYS_BOOK == 0):
        print "No known birthdays!"
    else:
		month = raw_input("Month> ")
		if month not in MONTHS:
			print month, "is not a valid month! Please use:", MONTHS.keys()
		else:
			print_bday_month(BIRTHDAY_BOOK, month)

def showweek():
    if len(BIRTHDAYS_BOOK == 0):
        print "No known birthdays!"
    else:
    	month = raw_input("Month> ")
		if month not in MONTHS:
			print month, "is not a valid month! Please use:", MONTHS.keys()
		else:
            month_num = MONTHS[month]
			if (month_num == 2 and day > 29) or (month_num % 2 == 0 and day > 30) or month_num > 31:
				print "Please specify a valid day!"
			else:
    	        print_bday_week(BIRTHDAY_BOOK, month, day)

def rm():
    if len(BIRTHBOOK_BOOK == 0):
        print "No known birthdays!"
    else:
		name = raw_input("Name> ")
		if name not in BIRTHBOOK_BOOK:
			print name, "is not a known name!"
		else:
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
