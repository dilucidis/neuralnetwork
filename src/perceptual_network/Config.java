package perceptual_network;

import data.Data;
import neurons.Learnon;

public class Config {
	//"builder" class for network
	private int num_hidden;
	private int input_layer_length;
	private int hidden_layer_length;
	private int output_layer_length;
	private float[][] initial_inner_weights;
	private float[] initial_output_weights;
	private Data data;
	private Class<? extends Learnon> learnonType;
	private boolean randomweights;
	
	public Config(){
		this.setNumberOfHiddenLayers(-1);
		this.setInputLayerLength(-1);
		this.setHiddenLayerLength(-1);
		this.setOutputLayerLength(-1);
		this.setRandomweights(false);
	}
	
	public void setNumberOfHiddenLayers(int n){
		num_hidden = n;
	}
	
	public void setAllLayersLength(int l){
		this.setInputLayerLength(l);
		this.setHiddenLayerLength(l);
		this.setOutputLayerLength(l);
	}
	
	public void setInputLayerLength(int l){
		input_layer_length = l;
	}
	
	public void setHiddenLayerLength(int l){
		hidden_layer_length = l;
	}
	
	public void setOutputLayerLength(int l){
		output_layer_length = l;
	}
	
	public void setInitialWeights(float[][] innerweights, float[] outputweights){
		initial_inner_weights = innerweights;
		initial_output_weights = outputweights;
	}
	
	public int getNumberOfHiddenLayers() {
		return num_hidden;
	}
	
	public int getInputLayerLength() {
		return input_layer_length;
	}
	
	public int getHiddenLayerLength() {
		return hidden_layer_length;
	}
	
	public int getOutputLayerLength() {
		return output_layer_length;
	}
	
	public float[][] getInnerWeights(){
		return initial_inner_weights;
	}
	
	public float[] getOutputWeights(){
		return initial_output_weights;
	}
	
	public Data getData() {
		return data;
	}
	
	public void setData(Data data) {
		this.data = data;
	}
	
	public void setNeuronTypes(Class<? extends Learnon> cl){
		this.learnonType = cl;
	}
	
	public Class<? extends Learnon> getNeuronType(){
		return this.learnonType;
	}

	public boolean isRandomweights() {
		return randomweights;
	}

	public void setRandomweights(boolean randomweights) {
		this.randomweights = randomweights;
	}
}
