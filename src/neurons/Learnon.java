package neurons;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import interfaces.Updateable;

public abstract class Learnon extends Neuron implements Updateable {

	HashMap<Neuron, Double> inputs_and_weights; //the neuronal input and the weight values 
	protected double defaultWeight = 0.34; //TODO randomize initial weights
	protected double learningRate = 0.1;
	protected Random rand;
	protected long seed;
	protected boolean isRandom;
	protected Learnon() {
		inputs_and_weights = new HashMap<Neuron, Double>();
		rand = new Random();
	}

	protected Learnon(Learnon n) {
		super(n);
		inputs_and_weights= new HashMap<Neuron, Double>(n.getInputsAndWeights()); //deep copy of hashmap
		defaultWeight = n.defaultWeight;
		learningRate = n.learningRate;
		isRandom = n.isRandom;
		if(isRandom){
			seed = n.seed;
		//this random is only guaranteed identity with the other if it hasn't been used yet
			rand = new Random(seed);
		}
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

	//crucially, addInput does not deep copy I; when I updates, inputs will as well
	public void addInput(Neuron I) {
		Double weight = this.isRandom ? randomWeight() : defaultWeight;
		this.inputs_and_weights.put(I, weight);
	}
	
	public void addInputs(Neuron[] I){
		for( Neuron n : I)
			addInput(n);
	}
	
	public void addInputsAndCustomWeights(HashMap<Neuron, Double> inputsAndWeights){
		this.inputs_and_weights.putAll(inputsAndWeights);
	}
	
	public HashMap<Neuron, Double> getInputsAndWeights(){
		return inputs_and_weights;
	}
	
	public void setRandom(boolean isRandom){
		this.isRandom = isRandom;
	}
	
	public void setRandomSeed(long seed){
		rand.setSeed(seed);
	}
	
	protected double randomWeight(){
		return rand.nextDouble();
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
