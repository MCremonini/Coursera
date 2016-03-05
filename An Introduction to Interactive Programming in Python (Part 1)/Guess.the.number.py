# template for "Guess the number" mini-project
# input will come from buttons and an input field
# all output for the game will be printed in the console
import simplegui
import math
import random

secret_number   = 0
max_range       = 100
number_of_guess = 0
max_guess       = 0


# helper function to start and restart the game
def new_game():
    # initialize global variables used in your code here
    global secret_number, number_of_guess, max_guess
    secret_number = random.randrange(0, max_range)
    number_of_guess = 0
    max_guess = int(math.ceil(math.log(max_range,2)))
    print 'New game. Range is [0,' + str(max_range) + ')'
    print 'Number of remaining guesses is', max_guess - number_of_guess, '\n'


# define event handlers for control panel
def range100():
    # button that changes the range to [0,100) and starts a new game 
    global max_range
    max_range = 100
    new_game()
    

def range1000():
    # button that changes the range to [0,1000) and starts a new game     
    global max_range
    max_range = 1000
    new_game()  
    
    
def input_guess(guess):
    # main game logic goes here	
    global number_of_guess
        
    try:
        number = int(guess)  
    except:
        print 'Error: Invalid input. You entered \'' + guess + '\'.\nPlease enter an integer in the range [0,' + str(max_range) + ')'
        print
        return
    
    if number >= 0 and number < max_range:
        print 'Guess was', number
        number_of_guess += 1  
        print 'Number of remaining guesses is', max_guess - number_of_guess
    
        if(number_of_guess < max_guess):
            if(number > secret_number):
                print 'Lower!\n'
            elif(number < secret_number):
                print 'Higher!\n'
            else:
                print 'Correct!\n'
                new_game()
        elif(number_of_guess == max_guess):
            # last try
            if(number == secret_number):
                print 'Correct!\n'
            else:
                print 'You ran out of guesses. The number was', secret_number, '\n'
        
            new_game()
    else:
        print 'Error: out of range. You entered \'' + str(number) + '\'.\nPlease enter an integer in the range [0,' + str(max_range) + ')\n'
            

    
# create frame
fr = simplegui.create_frame('Guess the number', 200, 200)
fr.add_button('Range is [0, 100)', range100, 200)
fr.add_button('Range is [0, 1000)', range1000, 200)
fr.add_input('Enter a guess', input_guess, 200)


# register event handlers for control elements and start frame


# call new_game 
new_game()


# always remember to check your completed program against the grading rubric
