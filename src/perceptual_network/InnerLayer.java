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
		for(int i = 0; i<size; i++)
			bank[i] = new Perceptron();
		num++;
	}
	
	public static int getNum(){
		return num;
	}
	public static void resetNum(){
		num=0;
	}
	
	public void wireAxon(Neuron p, int y){
		((Perceptron) super.bank[y]).addInput(p);
	}
	
	public void wireAxons(Layer l, int y){
		((Perceptron) super.bank[y]).addInputs(l.bank);
	}
	
	public void wireAllAxons(Layer l){
		for(int i = 0; i < super.bank.length; i++){
			this.wireAxons(l, i);
		}
	}
	
	public void setDefaultWeights(float[] weights){
		for(int i = 0; i < weights.length; i++)
			((Perceptron)super.bank[i]).setDefaultWeight(weights[i]);
	}

}
