package perceptual_network;

import interfaces.*;
import neurons.*;

//updateable simply calls update on each neuron (layer type independent, which is nice)
public abstract class Layer implements Updateable{
	
	private static int num = 0;
	protected Neuron[] bank;
	
	public Layer(){
		num++;
	}
	
	public Layer(int size){
		//my new favorite trick
		this();
		bank = new Neuron[size];
	}
	
	public int length(){
		return bank.length;
	}
	
	public void update(){
		for(Neuron n: bank)
			n.update();
	}
	
	public Neuron setNeuron(int x, Neuron n){
		Neuron old = bank[x];
		bank[x] = n;
		return old;
	}
	
	public Neuron[] setNeurons(Neuron[] n){
		Neuron[] temp = new Neuron[bank.length];
		for(int i = 0; i < n.length; i++){
			temp[i]=bank[i];
			bank[i]=n[i];
		}
			return temp;
	}
	
	public Neuron grabAxon(int x){
		return bank[x];
	}
	
	public static int getNum(){
		return num;
	}	
	
	public static void resetNum(){
		num=0;
	}
	
	public float[] checkfire(){
		float[] firevalues = new float[this.bank.length];
		for (int i = 0; i < this.bank.length; i++)
			firevalues[i] = this.bank[i].checkFire();
		return firevalues;
	}
}
