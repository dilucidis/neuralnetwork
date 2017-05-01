package perceptual_network;

import neurons.Neuron;
import neurons.Perceptron;

public class OutputLayer extends Layer {


	public OutputLayer(int size) {
		super(size);
	}

	public OutputLayer(Neuron[] rawLayer) {
		super(rawLayer.length);
		for(Neuron n : rawLayer)
			n = new Perceptron();
	}
	
	public boolean[] output(){
		boolean[] out = new boolean[super.bank.length];
		for(int i = 0; i<out.length;i++)
			if(bank[i].checkFire()>=1.0f)
				out[i]=true;
			else
				out[i]=false;
		
		return out;
	}
}
