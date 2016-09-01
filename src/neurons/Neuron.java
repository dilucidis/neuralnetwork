package neurons;

import interfaces.*;

public abstract class Neuron implements Updateable{
	private static int num = 0;
	private float fireValue;

	public Neuron() {
		num++;
		this.fireValue = 0.0f;
	}
	
	public Neuron(Neuron n){
		this();
		this.fireValue = n.checkFire();
	}

	public static int getNum(){
		return num;
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
	public float manualFire(boolean zoo){
		if(zoo)
			return manualFire(1.0f);
		
		return manualFire(0.0f);
	}
}
