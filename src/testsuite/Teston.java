package testsuite;

import neurons.Learnon;
import neurons.Neuron;

public class Teston extends Learnon {

	private Teston() {
		// TODO Auto-generated constructor stub
	}
	public Teston(int x){
		this();
	}
	public Teston(Learnon n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	public Teston(Neuron[] inputs) {
		super(inputs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public float activationFunction(float x) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float derivativeActivationFunction(float x) {
		// TODO Auto-generated method stub
		return 0;
	}
}
