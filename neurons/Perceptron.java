package neurons;

import java.util.HashMap;
import java.util.Map;

public class Perceptron extends Neuron{
	private static int num = 0; //running count of perceptrons
	// if weighted inputs sum to this value, fire
	double threshold;
	private double defaultWeight = 0.34; //TODO randomize initial weights
	private double learningRate = 0.1;
	
	HashMap<Neuron, Double> inputs_and_weights; //the neuronal input and the weight values 
	HashMap<Neuron, Double> best_weights; //learning is in progress, this is for the least error function
	
	public Perceptron(){
		super();
		threshold= 1.0f; //TODO support other thresholds?
		inputs_and_weights = new HashMap<Neuron, Double>();
		best_weights = new HashMap<Neuron, Double>();
		num++;
	}
	
	public Perceptron(Neuron[] inputs) {
		this(); //call the other constructor
		addInputs(inputs); //finish initialization with wiring of the inputs
	}
	//perceptron copy constructor (for deep copy capability)
	public Perceptron(Perceptron p){
		super(p); //call the copy constructor in class Neuron 
		threshold= p.getThreshold(); //finish the perceptron only business
		inputs_and_weights= new HashMap<Neuron, Double>(p.getInputsAndWeights()); //deep copy of hashmap
		best_weights =  new HashMap<Neuron, Double>(p.getBestWeights());
		num++;
	}
	//static method for perceptron pop. count
	public static int getNum(){
		return num;
	}
	//update is the sum function which determines neuronal activity (fire or not fire)
	public void update() {
		resetFire();
		int sum = 0;
		//summation of input neuron fire value*weight
		for (Map.Entry<Neuron, Double> entry : inputs_and_weights.entrySet())
				sum += entry.getValue();
		//compare sum to threshold, if it exceeds, fire. Else, keep the default
		if (sum >= threshold)
			super.manualFire(1.0f);
	}
	//crucially, addInput does not deep copy I; when I updates, inputs will as well
	public void addInput(Neuron I) {
		inputs_and_weights.put(I, defaultWeight);
		best_weights.put(I, defaultWeight);
	}
	
	public void addInputs(Neuron[] I){
		for( Neuron p : I)
			addInput(p);
	}
	
	public double getThreshold(){
		return threshold;
	}
	
	public double getDefaultWeight(){
		return defaultWeight;
	}
	
	public double setDefaultWeight(double newDW){
		double temp = defaultWeight;
		defaultWeight = newDW;
		return temp;
	}
	
	public double getLearningRate(){
		return learningRate;
	}
	
	public double setLearningRate(double newLR){
		double temp = learningRate;
		learningRate= newLR;
		return temp;
	}
	
	public HashMap<Neuron, Double> getInputsAndWeights(){
		return inputs_and_weights;
	}
	
	public HashMap<Neuron, Double> getBestWeights(){
		return best_weights;
	}
	
}
