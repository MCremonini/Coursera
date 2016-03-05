# Rock-paper-scissors-lizard-Spock template

import random

# The key idea of this program is to equate the strings
# "rock", "paper", "scissors", "lizard", "Spock" to numbers
# as follows:
#
# 0 - rock
# 1 - Spock
# 2 - paper
# 3 - lizard
# 4 - scissors

# helper functions

def name_to_number(name):
    # convert name to number using if/elif/else
    if name == 'rock':
        ret = 0
    elif name == 'Spock':
        ret = 1
    elif name == 'paper':
        ret = 2
    elif name == 'lizard':
        ret = 3
    else:
        ret = 4
    # don't forget to return the result!
    return ret


def number_to_name(number):
    # convert number to a name using if/elif/else
    if number == 0:
        ret = 'rock'
    elif number == 1:
        ret = 'Spock'
    elif number == 2:
        ret = 'paper'
    elif number == 3:
        ret = 'lizard'
    else:
        ret = 'scissors'
    # don't forget to return the result!
    return ret
    

def rpsls(player_choice): 
        
    # print a blank line to separate consecutive games
    print

    # print out the message for the player's choice
    print 'Player chooses ', player_choice

    # convert the player's choice to player_number using the function name_to_number()
    player_number = name_to_number(player_choice)

    # compute random guess for comp_number using random.randrange()
    comp_number = random.randrange(0,5)

    # convert comp_number to comp_choice using the function number_to_name()
    comp_choice = number_to_name(comp_number)
    
    # print out the message for computer's choice
    print 'Computer chooses ', comp_choice

    # compute difference of comp_number and player_number modulo five
    diff = (comp_number - player_number) % 5

    # use if/elif/else to determine winner, print winner message
    if diff > 2:
        print 'Player wins!'
    elif diff > 0:
        print 'Computer wins!'
    else:
        print 'Player and computer tie!'      

    
# test your code - THESE CALLS MUST BE PRESENT IN YOUR SUBMITTED CODE
rpsls("rock")
rpsls("Spock")
rpsls("paper")
rpsls("lizard")
rpsls("scissors")

# always remember to check your completed program against the grading rubric
def rpsls(player_choice, comp_choice): 
    # used for test
        
    # print a blank line to separate consecutive games
    print

    print 'Player chooses ', player_choice
    player_number = name_to_number(player_choice)

    print 'Computer chooses ', comp_choice
    comp_number = name_to_number(comp_choice)
 
    diff = (comp_number - player_number) % 5

    # use if/elif/else to determine winner, print winner message
    if diff > 2:
        print 'Player wins!'
    elif diff > 0:
        print comp_choice, ' beats ', player_choice, '. Computer win!'
    else:
        print 'Computer wins!'

print
print '****************************'
print '****************************'
print '*** TEST ALL COMBINATION ***'
print '****************************'
print '****************************'

rpsls("rock", "scissors")
rpsls("rock", "Spock")
rpsls("rock", "lizard")
rpsls("rock", "paper")
rpsls("rock", "rock")

rpsls("Spock", "scissors")
rpsls("Spock", "rock")
rpsls("Spock", "lizard")
rpsls("Spock", "paper")
rpsls("Spock", "Spock")

rpsls("paper", "scissors")
rpsls("paper", "Spock")
rpsls("paper", "lizard")
rpsls("paper", "rock")
rpsls("paper", "paper")

rpsls("lizard", "rock")
rpsls("lizard", "scissors")
rpsls("lizard", "Spock")
rpsls("lizard", "lizard")
rpsls("lizard", "paper")

rpsls("scissors", "rock")
rpsls("scissors", "scissors")
rpsls("scissors", "Spock")
rpsls("scissors", "lizard")
rpsls("scissors", "paper")