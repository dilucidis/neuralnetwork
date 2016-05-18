package testsuite;

import java.io.File;

import data.Data;
import io.IO;
import perceptual_network.*;
import neurons.*;

public class Tester {

	/**
	 * @param args
	 */
	private static void testPerceptrons(){
		Perceptron p = new Perceptron();
		Perceptron c = new Perceptron(p);
		c.setDefaultWeight(1);
		c.addInput(p);
		System.out.println("c inputs size: "+c.getInputsAndWeights().size());
		System.out.println("weight for p in c: "+c.getInputsAndWeights().get(p));
		c.update();
		System.out.println(c.checkFire());
		p.manualFire(true);
		c.update();
		System.out.println(c.checkFire());
		System.out.println(Perceptron.getNum()+"+"+InputNeuron.getNum());
		System.out.println(Neuron.getNum());
		System.out.println("dank");
	}
	
	private static void testNetwork(){
		Data newData = new Data(new File("C:\\Users\\Shane Parr\\git\\neuralnetwork\\data\\datasets.txt"));
		Network New = new Network(4, 0, newData);
		
		New.update();
	}
	
	private static void testInputNeurons(){
		//shit this all got real complicated real fast
//		IO simpleCase = new IO(new boolean[]{true},0);
		
		Network n = new Network();
		InputNeuron I = new InputNeuron(0, n);
		System.out.println(I.checkFire());
		//this is false, neuron hasn't updated yet and default is false
		I.update();
		System.out.println(I.checkFire());
		//the input neuron's input reads as true, so it fires after updating
//		simpleCase = new IO(new boolean[]{false} ,0);
		I.update();
		System.out.println(I.checkFire());
		//after changing the input array and updating, the neuron is no longer firing
		System.out.println("dank");
		//fucking success with all the shallow copy bullshit this is great
	}
	
	private static void testLayers(){
			Layer[] layers = new Layer[5];
			for (int i = 0; i<layers.length;i++)
				layers[i] = new InnerLayer();
			System.out.println(Layer.getNum());
			System.out.println(InnerLayer.getNum());
			System.out.println("dank");
		
	}
	
	private static void testIO(){
		IO test = new IO(new boolean[3],new boolean[3]);
		for(int i = 0; i < 3; i++)
			System.out.println(test.getInputValue(i));
	}
	
	private static void testData(){
		
		Data test = new Data(new File("C:\\Users\\Shane Parr\\git\\neuralnetwork\\data\\datasets.txt"));
		
	}
	private static void testAll(){
//		  System.out.println("Testing full functionality: ");
//		  Neuron[] raw;
//		  Data ANDFunctionTestData= new Data(new String(datatext.txt));
//		  raw = new Neuron[3];
//		  for(int i = 0; i<raw.length;i++)
//		  	raw[i] = new InputNeuron(i);
//		  InputLayer in = new InputLayer(raw);
//		  in.hookToDataset(ANDFunctionTestData.nextDataset());
	}
	public static void main(String[] args) {
	//testPerceptrons();
	//testInputNeurons();
	//testLayers();
	//testIO();
	//testData();
	  testNetwork();
	  
	  
	}

}
