package perceptual_network;


import java.util.HashMap;

import data.Data;

import interfaces.Updateable;
import io.IO;
import neurons.Neuron;

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
		configureInputLayer(c);
		//init the innerlayers
		configureInnerLayers(c);
		//init the last layer as an outputlayer
		configureOuterLayer(c);
		//if the config includes a data file, read it in
		if(c.getData()!=null)
			this.data = c.getData();
	}
	
	private void configureInputLayer(Config c){
		layers[0] = new InputLayer(c.getInputLayerLength());
	}
	
	private void configureInnerLayers(Config c){
		boolean manualweights = false;
		if(c.getInnerWeights()!=null)
			manualweights=true;
		for(int i = 1;  i<=c.getNumberOfHiddenLayers(); i++){
			layers[i] = new InnerLayer(c.getHiddenLayerLength(), c.getNeuronType());
			if(manualweights)
				((InnerLayer)layers[i]).setDefaultWeights(c.getInnerWeights()[i-1]);
			else if(c.isRandomweights())
				((InnerLayer)layers[i]).setAllRandom();
			((InnerLayer)layers[i]).wireAllAxons(layers[i-1]);
		}
	}

	private void configureOuterLayer(Config c){
		boolean manualWeights = false;
		if(c.getOutputWeights()!=null)
			manualWeights = true;
		layers[layers.length-1] = new OutputLayer(c.getOutputLayerLength(), c.getNeuronType());
		//set output layer weights if manual or random (otherwise uses some defaults)
		if(manualWeights)
			((InnerLayer)layers[layers.length-1]).setDefaultWeights(c.getOutputWeights());
		else if(c.isRandomweights())
			((InnerLayer)layers[layers.length-1]).setAllRandom();
		//wire it with the previous layer
		((OutputLayer)layers[layers.length-1]).wireAllAxons(layers[layers.length-2]);

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
	
	public Neuron getNeuron(int layernum, int numInLayer){
		return this.layers[layernum].grabAxon(numInLayer);
	}
	
	public void setSingleLearnonCustomWeights(int layernum, int num, 
								HashMap<Neuron, Double> newWeights){
		if(layernum==0)
			throw new RuntimeException("Cannot set custom input layer weights,"
									 + " as there are no input layer weights to set.");
		((InnerLayer)this.layers[layernum]).setSingleLearnonCustomWeights(num, newWeights);
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
