package perceptual_network;


import neurons.*;

public class InnerLayer extends Layer {
	private static int num = 0;
	
	public InnerLayer(){
		super();
		num++;
	}
	
	public InnerLayer(int size) {
		super(size);
		num++;
	}
	
	public static int getNum(){
		return num;
	}
	
	public void wireAxon(Neuron p, int y){
		((Perceptron) super.bank[y]).addInput(p);
	}
	
	public void wireAxons(Neuron[] I, int y){
		((Perceptron) super.bank[y]).addInputs(I);
	}
	

}
