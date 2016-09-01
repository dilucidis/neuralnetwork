package perceptual_network;

import neurons.Neuron;

public class OutputLayer extends Layer {


	public OutputLayer(int size) {
		super(size);
		// TODO Auto-generated constructor stub
	}

	public OutputLayer(Neuron[] rawLayer) {
		super(rawLayer);
		// TODO Auto-generated constructor stub
	}
	public boolean[] output(){
		float f;
		boolean[] out = new boolean[super.bank.length];
		for(int i = 0; i<out.length;i++){
			f = bank[i].checkFire();
			if (f==1.0f)
				out[i]=true;
			else if(f==0.0f)
				out[i]=false;
			else
				out[out.length]=false;
		}
		return out;
	}
}
