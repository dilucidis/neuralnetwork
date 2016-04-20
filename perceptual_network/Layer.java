package perceptual_network;

import interfaces.*;

//updateable will only be implemented in the subclasses
public abstract class Layer implements Updateable{
	public static int num = 0;
	private Perceptron[] bank;
	
	public Layer(){
		num++;
	}
	
	public Layer(int size){
		//my new favorite trick
		this();
		bank = new Perceptron[size];
	}
	
	public Perceptron setPerceptron(Perceptron p, int x){
		Perceptron old = bank[x];
		bank[x] = p;
		return old;
	}
	
	public Perceptron grabAxon(int x){
		return bank[x];
	}
	
	public void wireAxon(Perceptron p, int y){
		bank[y].addInput(p);
	}
	
	public void wireAxons(Perceptron[] I, int y){
		bank[y].addInputs(I);
	}
	
	public static int getNum(){
		return num;
	}	
}
