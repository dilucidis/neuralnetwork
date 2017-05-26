package neurons;

import java.util.HashMap;
import java.util.Map;

public class Perceptron extends Learnon{
	private static int num = 0; //running count of perceptrons
	// if weighted inputs sum to this value, fire
	double threshold;
	
	HashMap<Neuron, Double> best_weights; //learning is in progress, this is for the least error function
	
	public Perceptron(){
		super();
		threshold= 1.0f; //TODO support other thresholds?
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
		best_weights =  new HashMap<Neuron, Double>(p.getBestWeights());
		num++;
	}
	//static method for perceptron pop. count
	public static int getNum(){
		return num;
	}
	public static void resetNum(){
		num=0;
	}

	//crucially, addInput does not deep copy I; when I updates, inputs will as well
	public void addInput(Neuron I) {
		inputs_and_weights.put(I, defaultWeight);
		best_weights.put(I, defaultWeight);
	}
	
	public double getThreshold(){
		return threshold;
	}
	

	public HashMap<Neuron, Double> getBestWeights(){
		return best_weights;
	}

	@Override
	public float valueFunction(float sum) {
		return (sum >= threshold) ? 1.0f : 0;
	}
	
}
