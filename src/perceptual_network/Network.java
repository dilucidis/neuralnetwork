package perceptual_network;


import data.Data;

import interfaces.Updateable;
import io.IO;

public class Network implements Updateable {
	private IO io;
	private boolean freshIO;
	private boolean[] out;
	private Data data;

	private Layer[] layers;
	
	public Network(Config c){
		//takes in a config file
		//the layers of the network: input + nh*hidden + output
		this.layers = new Layer[c.getNumberOfHiddenLayers()+2]; 
		//init the inputlayer
		layers[0] = new InputLayer(c.getInputLayerLength());
		//init the innerlayers, and wire them with the previous layer
		for(int i = 1;  i<=c.getNumberOfHiddenLayers(); i++){
			layers[i] = new InnerLayer(c.getHiddenLayerLength());
			((InnerLayer)layers[i]).wireAllAxons(layers[i-1]);
		}
		//init the last layer as an outputlayer
		layers[layers.length-1] = new OutputLayer(c.getOutputLayerLength());
		//wire it with the previous layer
		((OutputLayer)layers[layers.length-1]).wireAllAxons(layers[layers.length-2]);
		//if the config includes a data file, read it in
		if(c.getData()!=null)
			this.data = c.getData();
		//if there are manual weight settings:
		if(c.getInnerWeights()!=null&&c.getOutputWeights()!=null){
			//set inner weights
			for(int i = 1; i < layers.length-1; i++)
				((InnerLayer)layers[i]).setDefaultWeights(c.getInnerWeights()[i]);
			//set the output weights
			((InnerLayer)layers[layers.length-1]).setDefaultWeights(c.getOutputWeights());
		}
	}

	
	public void update() {
		//check if IO has been fed manually, else read from dataset
		if(!freshIO)
			if(data==null)
				throw new RuntimeException("null data and no manual IO");
			else
				setInput(data.nextDataSet());
		//actually give the network its food
		((InputLayer)this.layers[0]).feedValues(this.io);
		//run the network
		for( Layer l : layers)
			l.update();
		//read the output to out
		out = ((OutputLayer)layers[layers.length-1]).output();
		//mark the current IO as used
		freshIO = false;
	}
	public IO setInput(IO in){
		IO temp = this.io;
		this.io = in;
		//wouldn't wnat to toss out fresh data
		freshIO = true;
		return temp;
	}
	public boolean getInput(int x){
		return io.getInputValue(x);
	}
	public boolean getOutput(int y){
		return io.getOutputValue(y);
	}
	public boolean[] getOutput(){
		return this.out;
	}
	public void run(){
		//actually run the network
		this.update();
		//write to output
		this.out = ((OutputLayer) layers[layers.length-1]).output();
	}
}
