# Design Snake and Ladder

The problem statement can be found at https://www.procoderfast.com/low-level-design/problem/snake-and-ladder.

## Design Diagram

You can find the design diagram SnakeAndLadder.png in the current directory.

## Approach
Game board is represented by the Board class and each cell is represented by the Cell class. We have a Player class to represent players and Dice class to represent the dice.

Ladders and Snakes are represented by Ladder and Snake classes respectively, both classes are child of Path class. Path is representing a path on the board with a start and an end.

One complex piece of this problem is the validation part of the user input as there are multiple contraints on the input like you cannot have snakes and ladders forming a cycle. For this particular validation we have a utility class called PathGraph which detects cycle in the paths (snakes and ladders) on the board.

The game is auto played by the AutoRunner class which creates an instance of the Game class and simulate the actions of the players.