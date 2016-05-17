//http://web.cs.ucla.edu/~rosen/161/notes/alphabeta.html
//1 is AI
package cases;

import java.awt.Point;

public class Generator {
	private String out;
	private Point[] board;
	private int index, score;
	
	public Generator(String in){
		board=new Point[in.length()/2];
		for (int i=0;i<in.length()/2;i++){
			board[i]=new Point(Integer.parseInt(""+in.charAt(2*i)), Integer.parseInt(""+in.charAt(2*i+1))); //pairs booleans
		}
		score=Integer.MIN_VALUE;
		minimax(board, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private int minimax(Point[] input, int depth, int alpha, int beta) {
		boolean isMax; 
		int a=Integer.valueOf(alpha);
		int b=Integer.valueOf(beta);
		Point[] temp=new Point[input.length];
		if(depth%2==0){ //is it a maximizing or minimizing node
			isMax=true;
		}else{
			isMax=false;
		}
		
		//test (more pruning)
		if (isMax && beta==-10){ //if minimizing move already found
			return Integer.MAX_VALUE; //Ignore node and children
		}
		
		//test (more pruning)
		if (!isMax && alpha>-10 && alpha<0){ //if maximizing move already found
			return Integer.MIN_VALUE; //Ignore node and children
		}
		
		System.out.println("\nMINIMAX\ndepth: "+depth+"\nalpha: "+alpha+"\nbeta: "+beta); //console
		while(a<=b && a>=alpha && b<=beta){
			System.out.println("isWin(): "+isWin(input)); //console
			if(isWin(input)==1){ //set score if a player wins
				if (isMax) alpha=depth-10;
				else beta=depth-10;
				break;
			}else if(isWin(input)==-1){
				if (isMax) alpha=-10;
				else beta=-10;
				break;
			}
			else{
				
				for (int i=0; i<input.length; i++){ //loop through each square
					if (input[i].x==0){ //if square is empty
						 System.out.println("next index: "+i);
						 copy(input, temp);
						 temp[i].x=1;
						 if (isMax){
							 temp[i].y=1;
							 a=minimax(temp, depth+1, alpha, beta);
							 if (a>alpha){
								 if (a>score){
									score=Integer.valueOf(a);
									index=Integer.valueOf(i);
								 }
								alpha=Integer.valueOf(a);
							 }
						 }else{
							 temp[i].y=0;
							 b=minimax(temp, depth+1, alpha, beta);
							 if (b<beta){
								 beta=Integer.valueOf(b);
							 }
						 }
					}
				}
				
			}
		}
			
		if(isMax) return alpha;
		else return beta;
	}
	
	private int isWin(Point[] b){
		if(b[0].x==1 && b[1].x==1 && b[2].x==1  
		&& b[0].y==b[1].y && b[1].y==b[2].y 
		|| b[0].x==1 && b[3].x==1 && b[6].x==1  
		&& b[0].y==b[3].y && b[3].y==b[6].y){
			if(b[0].y==1) return 1;
			else return -1;
		}
		
		if(b[3].x==1 && b[4].x==1 && b[5].x==1
		&& b[3].y==b[4].y && b[4].y==b[5].y
		|| b[1].x==1 && b[4].x==1 && b[7].x==1
		&& b[1].y==b[4].y && b[4].y==b[7].y
		|| b[0].x==1 && b[4].x==1 && b[8].x==1 
		&& b[0].y==b[4].y && b[4].y==b[8].y
		|| b[2].x==1 && b[4].x==1 && b[6].x==1
		&& b[2].y==b[4].y && b[4].y==b[6].y){
			if(b[4].y==1) return 1;
			else return -1;
		}
		
		if(b[6].x==1 && b[7].x==1 && b[8].x==1
		&& b[6].y==b[7].y && b[7].y==b[8].y
		|| b[2].x==1 && b[5].x==1 && b[8].x==1
		&& b[2].y==b[5].y && b[5].y==b[8].y){
			if(b[8].y==1) return 1;
			else return -1;
		}
		
		return 0;
	}
	
	private void copy(Point[] a, Point[] b){
		for (int i=0;i<a.length;i++)
			b[i]=new Point(a[i].x,a[i].y);
	}
	
	private boolean isFull(Point[] brd){
		for (Point p:brd){
			if (p.x==0) 
				return false;
		}return true;
	}

	private void padOut(){
		for (int i=0; i<4-out.length();i++){
			out="0"+out;
		}
	}
	
	public String getOut(){
		out=Integer.toString(index, 2);
		padOut();
		return out;
	}

}
