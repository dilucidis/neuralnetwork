//http://web.cs.ucla.edu/~rosen/161/notes/alphabeta.html
//X is TRUE
package cases;

import java.awt.Point;

public class Generator {
	private String out;
	private Point[] board;
	
	public Generator(String in){
		for (int i=0;i<in.length()/2;i++){
			board[i]=new Point(Integer.parseInt(""+in.charAt(2*i)), Integer.parseInt(""+in.charAt(2*i+1))); //pairs booleans
		}
		
		minimax(board, true, 0, Integer.MAX_VALUE, Integer.MAX_VALUE);
	}

	private int minimax(Point[] input, boolean isMax, int depth, int alpha, int beta) {
		if(isWin()==1)
			return depth-10;
		else if(isWin()==-1)
			return -10;
		
		for (int i=0; i<input.length; i++){ //loop through each square
			if (input[i].x==0){ //if square is empty
				
			}
		}
		
		return alpha;
	}
	
	private int isWin(){
		if(board[1].x==1 && board[2].x==1 && board[3].x==1  
		&& board[1].y==board[2].y && board[2].y==board[3].y 
		|| board[1].x==1 && board[4].x==1 && board[7].x==1  
		&& board[1].y==board[4].y && board[4].y==board[7].y){
			if(board[1].y==1) return 1;
			else return -1;
		}
		
		if(board[4].x==1 && board[5].x==1 && board[6].x==1
		&& board[4].y==board[5].y && board[5].y==board[6].y
		|| board[2].x==1 && board[5].x==1 && board[8].x==1
		&& board[2].y==board[5].y && board[5].y==board[8].y
		|| board[1].x==1 && board[5].x==1 && board[9].x==1 
		&& board[1].y==board[5].y && board[5].y==board[9].y
		|| board[3].x==1 && board[5].x==1 && board[7].x==1
		&& board[3].y==board[5].y && board[5].y==board[7].y){
			if(board[5].y==1) return 1;
			else return -1;
		}
		
		if(board[7].x==1 && board[8].x==1 && board[9].x==1
		&& board[7].y==board[8].y && board[8].y==board[9].y
		|| board[3].x==1 && board[6].x==1 && board[9].x==1
		&& board[3].y==board[6].y && board[6].y==board[9].y){
			if(board[9].y==1) return 1;
			else return -1;
		}
		
		return 0;
	}

	public String getOut(){
		return out;
	}

}
