package boxPuzzleGame;
import java.util.*;
public class DFS {
	public static ArrayList<String> records = new ArrayList<String>(); 
	public static Stack<node> st = new Stack<node>();
	
	public static void main(String args[]) {// main method that runs the AI
		Scanner s = new Scanner(System.in);
		
		System.out.println("Input Starting Board State");
		String input = s.next();
		
		node Sstate = new node(input);
		
		
	
		records.add(Sstate.record);
		st.add(Sstate);
		
		node goal = DFSrun();
		System.out.println("See steps? y or n");
		String dis = s.next();
		
		if(dis.compareTo("y") == 0) {
			while(goal != null) {
				goal.displayState(goal.boardState, goal.depth, goal.heuristicValue);
				goal = goal.parent;
			}
		}

	}
	
	public static node DFSrun() {//Depth first search tree that is used to traverse the tree of possible board states. Checks the board states with the highest heuristic values first
		int iteration = 0;
		node n = null;
		while(st.empty() == false){
			n = st.pop();
			records.add(n.record);
			n.displayState(n.boardState, iteration, n.heuristicValue);
			iteration++;
			
			n.createChildArr(n.children, n.possibleMoves, n.boardState, n.IOS, n.depth, n, n.heuristicValue);
			
			sortChildren(n, 0, n.children.size() - 1);
			
			for(node i : n.children) {
				if(checkRecords(i)) {
					st.push(i);
				}
			}
			if(n.goalState) {
				return n;
			}
		}
		return n;
	}
	
	public static boolean checkRecords(node n) {//checks an arraylist of previous visited board states to see if the given board state has been visited already
		int i = 0;
		boolean newEntry = true;
		while(i < records.size() && newEntry == true) {
			if(n.record.compareTo(records.get(i)) == 0) {
				newEntry = false;
			}
			i++;
		}
		return newEntry;
	}
		
	public static void sortChildren(node n, int L , int R) {//sort the array of children nodes from smallest heuristic value to greatest heuristic value
		int mid = (R + L)/2;
		
		if((R-L) > 1) {
			sortChildren(n, L, mid -1);
			sortChildren(n, mid, R);
		}
		
		node [] temp = new node [(R - L) + 1];
		
		int i = 0;
		int start = L;
		if((R+L)%2 != 0) {
			mid++;
		}
		
		while(i < temp.length) {
			if(mid <= R && n.children.get(L).heuristicValue > n.children.get(mid).heuristicValue) {
				temp[i] = n.children.get(mid);
				mid++;
				i++;
			}
			else {
				temp[i] = n.children.get(L);
				i++;
				L++;
			}
		}
		
		int j = 0;
		while(j < temp.length) {
			n.children.set(start + j, temp[j]);
			j++;
		}
	
	}
}

	
