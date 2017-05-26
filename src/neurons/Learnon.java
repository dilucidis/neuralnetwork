package neurons;

import java.util.HashMap;
import java.util.Map;

import interfaces.Updateable;

public abstract class Learnon extends Neuron implements Updateable {

	HashMap<Neuron, Double> inputs_and_weights; //the neuronal input and the weight values 
	protected double defaultWeight = 0.34; //TODO randomize initial weights
	protected double learningRate = 0.1;

	public Learnon() {
		inputs_and_weights = new HashMap<Neuron, Double>();
	}

	public Learnon(Learnon n) {
		super(n);
		inputs_and_weights= new HashMap<Neuron, Double>(n.getInputsAndWeights()); //deep copy of hashmap
		defaultWeight = n.defaultWeight;
		learningRate = n.learningRate;
	}
	public Learnon(Neuron[] inputs){
		this(); //call the other constructor
		addInputs(inputs); //finish initialization with wiring of the inputs
	}
	//got to implement this differently depending on what kind of neuron you are
	public abstract float activationFunction(float x);
	//this better be correctly implemented as the derivative of the value function
	public abstract float derivativeActivationFunction(float x);
	
	@Override
	public void update() {
		super.resetFire();
		float sum = 0;
		
		for (Map.Entry<Neuron, Double> entry : inputs_and_weights.entrySet())
			sum += entry.getValue()*entry.getKey().checkFire();
		
		super.fire(activationFunction(sum));
	}

	public void addInput(Neuron I) {
		inputs_and_weights.put(I, defaultWeight);
	}
	
	public void addInputs(Neuron[] I){
		for( Neuron n : I)
			addInput(n);
	}
	
	public HashMap<Neuron, Double> getInputsAndWeights(){
		return inputs_and_weights;
	}

	public double getDefaultWeight(){
		return defaultWeight;
	}

	public void setDefaultWeight(double newDW){
		this.defaultWeight = newDW;
	}
	
	public double getLearningRate(){
		return learningRate;
	}
	
	public void setLearningRate(double newLR){
		this.learningRate = newLR;
	}
	
}
