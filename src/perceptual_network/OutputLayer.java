package perceptual_network;

import neurons.Learnon;

//Extending innerlayer is important: fully functional layer that also outputs
public class OutputLayer extends InnerLayer {

	public OutputLayer(int size, Class<? extends Learnon> cl) {
		super(size, cl);
	}
	
	public boolean[] output(){
		boolean[] out = new boolean[super.bank.length];
		
		for(int i = 0; i < out.length; i++)
			out[i] = (bank[i].checkFire()>=1.0f);
		
		return out;
	}
	public float[] fout(){
		float[] fout = new float[super.bank.length];
		for(int i = 0; i < fout.length; i++)
			fout[i] = bank[i].checkFire();
		return fout;
	}
	
	public void learn(float[] target){
		for(int i = 0; i < this.length(); i++)
			super.bank[i].learnAsOutputNeuron(target[i]);
	}
}
