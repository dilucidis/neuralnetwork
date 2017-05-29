package neurons;


public class InputNeuron extends Neuron{
	private static int num = 0;
	//store inputs here
	private float lastReadValue;
	
	public InputNeuron() {
		super();
		num++;
	}

	//use this method to feed IO inputs into the network
	public void readValue(float input){
		lastReadValue = input;
	}
	
	public void readValue(boolean input){
		lastReadValue = input ? 1.0f : 0.0f;
	}
	
	public static int getNum(){
		return num;
	}
	
	public static void resetNum(){
		num=0;
	}
	//this should only be used for input neurons to communicate
	//does this work?
	//update for this neuron will read the input boolean array, and fire accordingly.
	public void update() {
		super.resetFire();
		//manualFire converts {True,False} to {1.0f, 0.0f} automatically
		super.fire(lastReadValue);
	}
}
