package neurons;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import interfaces.Updateable;
import perceptual_network.Layer;

public abstract class Learnon extends Neuron implements Updateable {

	HashMap<Neuron, Double> inputs_and_weights; //the neuronal input and the weight values 
	HashMap<Neuron, Double> inputs_and_deltas;
	protected double defaultWeight = 0.34; //TODO randomize initial weights
	protected double learningRate = 0.01;
	protected Random rand;
	protected long seed;
	protected boolean isRandom;
	
	protected Learnon() {
		inputs_and_weights = new HashMap<>();
		inputs_and_deltas = new HashMap<>();
		rand = new Random();
	}

	protected Learnon(Learnon n) {
		super(n);
		inputs_and_weights= new HashMap<Neuron, Double>(n.getInputsAndWeights()); //deep copy of hashmap
		inputs_and_deltas = new HashMap<>();
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
		inputs_and_deltas = new HashMap<>();
	}
	//got to implement this differently depending on what kind of neuron you are
	public abstract float activationFunction(float x);
	//this better be correctly implemented as the derivative of the value function
	public abstract float derivativeActivationFunction(float x);
	
	private double learnWeight(Neuron i, double OutputPartial){
		double partialErrorDerivativeWithRespectToOutput = OutputPartial;
		
		double partialOutputDerivativeWithRespectToSum =
				derivativeActivationFunction(sum());
		
		double storedDelta = 
				partialErrorDerivativeWithRespectToOutput*
				partialOutputDerivativeWithRespectToSum;
		double partialSumDerivativeWithRespectToWeight =
				i.checkFire();
		
		double partialErrorDerivativeWithRespectToWeight = 
				partialErrorDerivativeWithRespectToOutput*
				partialOutputDerivativeWithRespectToSum*
				partialSumDerivativeWithRespectToWeight;
				
		
		
		double prevWeight = this.inputs_and_weights.get(i);
		double n = this.learningRate;
		double dweight = -n*partialErrorDerivativeWithRespectToWeight;
		this.inputs_and_weights.put(i, prevWeight+dweight);
		return storedDelta;
	}
	
	public void learnAsOutputNeuron(float target){
		double outputPartial = 2*(this.checkFire()-target);
		for(Neuron n : this.inputs_and_weights.keySet())
			this.inputs_and_deltas.put(n, learnWeight(n, outputPartial));
		
	}
	
	public void learnAsInnerNeuron(Layer nextLayer){
		for(Neuron n : this.inputs_and_weights.keySet())
			this.inputs_and_deltas.put(n, learnAsInnerWeight(n, nextLayer));
	}

	
	private double learnAsInnerWeight(Neuron i, Layer nextLayer){
		
		double outputPartial = 0;
		double nextLayerWeight;
		double nextLayerDelta;
		for(int l = 0; l < nextLayer.length(); l++){
			Learnon nex = (Learnon)nextLayer.grabAxon(l);
			nextLayerWeight = nex.getWeight(this);
			nextLayerDelta = nex.getDelta(this);
			outputPartial +=nextLayerWeight*nextLayerDelta;
		}

		return learnWeight(i, outputPartial);
	}
	
	protected double getWeight(Neuron n){
		return this.inputs_and_weights.get(n);
	}
	
	protected double getDelta(Neuron n){
		return this.inputs_and_deltas.get(n);
	}
	public float sum(){
		float sum = 0;
		
		for (Map.Entry<Neuron, Double> entry : inputs_and_weights.entrySet())
			sum += entry.getValue()*entry.getKey().checkFire();
		
		return sum;
	}
	@Override
	public void update() {
		super.resetFire();
		
		
		super.fire(activationFunction(sum()));
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
		return (2*rand.nextDouble()-1)/10;
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
