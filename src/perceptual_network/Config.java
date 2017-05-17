package perceptual_network;

import data.Data;

public class Config {
	//"builder" class for network
	private int num_hidden;
	private int input_layer_length;
	private int hidden_layer_length;
	private int output_layer_length;
	private float[][] initial_inner_weights;
	private float[] initial_output_weights;
	private Data data;
	public Config(){
		setNumberOfHiddenLayers(-1);
		setInputLayerLength(-1);
		setHiddenLayerLength(-1);
		setOutputLayerLength(-1);
	}
	public void setNumberOfHiddenLayers(int n){
		num_hidden = n;
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
}
