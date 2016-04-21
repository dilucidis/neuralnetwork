package neurons;

import interfaces.*;

public abstract class Neuron implements Updateable{
	private static int num = 0;
	private boolean fire;

	public Neuron() {
		num++;
		fire = false;
	}
	
	public Neuron(Neuron n){
		this();
		fire = n.checkFire();
	}
	
	public static int getNum(){
		return num;
	}
	
	public boolean checkFire() {
		return fire;
	}

	public boolean resetFire() {
		return manualFire(false);
	}

	public boolean manualFire(boolean active) {
		boolean temp = fire;
		fire = active;
		return temp;
	}

}
