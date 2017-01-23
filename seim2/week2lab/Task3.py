from datetime import *

MONTHS = {"Jan":1, "Feb":2, "Mar":3, "Apr":4, "May":5, "Jun":6, "Jul":7, "Aug":8, "Sep":9, "Oct":10, "Nov":11, "Dec":12}

def test_date(start_date, day):
    bday = date.today().replace(month=MONTHS[day["month"]], day=day["day"])
    diff = start_date - bday
    if not abs(diff.days) < 7:
    	return (abs(start_date - bday.replace(year=date.today().year+1)).days) < 7
    else:
        return True

def parse_entry(line):
    split = line.split(",")
    return (split[0], {"month":split[1], "day":int(split[2])})

def load_book(path="birthdays.txt"):
    book = {}
    with open(path) as file:
        for line in file:
            (name, entry) = parse_entry(line)
            book[name] = entry
    return book

def print_bday_person(book, name):
    print "%s's birthday is %dth of %s" % (name, book[name]["day"], book[name]["month"])

def print_bday_month(book, month):
    for name in {name: day for name, day in book.iteritems() if day["month"] == month}:
        print_bday_person(book, name)

def print_bday_week(book, month, day):
    start_date = date.today().replace(month=MONTHS[month], day=day)
    for name, d in {name: d for name, d in book.iteritems() if test_date(start_date, d)}.iteritems():
    	print_bday_person(book, name)

birthday_book = load_book()
print "\tTest print_bday_person\n",
print_bday_person(birthday_book, "John")
print "\n\tTest print_bday_month\n",

print_bday_month(birthday_book, "Apr")
print "\n\tTest print_bday_week\n",
print_bday_week(birthday_book, "Dec", 30)
