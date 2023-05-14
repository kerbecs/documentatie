import uagame
import time

from uagame import Window
from time import sleep

# open window
window = Window('hello',300,200)

# display input message in the top left corner
text = window.input_string('Enter string >',0,0)

# count x for the text
x = window.get_width() - window.get_string_width(text)

# count y for text
y = window.get_height() - window.get_font_height()

# display the text input
window.draw_string(text,x,y)

#update the window
window.update()

# sleep for 2 seconds
sleep(2)

# close the window
window.close()


