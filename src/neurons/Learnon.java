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
	//got to implement this differently depending on what kind of neuron you are
	public abstract float valueFunction(float sum);
	
	@Override
	public void update() {
		super.resetFire();
		float sum = 0;
		for (Map.Entry<Neuron, Double> entry : inputs_and_weights.entrySet())
			sum += entry.getValue()*entry.getKey().checkFire();
		super.manualFire(valueFunction(sum));
	}

	public double getDefaultWeight(){
		return defaultWeight;
	}

	
	public double setDefaultWeight(double newDW){
		double temp = defaultWeight;
		defaultWeight = newDW;
		return temp;
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
	
	public double getLearningRate(){
		return learningRate;
	}
	
	public double setLearningRate(double newLR){
		double temp = learningRate;
		learningRate= newLR;
		return temp;
	}
	
}
