package neurons;

import interfaces.*;

public abstract class Neuron implements Updateable{
	private static int num = 0;
	private float fireValue;
	private ActivationFunction f;

	public Neuron() {
		num++;
		this.fireValue = 0.0;
	}
	
	public Neuron(Neuron n){
		this();
		this.fireValue = n.checkFire();
	}
	public Neuron(ActivationFunction f){
		this.f=ActivationFunction(f);
	}
	
	public static int getNum(){
		return num;
	}
	public void fire(){
		
	}
	public float checkFire() {
		return this.fireValue;
	}

	public float resetFire() {
		return manualFire(0);
	}

	public float manualFire(float active) {
		float temp = this.fireValue;
		this.fireValue = active;
		return temp;
	}
}
