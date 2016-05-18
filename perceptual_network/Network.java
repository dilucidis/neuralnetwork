package perceptual_network;

import java.util.ArrayList;

import data.Data;

import interfaces.Updateable;
import io.IO;

public class Network implements Updateable {
	private IO io;
	//TODO add input implementation to this guy
	private boolean[] out;
	private Data data;
	private int layerlength;

	private ArrayList<Layer> layers;
	
	public Network() {
		layers = new ArrayList<Layer>();
	}
	
	public Network(int numHidden, int layerlength){
		this();
		this.layerlength=layerlength;
		layers.add(new InputLayer(layerlength, this));
		for(int i = 1;  i<=numHidden; i++)
			layers.add(new InnerLayer(layerlength));
		
	}
	public Network(int numHidden, int layerlength, Data data){
		this();
		this.data = data;
	}
	
	public void update() {
		io = data.nextDataSet();
		out = new boolean[this.layerlength];
		for( Layer l : layers)
			l.update();
		//layers.get(layers.size()-1);
	}
	public boolean getInput(int x){
		return io.getInputValue(x);
	}
	public boolean getOutput(int y){
		return io.getOutputValue(y);
	}
	
}
