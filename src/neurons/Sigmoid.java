package neurons;

public class Sigmoid extends Learnon {

	public float activationFunction(float x) {
		return (float)(1/(1+Math.exp(x)));
	}

	@Override
	public float derivativeActivationFunction(float x) {
		// TODO Auto-generated method stub
		return activationFunction(x)*(1-activationFunction(x));
	}

}
