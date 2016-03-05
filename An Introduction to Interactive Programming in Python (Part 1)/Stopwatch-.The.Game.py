# template for "Stopwatch: The Game"
import simplegui

# define global variables
interval = 100
counter = 0
n_stops = 0
n_stop_int = 0

# define helper function format that converts time
# in tenths of seconds into formatted string A:BC.D
def format(t):
    min = t / 600
    sec = (t - (min*600)) / 10
    dec = t - (min*600+sec*10)
    return str(min) + ':' + str("%02d" % sec) + '.' + str(dec)
    
# define event handlers for buttons; "Start", "Stop", "Reset"
def Start():
    timer.start()

def Stop():
    global n_stops, n_stop_int
    if timer.is_running():
        timer.stop()
        n_stops = n_stops + 1
        if counter % 10 == 0:
            n_stop_int = n_stop_int + 1

def Reset():
    global counter, n_stops, n_stop_int
    timer.stop()
    counter = 0
    n_stops = 0
    n_stop_int = 0

# define event handler for timer with 0.1 sec interval
def tick():
    global counter
    counter = counter + 1
    # wrap around
    if counter >= 6000:
        counter = 0
    print counter

# define draw handler
def draw(canvas):
    canvas.draw_text(format(counter), [100,100], 40, "White")
    canvas.draw_text(str(n_stop_int) + '/' + str(n_stops), [250,25], 30, "Red")
    
# create frame
frame = simplegui.create_frame("Stopwatch: The game", 300, 300)

# register event handlers
frame.add_button('Start', Start, 150)
frame.add_button('Stop', Stop, 150)
frame.add_button('Reset', Reset, 150)
frame.set_draw_handler(draw)
timer = simplegui.create_timer(interval, tick)

# start frame
frame.start()


# Please remember to review the grading rubric
