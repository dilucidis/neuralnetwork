package neurons;

public class Perceptron extends Learnon{
	private static int num = 0; //running count of perceptrons
	// if weighted inputs sum to this value, fire
	double bias;
		
	public Perceptron(){
		super();
		//the bias is negative to limit firing rates
		bias= -1.0f; //TODO support other biases? 
		num++;
	}
	
	public Perceptron(float bias){
		this();
		this.bias = bias;
	}
	//perceptron copy constructor (for deep copy capability)
	public Perceptron(Perceptron p){
		super(p); //call the copy constructor in class Learnon 
		bias= p.getBias(); //finish the perceptron only business
		num++;
	}
	//static method for perceptron pop. count
	public static int getNum(){
		return num;
	}
	
	public static void resetNum(){
		num = 0;
	}

	
	public double getBias(){
		return bias;
	}
	
	public void setBias(float b){
		this.bias = b;
	}

	@Override
	public float activationFunction(float sum) {
		//simply enough, compare the sum to a threshold and fire
		return (sum+bias >= 0) ? 1.0f : 0;
	}

	@Override
	public float derivativeActivationFunction(float x) {
		return 0; //sadly correct
	}
	
}
