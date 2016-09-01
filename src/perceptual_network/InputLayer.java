package perceptual_network;

import neurons.*;

public class InputLayer extends Layer {

	//inputs to be moved to network
	public InputLayer(int size, Network n) {
		super(size);
		for (int i = 0; i<size; i++){
			super.bank[i] =  new InputNeuron(i, n);
		}
	}
	
	

}
