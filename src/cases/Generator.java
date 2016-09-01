//http://web.cs.ucla.edu/~rosen/161/notes/alphabeta.html
//1 is CPU
package cases;

import java.awt.Point;

public class Generator {
	private String out;
	private Point[] board;
	private int index, max, drawIndex, depthZeroIndex;
	
	public Generator(String in){
		board=new Point[in.length()/2];
		for (int i=0;i<in.length()/2;i++){
			board[i]=new Point(Integer.parseInt(""+in.charAt(2*i)), Integer.parseInt(""+in.charAt(2*i+1))); //pairs booleans
		}
		max=Integer.MIN_VALUE; drawIndex=-1;
		minimax(board, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private int minimax(Point[] input, int depth, int alpha, int beta) {
		boolean isMax; 
		Point[] temp=new Point[input.length];
		if(depth%2==0){ 
			isMax=true;
		}else{
			isMax=false;
		}
		
		if (isMax){
			alpha=evaluate(input);
		}else{
			beta=evaluate(input);
		}
		
		System.out.println("\nMINIMAX\ndepth: "+depth+"\nisMax: "+isMax+"\nbeta: "+beta+"\nalpha: "+alpha); //console
		
		if (alpha>-50 && beta<50 || alpha==Integer.MIN_VALUE || beta==Integer.MAX_VALUE){ //if not a win
			for (int i=0;i<input.length;i++){
				if (depth==0){
					depthZeroIndex=Integer.valueOf(i);
				}
				if (alpha<=beta){
					if (isFull(input) && evaluate(input)==0){
						drawIndex=Integer.valueOf(depthZeroIndex);
					}
					if (input[i].x==0){
						copy(input,temp);
						temp[i].x=1;
						if (isMax){
							temp[i].y=1;
							alpha=minimax(temp, depth+1, alpha, beta);
							if (alpha<=beta){	
								if (alpha>max){
									max=Integer.valueOf(alpha);
									index=Integer.valueOf(i);
								}
							}
						}else{
							temp[i].y=0;
							beta=minimax(temp, depth+1, alpha, beta);							}
						}
					
					if (depth==0 && i==input.length-1){ //by the last child node
						if (max<50){
							index=Integer.valueOf(drawIndex);
						}
					}
				}	
			}
		}
		
		if(isMax) return alpha;
		else return beta;
	}
	
	private int evaluate(Point[] brd){
		int score=0;
		score+=evaluate(brd[0],brd[1],brd[2]);
		score+=evaluate(brd[3],brd[4],brd[5]);
		score+=evaluate(brd[6],brd[7],brd[8]);
		
		score+=evaluate(brd[0],brd[3],brd[6]);
		score+=evaluate(brd[1],brd[4],brd[7]);
		score+=evaluate(brd[2],brd[5],brd[8]);
		
		score+=evaluate(brd[0],brd[4],brd[8]);
		score+=evaluate(brd[6],brd[4],brd[2]);
		
		return score;
	}
	
	private int evaluate(Point a, Point b, Point c){
		int score=0;
		int humanMoves=0;
		int cpuMoves=0;
		int empty=0;
		Point[] line={a,b,c};
		
		for (Point pt: line){
			if (pt.x==1){
				if (pt.y==1)
					cpuMoves++;
				else
					humanMoves++;
			}else{
				empty++;
			}
		}
		
		if (3-cpuMoves==empty){
			switch(cpuMoves){
			default: break;
			case 1: score++;
					break;
			case 2: score+=10;
					break;
			case 3: score+=100;
					break;
			}
		}	
		
		if (3-humanMoves==empty){
			switch(humanMoves){
			default: break;
			case 1: score--;
					break;
			case 2: score-=10;
					break;
			case 3: score-=100;
					break;
			}
		}
	
		return score;
	}
	
	private void copy(Point[] original, Point[] b){
		for (int i=0;i<original.length;i++)
			b[i]=new Point(original[i].x,original[i].y);
	}
	
	private boolean isFull(Point[] brd){
		for (Point p:brd){
			if (p.x==0) 
				return false;
		}return true;
	}
	
	public String getOut(){
		out=Integer.toString(index, 2);
		while (out.length()!=4){
			out="0"+out;
		}
		return out;
	}

}
