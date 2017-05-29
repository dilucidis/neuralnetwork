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
	
	public static void resetNum(){
		num = 0;
	}

	public float checkFire() {
		return this.fireValue;
	}
	//resetFire() is the samething as fire(0) or fire(false)
	public float resetFire() {
		return fire(0);
	}
	//basically setFireValue by another name
	public float fire(float active) {
		float temp = this.fireValue;
		this.fireValue = active;
		return temp;
	}
	//turn booleans to floats then just manualFire on the float
	public float fire(boolean zoo){
		return fire(zoo ? 1.0f : 0.0f);
	}
}
