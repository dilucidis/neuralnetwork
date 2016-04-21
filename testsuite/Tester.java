package testsuite;

import perceptual_network.*;
import neurons.*;

public class Tester {

	/**
	 * @param args
	 */
	private static void testNeurons(){
		Perceptron p = new Perceptron();
		Perceptron c = new Perceptron(p);
		Neuron n = new InputNeuron(0);
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
	
	private static void testInputNeurons(){
		boolean[] simpleCase = {true};
		InputNeuron I = new InputNeuron(0);
		I.hookToDataset(simpleCase);
		System.out.println(I.checkFire());
		//this is false, neuron hasn't updated yet and default is false
		I.update();
		System.out.println(I.checkFire());
		//the input neuron's input reads as true, so it fires after updating
		simpleCase[0] = false;
		I.update();
		System.out.println(I.checkFire());
		//after changing the input array and updating, the neuron is no longer firing
		System.out.println("dank");
		//fucking success with all the shallow copy bullshit this is great
	}
	
	private static void testLayers(){
			Layer l = new InputLayer();
			Layer[] layers = new Layer[5];
			for (int i = 0; i<layers.length;i++)
				layers[i] = new InnerLayer();
			System.out.println(Layer.getNum());
			System.out.println(InnerLayer.getNum());
			System.out.println("dank");
		
	}
	public static void main(String[] args) {
	//testNeurons();
	//testInputNeurons();
	testLayers();
	}

}