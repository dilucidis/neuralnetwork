package perceptual_network;

import java.util.ArrayList;

import interfaces.Updateable;

public class Network implements Updateable {
	
	
	ArrayList<Layer> layers;
	
	public Network() {
		layers = new ArrayList<Layer>();
	}
	
	public Network(int numHidden, int layerlength){
		this();
		layers.add(new InputLayer(layerlength));
		for(int i = 1;  i<=numHidden; i++)
			layers.add(new InnerLayer(layerlength));
		
	}
	
	public void update() {
		for( Layer l : layers)
			l.update();
	}
	
}
