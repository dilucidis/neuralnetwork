package neurons;

public class ReLU extends Learnon {


	@Override
	public float activationFunction(float sum) {
		return (sum>0) ? sum : 0;
	}

	@Override
	public float derivativeActivationFunction(float sum) {
		return (sum>0) ? 1 : 0;
	}

}
