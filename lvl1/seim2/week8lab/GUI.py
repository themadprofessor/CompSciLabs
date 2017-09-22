from Tkinter import *

# First a function to draw a chart of data onto the supplied canvas, can
# labels is a list of strings for the x-axis labels
# data is a list of values to be used for the bars on the chart

def chart( can, labels, data, correct):
    # Delete all before starting
    l = can.find_all()
    for i in l:
        can.delete( i )

    # Calculate the size for the barchart
    maxX = can.winfo_width()
    maxY = can.winfo_height()
    xOrig = 30
    yOrig = maxY - 30
    xSize = maxX - 2*30
    ySize = maxY - 2*30

    # Find the largest data value, to calculate scaling
    maxV = data[ 0 ]
    for i in data:
        if i > maxV:
            maxV = i
        
    yScale = ySize / maxV
    xScale = xSize / len( data )

    # Draw the axes
    can.create_line( xOrig, yOrig, xOrig+xScale*len(data),yOrig )
    can.create_line( xOrig, yOrig, xOrig,yOrig-yScale*maxV-5 )
    for i in range( len( data ) ):
        can.create_text( xOrig+xScale*i+xScale/2,yOrig+20,text=labels[ i ])

    for i in range( maxV + 1 ):
        can.create_line(xOrig-5,yOrig-yScale*i,xOrig,yOrig-yScale*i )

    can.create_text(xOrig-20,yOrig,text="0")
    can.create_text(xOrig-20,yOrig-yScale*maxV,text=str(maxV))

    # Draw the bars 
    for i in range( len(data) ):
    	if i == correct:
            can.create_rectangle(xOrig+xScale*i+2,yOrig-yScale*data[i],
                             xOrig+xScale*i+xScale-2,yOrig,fill="green")
	else:
            can.create_rectangle(xOrig+xScale*i+2,yOrig-yScale*data[i],
                             xOrig+xScale*i+xScale-2,yOrig,fill="red")

        
# This sets up a window with a canvas for your bar chart to be drawn in,
# and just now a single radio button

_GRAPH_CANS = []
_GRAPH_CURR = 0
_GRAPH_VAR = None
_TOP = None

def _go():
    global _GRAPH_CURR
    index = _GRAPH_VAR.get()
    _GRAPH_CANS[_GRAPH_CURR].pack_forget()
    _GRAPH_CANS[index].pack(fill=BOTH, expand=True)
    _GRAPH_CURR = index
    _GRAPH_CANS[index].update()
    print _GRAPH_CANS[index].winfo_height(), _GRAPH_CANS[index].winfo_width()

def application(count):
    global _GRAPH_VAR, _TOP
    # Graphical user interface
    _TOP = Tk()                     # Top level window
    _TOP.title("PRS data viewer")
    _GRAPH_VAR = IntVar()

    ### Now create a frame to hold the various parts of the application
    grapher = Frame( _TOP )
    grapher.pack(fill=BOTH, expand=True)

    ### Now create a set of radio buttons along the bottom
    ### for choosing which question to view
    ### Put them in a new frame to get a tidy layout
    buttons = Frame( _TOP )  # This is to hold the buttons
    buttons.pack(fill=BOTH, expand=True)

    

    for i in xrange(count):
        butt = Radiobutton(buttons, value=i, text=str(i+1), indicatoron=0, variable=_GRAPH_VAR, command=_go)
        butt.grid(row=0,column=i)
        graphCan = Canvas( grapher )    # Note, this is a Tkinter canvas!! 
	_GRAPH_CANS.append(graphCan)
    _GRAPH_CANS[0].pack(fill=BOTH, expand=True)
    print _GRAPH_CANS[0].winfo_height(), _GRAPH_CANS[0].winfo_width()

    return _GRAPH_CANS

def complete():
    _TOP.mainloop()
