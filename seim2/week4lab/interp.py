from Canvas import *
import sys

class InterpreterError(Exception):
    def __init__(self, value):
        self.value = value

def convert_coords(x, y):
    try:
        x = int(x)
        y = int(y)
        return x,y
    except ValueError as e:
        raise InterpreterError, "Given coordinate is not a pair of numbers!", e

def interpret(cmds):
    curr_pos = (0,0)
    try:
        for line in [l.strip() for l in cmds]:
            if len(line) == 0:
                continue
            curr_pos = parse(line, curr_pos)
        complete()
    except InterpreterError as e:
        print "Error raised while intepreting!\n", e.value

def parse(line, curr_pos):
    global IS_RECORDING
    split = line.split()

    if split[0] not in COMMANDS and split[0] not in DEFS:
        raise InterpreterError(split[0] + " is not a valid command!")
    elif IS_RECORDING and split[0] != "end":
        RECORD.append(line)
        return curr_pos
    else:
        if split[0] in DEFS and split[0] not in COMMANDS:
            for cmd in DEFS[split[0]]:
                curr_pos = parse(cmd, curr_pos)
            return curr_pos
        else:
            return COMMANDS[split[0]](curr_pos, *split[1:])

def pos(cur_pos, x, y):
    return convert_coords(x, y)

def move(cur_pos, x, y):
    x,y = convert_coords(x,y)
    return (cur_pos[0]+x, cur_pos[1]+y)

def line(cur_pos, x, y):
    x,y = convert_coords(x,y)
    new_x,new_y = move(cur_pos, x, y)
    create_line(cur_pos[0], cur_pos[1], new_x, new_y)
    return new_x, new_y

def loop(cur_pos, count):
    try:
        global LOOP, RECORD_LOOP, LOOP_COUNT
        RECORD = []
        RECORD_LOOP = True
        LOOP_COUNT = int(count)
        return cur_pos
    except ValueError as e:
        raise InterpreterError, "Given loop count is not an integer!", e

def end(cur_pos, *args):
    if len(args) != 0:
        raise InterpreterError, "End command takes no paramaters!"
    global IS_RECORDING
    IS_RECORDING = False
   
    if LOOP_COUNT == 0 and DEF_NAME != "":
        DEFS[DEF_NAME] = RECORD[:]
    else:
        for i in xrange(LOOP_COUNT):
            for line in LOOP:
                cur_pos = parse(line, cur_pos)
    return cur_pos

def define(cur_pos, name):
    global IS_RECORDING, RECORD, DEF_NAME
    IS_RECORDING = True
    RECORD = []
    DEF_NAME = name
    return cur_pos

LOOP_COUNT = 0
DEF_NAME = ""
IS_RECORDING = False
RECORD = []
DEFS = {}
COMMANDS = {"position": pos,
        "line": line,
        "move": move,
        "loop": loop,
        "end": end,
        "define": define}

#TODO: Check file
with open(sys.argv[1]) as f:
    interpret(f)
