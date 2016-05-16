//http://web.cs.ucla.edu/~rosen/161/notes/alphabeta.html
//1 is AI
package cases;

import java.awt.Point;
import java.util.Arrays;

public class Generator {
	private String out, in;
	private Point[] board;
	private int index, score;
	
	public Generator(String in){
		this.in=in;
		for (int i=0;i<in.length()/2;i++){
			board[i]=new Point(Integer.parseInt(""+in.charAt(2*i)), Integer.parseInt(""+in.charAt(2*i+1))); //pairs booleans
		}
		score=-10;
		minimax(board, 0, Integer.MAX_VALUE, Integer.MAX_VALUE);
	}

	private int minimax(Point[] input, int depth, int alpha, int beta) {
		boolean isMax;
		Point[] temp;
		if(depth%2==0){ //is it a maximizing or minimizing node
			isMax=true;
		}else{
			isMax=false;
		}
		
		while(alpha>=beta){
			if(isWin()==1) //returns score if a player wins
				if (isMax) alpha=depth-10;
				else beta=depth-10;
			else if(isWin()==-1)
				if (isMax) alpha=-10;
				else beta=-10;
			
			//else
			for (int i=0; i<input.length; i++){ //loop through each square
				if (input[i].x==0){ //if square is empty
					 temp=Arrays.copyOf(input, input.length);
					 temp[i].x=1;
					 if (isMax){
						 temp[i].y=1;
						 alpha=minimax(temp, depth+1, alpha, beta);
						 if (alpha>score&&alpha<0){
							score=Integer.valueOf(alpha);
							index=Integer.valueOf(i);
						 }
					 }else{
						 temp[i].y=0;
						 beta=minimax(temp, depth+1, alpha, beta);
						 if (beta>score&&beta<0){
							score=Integer.valueOf(beta);
							index=Integer.valueOf(i);
						 }
					 }
				}
			}
		}
			
		if(isMax) return alpha;
		else return beta;
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
		//not done yet
		return out;
	}

}
