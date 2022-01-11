# 8-Puzzle-Weighted-AI-

This project is an low level AI that solves an 8 puzzle with a twist: moving each piece has a cost associated with it, which is the value of that piece. In other words, moving the number 2 tile would cost 2 and moving the number 9 tile would cost 9. 

Given the goal that you want the 8 puzzle to reach and the starting state of the 8 puzzle, the AI will use a depth first search tree, searching only 1 layer deep, to find the immediate best move.

The best move is determined by a heuristic value of a given board state, which I made equal to how much it would cost to reach the board state. That way the AI will prefer to make more low costing moves than few high costing moves. 

