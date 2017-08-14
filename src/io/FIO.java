package io;

public class FIO {
	//TODO consider replacing float arrays with floats
	private float[] input;
	private float[] output;
	private boolean outputGiven = false;
	
	public FIO(int inputSize, int outputSize) {
		this.input  = new float[inputSize];
		this.output = new float[outputSize];
	}
	
	public FIO(float[] input, float[] output){
		this(input.length, output.length);
		setInput(input);
		setOutput(output);
	}
	
	public FIO(float[] input, int outputSize){
		this(input.length, outputSize);
		setInput(input);
	}
	
	public FIO(IO io){
		input = new float[io.getInputLength()];
		output = new float[io.getOutputLength()];
		for(int i = 0; i < io.getInputLength(); i++)
			input[i] = io.getInputValue(i) ? 1.0f : 0;
		for(int i = 0; i < io.getOutputLength(); i++)
			output[i] = io.getOutputValue(i) ? 1.0f : 0;
	}
	//private because all IO pairs start with an unchanging input
	private void setInput(float[] input){
		for(int i = 0; i < input.length; i++)
			this.input[i]=input[i];
	}
	//public for the non-training IO pairs, where the network produces the output
	public void setOutput(float[] output){
		for(int i = 0; i < output.length; i++)
			this.output[i]=output[i];
		outputGiven = true;
	}
	
	public int getInputLength(){
		return input.length;
	}
	public int getOutputLength(){
		return output.length;
	}
	
	public float getInputValue(int x){
		return this.input[x];
	}
	
	public float getOutputValue(int y){
		if(outputGiven)
			return this.output[y];
		else
			throw new RuntimeException("no output given");
	}
	public float[] getOutput(){
		return output;
	}
}
