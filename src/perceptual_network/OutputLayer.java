package perceptual_network;
//Extending innerlayer is important: fully functional layer that also outputs
public class OutputLayer extends InnerLayer {


	public OutputLayer(int size) {
		super(size);
	}
	
	public boolean[] output(){
		boolean[] out = new boolean[super.bank.length];
		
		for(int i = 0; i<out.length;i++)
			if(bank[i].checkFire()>=1.0f)
				out[i]=true;
			else
				out[i]=false;
		
		return out;
	}
}
