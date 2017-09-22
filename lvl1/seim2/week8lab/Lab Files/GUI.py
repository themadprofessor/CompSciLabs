from Tkinter import *

# First a function to draw a chart of data onto the supplied canvas, can
# labels is a list of strings for the x-axis labels
# data is a list of values to be used for the bars on the chart

def chart( can, labels, data ):
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
        can.create_rectangle(xOrig+xScale*i+2,yOrig-yScale*data[i],
                             xOrig+xScale*i+xScale-2,yOrig,fill="green")

        
# This sets up a window with a canvas for your bar chart to be drawn in,
# and just now a single radio button

def application():
    # Graphical user interface
    root = Tk()                     # Top level window
    root.title("PRS data viewer")

    ### Now create a frame to hold the various parts of the application
    grapher = Frame( root )
    grapher.grid()

    # Put a canvas into it to draw on
    graphCan = Canvas( grapher )    # Note, this is a Tkinter canvas!! 
    graphCan.grid()

    ### Now create a set of radio buttons along the bottom
    ### for choosing which question to view
    ### Put them in a new frame to get a tidy layout
    buttons = Frame( root )  # This is to hold the buttons
    buttons.grid()           # Display it under the canvas
    iv = IntVar()            # To hold the value representing which radio button is pressed

    def rbAction():
        q = iv.get()
        graphCan.create_text( 100,100, text=str( q ) )

    # Make one button
    r = Radiobutton( buttons, value=0, variable=iv, indicatoron=0, text=' '+str( 1 )+' ', command=rbAction )
    r.grid()

    root.mainloop()
