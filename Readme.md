# Mars Rover Kata

Develop an API that moves a rover around on a grid.

## Rules:

+ You are given the initial starting 2D point (x,y) of a rover and the direction (N,S,E,W) it is facing.
+ The rover receives a character array of commands (String).
    + F to move forward
    + B to move backward
    + L to turn left
    + R to turn right
    + Z to undo previous command
    + Y to redo previously undone command
+ Use TDD