package boxPuzzleGame;
import java.util.*;
public class node {
	public String record;
	public boolean goalState;
	public int[] boardState;
	public int cost;
	public int heuristicValue;
	public int depth;
	public int IOS;
	public String PreviousMove;
	public String possibleMoves;
	public ArrayList<node> children;
	public node parent;
	
	public node(String input) {//initial starting board state node
		record = input;
		goalState = GoalCheck(input);
		boardState = createArr(input);
		cost = 0;
		heuristicValue = 0;
		depth = 0;
		IOS = indexOfSpace(boardState);
		PreviousMove = "";
		possibleMoves = possibleMoves(boardState, IOS, PreviousMove);
		children = new ArrayList<node>();
		parent = null;
	}
	
	public node(String input, node par, int c, String prevMov, int indexOfSpace, int d, int hv) {//board state node in the tree of possible moves. 
		record = input;
		goalState = GoalCheck(input);
		boardState = createArr(input);
		cost = c;
		heuristicValue = (8 - c) + hv;
		if(goalState) {
			heuristicValue = Integer.MAX_VALUE;
		}
		depth = d + 1;
		IOS = indexOfSpace;
		PreviousMove = prevMov;
		possibleMoves = possibleMoves(boardState, IOS, PreviousMove);
		children = new ArrayList<node>();
		parent = par;
	}
	
	public int indexOfSpace(int [] state) { //Returns the index of where the space in the board state is
		int i = 0;
		while(state[i] != 0) {
			i++;
		}
		int indexOfSpace = i;
		
		return indexOfSpace;
	}
	
	public String possibleMoves(int [] state, int indexOfSpace, String PreviousMove) { //Returns a string of all possible moves based on given board state
		String possibleM = "";
		
		if(indexOfSpace != 0 && indexOfSpace != 3 && indexOfSpace != 6 && !PreviousMove.equals("L")) {
			possibleM = possibleM + "R";
		}
		if(indexOfSpace != 2 && indexOfSpace != 5 && indexOfSpace != 8 && !PreviousMove.equals("R")) {
			possibleM = possibleM + "L";
		}
		if(indexOfSpace != 0 && indexOfSpace != 1 && indexOfSpace != 2 && !PreviousMove.equals("U"))  {
			possibleM = possibleM + "D";
		}
		if(indexOfSpace != 6 && indexOfSpace != 7 && indexOfSpace != 8 && !PreviousMove.equals("D")) {
			possibleM = possibleM + "U";
		}
		
		return possibleM;
	}
	
	public int[] createArr(String input) {//Turns a string of integers into an array of integers
		int[] value = new int [9];
		int i = 0;
		while(i< 8) {
			value[i] = Integer.valueOf(input.substring(i, i+1));
			i++;
		}
		value[i] = Integer.valueOf(input.substring(i));
		
		return value;
	}
	
	public boolean GoalCheck(String value) { //Checks to see if the board state has reached the goal state
		boolean isGoal = false;
		if(Integer.valueOf(value) == 123804765) {
			isGoal = true;
		}
		return isGoal;
	}
	
	public void createChildArr(ArrayList<node> childA, String possMoves, int [] boardState, int ios, int depth, node p, int heuristicValue) {//based on a node of a board state, it will create children node of board states and add it to the arraylist of board states

		String output = "";
		int piece;
		
		if(possMoves.contains("L")) {
			piece = boardState[ios+1];
			boardState[ios+1] = 0;
			boardState[ios] = piece;
			
			output = sboardState(boardState);
			
			childA.add(new node(output, p, piece, "L", ios+1, depth, heuristicValue));
			
			boardState[ios] = 0;
			boardState[ios+1] = piece;
			output = "";
		}
		
		if(possMoves.contains("R")) {
			piece = boardState[ios-1];
			boardState[ios-1] = 0;
			boardState[ios] = piece;
			
			output = sboardState(boardState);
			
			childA.add(new node(output, p, piece, "R", ios-1, depth, heuristicValue));
			
			boardState[ios] = 0;
			boardState[ios-1] = piece;
			output = "";
		}
		
		if(possMoves.contains("U")) {
			piece = boardState[ios+3];
			boardState[ios+3] = 0;
			boardState[ios] = piece;
			
			output = sboardState(boardState);
			
			childA.add(new node(output, p, piece, "U", ios+3, depth, heuristicValue));
			
			boardState[ios] = 0;
			boardState[ios+3] = piece;
			output = "";
		}
		
		if(possMoves.contains("D")) {
			piece = boardState[ios-3];
			boardState[ios-3] = 0;
			boardState[ios] = piece;
			
			output = sboardState(boardState);
					
			childA.add(new node(output, p, piece, "D", ios-3, depth, heuristicValue));
			
			boardState[ios] = 0;
			boardState[ios-3] = piece;
			output = "";
		}
	}
	
	public String sboardState(int [] boardState) { //returns an String version of the board state given the array of the board state
		int i = 0;
		String output = "";
			
		while(i < 9) {
			output = output + boardState[i];
			i++;
		}
		
		return output;
	}

	public void displayState(int [] boardState, int iteration, int heuristicValue) {//prints out current iteration, the heuristic value of the iteration, and the board state of the iteration
		int i = 0;
		int j = 0;
		
		System.out.println("Iteration #: " + iteration);
		System.out.println("Heuristic Value: " + heuristicValue);
		
		while(j < 3) {
			while(i < 3) {
				System.out.print( boardState[i+j*3] + " ");
				i++;
			}
			System.out.println("");
			i = 0;
			j++;
		}
	}
}
