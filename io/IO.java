package io;

public class IO {
	private boolean[] input;
	private boolean[] output;
	
	public IO(int inputSize, int outputSize) {
		this.input = new boolean[inputSize];
		this.output = new boolean[outputSize];
	}
	
	public IO(boolean[] input, boolean[] output){
		this(input.length, output.length);
		setInput(input);
		setOutput(output);
	}
	
	private void setInput(boolean[] input){
		for(int i = 0; i < input.length; i++)
			this.input[i]=input[i];
	}
	
	private void setOutput(boolean[] output){
		for(int i = 0; i < output.length; i++)
			this.output[i]=output[i];
	}
	public boolean getInputValue(int x){
		return this.input[x];
	}
}
