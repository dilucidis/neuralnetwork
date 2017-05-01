package perceptual_network;

import io.IO;
import neurons.*;

public class InputLayer extends Layer {

	public InputLayer(int size) {
		super(size);
		for (int i = 0; i<size; i++){
			super.bank[i] =  new InputNeuron();
		}
	}
	
	public void feedValues(IO inputs){
		if(inputs.getInputLength()!=super.bank.length)
			throw new RuntimeException("input length does not match input layer length");
		for(int i = 0; i < inputs.getInputLength();i++)
			((InputNeuron) super.bank[i]).readValue(inputs.getInputValue(i));
	}	
	
	

}
