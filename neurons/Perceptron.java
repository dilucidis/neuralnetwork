package neurons;

import java.util.HashMap;
import java.util.Map;

public class Perceptron extends Neuron{
	// if weighted inputs sum to this value, fire
	private static int num = 0;
	double threshold;
	private double defaultWeight = 0.34;
	private double learningRate = 0.1;
	
	HashMap<Neuron, Double> inputs_and_weights;
	HashMap<Neuron, Double> best_weights;
	
	public Perceptron(){
		super();
		threshold= 1.0;
		inputs_and_weights = new HashMap<Neuron, Double>();
		best_weights = new HashMap<Neuron, Double>();
		num++;
	}
	
	public Perceptron(Neuron[] inputs) {
		this();
		addInputs(inputs);
	}

	public Perceptron(Perceptron p){
		super(p);
		threshold= p.getThreshold();
		inputs_and_weights= new HashMap<Neuron, Double>(p.getInputsAndWeights());
		best_weights =  new HashMap<Neuron, Double>(p.getBestWeights());
		num++;
	}

	public static int getNum(){
		return num;
	}
	
	public void update() {
		resetFire();
		int sum = 0;
		
		for (Map.Entry<Neuron, Double> entry : inputs_and_weights.entrySet())
			if (entry.getKey().checkFire()) 
				sum += entry.getValue();
		
		if (sum >= threshold)
			super.manualFire(true);
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
