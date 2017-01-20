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
    """Maybe convert to date obj, and subtract to find time diff"""

birthday_book = load_book()
print_bday_person(birthday_book, "John")
print

print_bday_month(birthday_book, "Apr")
