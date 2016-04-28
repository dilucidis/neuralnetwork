package perceptual_network;

import neurons.*;

public class InputLayer extends Layer {
	private boolean[] currentDataSet;

	public InputLayer(int size) {
		super(size);
		currentDataSet= new boolean[size];
		for (int i = 0; i<size; i++){
			super.bank[i] =  new InputNeuron(i);
		}
	}
	
	

}
