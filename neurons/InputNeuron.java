package neurons;

public class InputNeuron extends Neuron{
	private static int num = 0;
	//this should just be a reference, so it updates with everything else.
	private boolean[] dataset;
	//used to keep track of the relevant element in dataset
	private int index;
	
	public InputNeuron(int x) {
		super();
		index = x;
		num++;
	}
	//copy constructor, but change the index because why would you need two of these things
	//that will always be set to the same value. You wouldn't. So don't.
	//Honestly, there isn't much reason to use this
	public InputNeuron(InputNeuron I, int x) {
		super(I);
		index = x;
		num++;
		//this should be a reference copy, so if the original dataset changes they all do
		dataset=I.getDataset();
	}
	
	public static int getNum(){
		return num;
	}
	//this should only be used for input neurons to communicate
	protected boolean[] getDataset(){
		return dataset;
	}
	//if this isn't a reference copy, its in error. ds may be a deep copy of whatever is passed
	//in which case this won't work correctly
	//edit: looks like it works correctly.
	public void hookToDataset(boolean[] ds){
		dataset=ds;
	}	
	//update for this neuron will read the input boolean array, and fire accordingly.
	public void update() {
		resetFire();
		super.manualFire(dataset[index]);
	}
}
