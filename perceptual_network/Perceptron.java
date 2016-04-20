package perceptual_network;

import java.util.HashMap;
import java.util.Map;
import interfaces.*;

public class Perceptron implements Updateable{
	// if weighted inputs sum to this value, fire
	public static int num = 0;
	double threshold;
	private boolean fire;
	private double defaultWeight = 0.34;
	private double learningRate = 0.1;
	HashMap<Perceptron, Double> inputs_and_weights;

	public Perceptron(){
		threshold= 1.0;
		inputs_and_weights = new HashMap<Perceptron, Double>();
		fire = false;
		num++;
	}
	
	public Perceptron(Perceptron[] inputs) {
		//for you nerds, what 'this()' does is call the other constructor
		this();
		addInputs(inputs);
	}
	//copy constructor (pls work)
	public Perceptron(Perceptron p){
		threshold= p.getThreshold();
		inputs_and_weights=p.getInputsAndWeights();
		fire = false;
		num++;
	}
	
	public void update() {
		fire = false;
		int sum = 0;
		
		for (Map.Entry<Perceptron, Double> entry : inputs_and_weights.entrySet())
			if (entry.getKey().checkFire()) 
				sum += entry.getValue();
		
		if (sum >= threshold)
			fire = true;
	}

	public boolean checkFire() {
		return fire;
	}

	public boolean resetFire() {
		return manualFire(false);
	}

	public boolean manualFire(boolean active) {
		boolean temp = fire;
		fire = active;
		return temp;
	}

	public void addInput(Perceptron I) {
		inputs_and_weights.put(I, defaultWeight);
	}
	
	public void addInputs(Perceptron[] I){
		for( Perceptron p : I)
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
	
	public HashMap<Perceptron, Double> getInputsAndWeights(){
		return inputs_and_weights;
	}

	
}
