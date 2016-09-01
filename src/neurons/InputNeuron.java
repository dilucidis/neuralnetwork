package neurons;

import perceptual_network.Network;


public class InputNeuron extends Neuron{
	private static int num = 0;
	//this should just be a reference, so it updates with everything else.
	//used to keep track of inputs
	//TODO make inputNeuron work with manual inputs so I can test the thing independent of network
	private Network n;
	private int index;
	
	public InputNeuron(int x, Network n) {
		super();
		index = x;
		this.n=n;
		num++;
	}
	//copy constructor, but change the index because why would you need two of these things
	//that will always be set to the same value. You wouldn't. So don't.
	//Honestly, there isn't much* reason to use this
//	public InputNeuron(InputNeuron I, int x) {
//		super(I);
//		index = x;
//		num++;
//	}
	//*any
	
	public static int getNum(){
		return num;
	}
	//this should only be used for input neurons to communicate
	//does this work?
	//update for this neuron will read the input boolean array, and fire accordingly.
	public void update() {
		resetFire();
		//manualFire converts {True,False} to {1.0f, 0.0f} automatically
		super.manualFire(n.getInput(index));
	}
}
