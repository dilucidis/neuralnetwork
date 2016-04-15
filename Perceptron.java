package perceptual_network;

import java.util.HashMap;
import java.util.Map;

public class Perceptron {
	// if weighted inputs sum to this value, fire
	double threshold;
	private boolean fire;
	private final double defaultweight = 0.34;
	private double learningRate = 0.1;
	HashMap<Perceptron, Double> inputs_and_weights;

	public Perceptron(){
		threshold= 1.0;
		inputs_and_weights = new HashMap<Perceptron, Double>();
		fire = false;
	}
	
	public Perceptron(Perceptron[] inputs) {
		threshold = 1.0;
		inputs_and_weights = new HashMap<Perceptron, Double>();
		
		for (Perceptron p : inputs)
			addInput(p);
		
		fire = false;
	}
	
	public boolean activationFunction() {
		fire = false;
		int sum = 0;
		
		for (Map.Entry<Perceptron, Double> entry : inputs_and_weights.entrySet())
			if (entry.getKey().checkFire()) 
				sum += entry.getValue();
		
		if (sum >= threshold)
			fire = true;
		
		return fire;
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
		inputs_and_weights.put(I, defaultweight);
	}
}
