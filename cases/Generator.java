//http://web.cs.ucla.edu/~rosen/161/notes/alphabeta.html
//1 is AI
package cases;

import java.awt.Point;
import java.util.Arrays;

public class Generator {
	private String out;
	private Point[] board;
	private int index, score;
	
	public Generator(String in){
		board=new Point[in.length()/2];
		for (int i=0;i<in.length()/2;i++){
			board[i]=new Point(Integer.parseInt(""+in.charAt(2*i)), Integer.parseInt(""+in.charAt(2*i+1))); //pairs booleans
		}
		score=-10;
		minimax(board, 0, Integer.MAX_VALUE, Integer.MAX_VALUE);
	}

	private int minimax(Point[] input, int depth, int alpha, int beta) {
		boolean isMax; 
		int a=Integer.valueOf(alpha);
		int b=Integer.valueOf(beta);
		Point[] temp;
		if(depth%2==0){ //is it a maximizing or minimizing node
			isMax=true;
		}else{
			isMax=false;
		}
		
		while(a>=b && a>=alpha && b<=beta){
			if(isWin()==1) //set score if a player wins
				if (isMax) a=depth-10;
				else b=depth-10;
			else if(isWin()==-1)
				if (isMax) a=-10;
				else b=-10;
			
			//else
			for (int i=0; i<input.length; i++){ //loop through each square
				if (input[i].x==0){ //if square is empty
					 temp=Arrays.copyOf(input, input.length);
					 temp[i].x=1;
					 if (isMax){
						 temp[i].y=1;
						 a=minimax(temp, depth+1, alpha, beta);
						 if (a>score&&a<0){
							score=Integer.valueOf(a);
							index=Integer.valueOf(i);
							alpha=Integer.valueOf(a);
						 }
					 }else{
						 temp[i].y=0;
						 b=minimax(temp, depth+1, alpha, beta);
						 if (b>score&&b<0){
							score=Integer.valueOf(b);
							index=Integer.valueOf(i);
							beta=Integer.valueOf(b);
						 }
					 }
				}
			}
		}
			
		if(isMax) return alpha;
		else return beta;
	}
	
	private int isWin(){
		if(board[0].x==1 && board[1].x==1 && board[2].x==1  
		&& board[0].y==board[1].y && board[1].y==board[2].y 
		|| board[0].x==1 && board[3].x==1 && board[6].x==1  
		&& board[0].y==board[3].y && board[3].y==board[6].y){
			if(board[0].y==1) return 1;
			else return -1;
		}
		
		if(board[3].x==1 && board[4].x==1 && board[5].x==1
		&& board[3].y==board[4].y && board[4].y==board[5].y
		|| board[1].x==1 && board[4].x==1 && board[7].x==1
		&& board[1].y==board[4].y && board[4].y==board[7].y
		|| board[0].x==1 && board[4].x==1 && board[8].x==1 
		&& board[0].y==board[4].y && board[4].y==board[8].y
		|| board[2].x==1 && board[4].x==1 && board[6].x==1
		&& board[2].y==board[4].y && board[4].y==board[6].y){
			if(board[4].y==1) return 1;
			else return -1;
		}
		
		if(board[6].x==1 && board[7].x==1 && board[8].x==1
		&& board[6].y==board[7].y && board[7].y==board[8].y
		|| board[2].x==1 && board[5].x==1 && board[8].x==1
		&& board[2].y==board[5].y && board[5].y==board[8].y){
			if(board[8].y==1) return 1;
			else return -1;
		}
		
		return 0;
	}

	public String getOut(){
		out=Integer.toString(index, 2);
		return out;
	}

}
