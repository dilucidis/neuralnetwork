package perceptual_network;


import data.Data;

import interfaces.Updateable;
import io.IO;

public class Network implements Updateable {
	private IO io;
	private boolean freshIO;
	private boolean[] out;
	private Data data;
	private int overrunCount = 0;
	private Layer[] layers;
	
	public Network(Config c){
		//takes in a config file
		//the layers of the network: input + nh*hidden + output
		this.layers = new Layer[c.getNumberOfHiddenLayers()+2]; 
		//init the inputlayer
		layers[0] = new InputLayer(c.getInputLayerLength());
		boolean manualweights = false;
		if(c.getInnerWeights()!=null&&c.getOutputWeights()!=null)
			manualweights=true;
		//init the innerlayers, and wire them with the previous layer
		for(int i = 1;  i<=c.getNumberOfHiddenLayers(); i++){
			layers[i] = new InnerLayer(c.getHiddenLayerLength());
			if(manualweights)
				((InnerLayer)layers[i]).setDefaultWeights(c.getInnerWeights()[i-1]);
			((InnerLayer)layers[i]).wireAllAxons(layers[i-1]);
		}
		//init the last layer as an outputlayer
		layers[layers.length-1] = new OutputLayer(c.getOutputLayerLength());
		//set output layer weights
		if(manualweights)
			((InnerLayer)layers[layers.length-1]).setDefaultWeights(c.getOutputWeights());
		//wire it with the previous layer
		((OutputLayer)layers[layers.length-1]).wireAllAxons(layers[layers.length-2]);
		//if the config includes a data file, read it in
		if(c.getData()!=null)
			this.data = c.getData();
	}

	
	public void update() {
		//check if IO has been fed manually, else read from dataset
		if(!freshIO)
			if(!dataLeft())
				throw new RuntimeException("no data left or manual IO means no input.");
			else
				this.setInput(data.nextDataSet());
		//actually give the network its food
		((InputLayer)this.layers[0]).feedValues(this.io);
		//run the network
		for( Layer l : layers)
			l.update();
		//read the output to out
		this.out = ((OutputLayer)layers[layers.length-1]).output();
		//mark the current IO as used
		freshIO = false;
	}
	//for use internally changing out io, but also for dataless testing
	public IO setInput(IO in){
		IO temp = this.io;
		this.io = in;
		//wouldn't wnat to toss out fresh data
		freshIO = true;
		return temp;
	}
	
	public void newData(Data data){
		this.data = data;
		//new data, so be be sure to read it
		freshIO = false;
	}
	
	public boolean getInput(int x){
		return io.getInputValue(x);
	}
	
	public boolean getIOOutput(int y){
		return io.getOutputValue(y);
	}
	
	public boolean getOutput(int y){
		return this.out[y];
	}
	
	public boolean[] getOutput(){
		return this.out;
	}
	
	public void run(){
		//actually run the network, but only if there is real data to work with
		if(freshIO||dataLeft())
			this.update();
		else 
			overrunCount++;
		//after each this.update() call, this.out should be updated
	}
	//toss out the next piece of data without setting it as input
	public void pass(){
		if(dataLeft())
			data.nextDataSet();
	}
	
	public boolean dataLeft(){
		return !(data==null||data.casesLeft()<=0);
	}
	
	public int getOverrunCount(){
		return overrunCount;
	}
	
	public void resetOverrunCount(){
		overrunCount = 0;
	}
}
