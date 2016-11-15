//CheckersBoard.java

class CheckersBoard{
	public static final int BLACK = 1;
	public static final int RED = 2;
	private int[][] board = new int[8][8];
	public CheckersBoard(){
		//sets all the black pieces at y values 0 - 2 and only when on white squares (when modulus 2 is different)
		for(int i = 0;i < 8;i++){
			for(int j = 0;j < 3;j++){
				if(j%2 != i%2)	board[j][i] = BLACK;
			}
		}
		//sets all the white pieces at y values 5 - 7 and only when on white squares (when modulus 2 is different)
		for(int i = 0;i < 8;i++){
			for(int j = 5;j < 8;j++){
				if(j%2 != i%2) board[j][i] = RED;
			}
		}
	}
	public boolean move(int x1, int y1, int x2, int y2){
		//tries to move and returns the success
		if(x1 == x2 && y1 == y2) return true; //if we reached the square it is true
		if(board[y1][x1] == 0) return false; //returns false if there is nothing on starting position
		if(x2 >= 8 || x2 < 0 || y2 >= 8 || y2 < 0) return false; //if the goal is out-of-bounds it is invalid
		if(board[y2][x2] != 0) return false; //we cannot move onto a square with a piece on it
		if(x1 >= 8 || x1 < 0 || y1 >= 8 || y1 < 0) return false; //out-of-bounds is false
		//the following two if statements check for movements directly to the left or the right, not jumping
		int curr = board[y1][x1]; //current colour
		int vertNext = 0; //next verticle square (for BLACK it is up for RED it is down)
		
		if(curr == BLACK){
			vertNext = 1; //going up
		} else{
			vertNext = -1; //going down
		}
		if(x1 + 1 < 8 && y1 + vertNext < 8){
			if(x1 + 1 == x2 && y1 + vertNext == y2){
				board[y1][x1] = 0;
				board[y2][x2] = curr;
				return true;
			}
		}
		if(x1 - 1 >= 0 && y1 + vertNext < 8){
			if(x1 - 1 == x2 && y1 + vertNext == y2){
				board[y1][x1] = 0;
				board[y2][x2] = curr;
				return true;
			}
		}
		//we now return the result of trying to jump
		if(move(x1,y1,x2,y2,curr)){
			board[y1][x1] = 0;
			board[y2][x2] = curr;
			return true;
		} else{
			return false;
		}
	}
	public boolean move(int x1, int y1, int x2, int y2, int curr){
		//checks if we can move using only jumping
		if(x1 == x2 && y1 == y2) return true; //if we reached the square it is true
		if(x1 >= 8 || x1 < 0 || y1 >= 8 || y1 < 0) return false; //out-of-bounds is false
		int opp = curr == BLACK?RED:BLACK; //opposite colour
		
		boolean valid = false;
		int vertNext = 0; //next verticle square (for BLACK it is up for RED it is down)
		
		if(curr == BLACK){
			vertNext = 1; //going up
		} else{
			vertNext = -1; //going down
		}
		if(x1 + 1 < 8 && y1+vertNext >= 0 && y1+vertNext < 8){
			if(board[y1+vertNext][x1+1] == opp){
				if(x1 + 2 < 8 && y1 + vertNext*2 < 8 && y1 + vertNext*2 >= 0){
					if(board[y1 + vertNext*2][x1+2] == 0)
						if(move(x1+2,y1+vertNext*2,x2,y2,curr)){
							board[y1+vertNext][x1+1] = 0; //removes piece we jumped over
							valid = true; //check if jumping right works
						}
				}
			}
		}
		if(x1 - 1 >= 0 && y1+vertNext >= 0 && y1+vertNext < 8 && !valid){
			//only checks if we can move the other way if we can't move the other way
			if(board[y1+vertNext][x1-1] == opp){
				if(x1 - 2 >= 0 && y1 + vertNext*2 < 8 && y1 + vertNext*2 >= 0){
					if(board[y1 + vertNext*2][x1-2] == 0)
						if(move(x1-2,y1+vertNext*2,x2,y2,curr)){
							board[y1+vertNext][x1-1] = 0; //removes piece we jumped over
							valid = true; //check if jumping left works
						}
				}
			}
		}
		return valid;
	}
	public int count(int colour){
		//returns number of a colour in the board
		int tot = 0;
		for(int i = 0;i < 8;i++){
			for(int j = 0;j < 8;j++){
				if(board[j][i] == colour){
					tot += 1; //increases total when we find colour
				}
			}
		}
		return tot;
	}
	public int getSquare(int x, int y){
		//returns square at x,y
		return board[y][x];
	}
	public void display(){
		//draws board
		for(int j = 7;j >= 0;j--){
			for(int i = 0;i < 8;i++){
				System.out.print(board[j][i]);
			}
			System.out.print('\n');
		}
	}
}