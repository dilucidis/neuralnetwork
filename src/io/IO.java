package io;

public class IO {
	//TODO consider replacing boolean arrays with floats
	private boolean[] input;
	private boolean[] output;
	private boolean outputGiven = false;
	
	public IO(int inputSize, int outputSize) {
		this.input  = new boolean[inputSize];
		this.output = new boolean[outputSize];
	}
	
	public IO(boolean[] input, boolean[] output){
		this(input.length, output.length);
		setInput(input);
		setOutput(output);
	}
	
	public IO(boolean[] input, int outputSize){
		this(input.length, outputSize);
		setInput(input);
	}
	//private because all IO pairs start with an unchanging input
	private void setInput(boolean[] input){
		for(int i = 0; i < input.length; i++)
			this.input[i]=input[i];
	}
	//public for the non-training IO pairs, where the network produces the output
	public void setOutput(boolean[] output){
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
	
	public boolean getInputValue(int x){
		return this.input[x];
	}
	
	public boolean getOutputValue(int y){
		if(outputGiven)
			return this.output[y];
		else
			throw new RuntimeException("no output given");
	}
	public boolean[] getOutput(){
		return output;
	}
}
