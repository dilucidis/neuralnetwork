package testsuite;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

import data.Data;
import io.IO;
import perceptual_network.*;
import neurons.*;

import org.junit.Before;
import org.junit.Test;


public class Tester {

	/**
	 * @param args
	 */
	static final String path = "/Users/sparr/workspace/neuralnetwork/src/data/";
	static final String ANDpath = path + "ANDdataset.txt";
	static final String XORpath = path + "XORdataset.txt";
	@Before
	public void setUp(){
		reset();
	}
	
	public void reset(){
		Perceptron.resetNum();
		Neuron.resetNum();
		InnerLayer.resetNum();
		Layer.resetNum();
		InputNeuron.resetNum();
	}
	@Test
	public void testPerceptrons(){
		Perceptron p = new Perceptron();
		Perceptron c = new Perceptron(p);
		c.setDefaultWeight(1);
		c.addInput(p);
		assertTrue(p.checkFire()==0);
		assertTrue(c.checkFire()==0);
		assertEquals(c.getInputsAndWeights().size(),1);
		assertTrue(c.getInputsAndWeights().get(p)==1.0);
		c.update();
		assertTrue(c.checkFire()==0.0);
		p.fire(true);
		c.update();
		assertTrue(c.checkFire()==1.0);
		assertTrue(Perceptron.getNum()==2&&InputNeuron.getNum()==0);
		assertTrue(Neuron.getNum()==2);
		reset();
		
	}
	@Test
	public void testData(){
		Data test = new Data(new File(Tester.ANDpath));
		assertTrue(test.currentCaseNumber==0);
		assertTrue(test.casesLeft()==4);
		IO io = test.nextDataSet();
		assertTrue(io.getInputLength()==3);
		for(int i = 0; i<io.getInputLength();i++)
			assertTrue(io.getInputValue(i));
		assertTrue(io.getOutputLength()==1);
		assertTrue(io.getOutputValue(0));
	}
	@Test
	public void testInputNeurons(){
//		IO simpleCase = new IO(new boolean[]{true},0);
		InputNeuron in = new InputNeuron();
		in.readValue(true);
		//as the neuron has not been updated yet
		assertTrue(in.checkFire()==0.0f);
		in.update();
		assertTrue(in.checkFire()==1.0f);
		in.readValue(false);
		assertTrue(in.checkFire()==1.0f);
		in.update();
		assertTrue(in.checkFire()==0.0f);
		reset();
	}
	
	@Test
	public void testLayerCount(){
			Layer[] layers = new Layer[5];
			for (int i = 0; i<layers.length;i++)
				layers[i] = new InnerLayer();
			assertTrue(Layer.getNum()==5);
			assertTrue(InnerLayer.getNum()==5);
			reset();
	}
	@Test
	public void testIO(){
		IO test = new IO( new boolean[]{true,true,false}, new boolean[]{false,true,true} );
			assertTrue(test.getInputValue(0));
			assertTrue(test.getInputValue(1));
			assertTrue(!test.getInputValue(2));
			
			assertTrue(!test.getOutputValue(0));
			assertTrue(test.getOutputValue(1));
			assertTrue(test.getOutputValue(2));
	}
	@Test
	public void testInputLayer(){
		//new input of True, output of size 1
		IO inputs = new IO(new boolean[]{true}, 1);
		//2 layer network of length 1 neuron per layer to run on this input
		//create input layer
		InputLayer ilayer = new InputLayer(1);
		//feed in the input
		ilayer.feedValues(inputs);
		assertTrue(ilayer.length()==1);
		ilayer.update();
		assertTrue(Arrays.equals(ilayer.checkfire(), new float[]{1.0f}));
	
	}
	@Test
	public void testInnerLayerConnectivity(){
		InnerLayer[] layers = new InnerLayer[2];
		Perceptron alwaysOn = new Perceptron();
		alwaysOn.fire(true);
		layers[0] = new InnerLayer(1, Perceptron.class);
		layers[0].setNeurons(new Perceptron[]{alwaysOn});
		assertTrue(Arrays.equals(layers[0].checkfire(), new float[]{1.0f}));
		layers[1] = new InnerLayer(1, Perceptron.class);
		((Perceptron) layers[1].grabAxon(0)).setDefaultWeight(2.0);
		layers[1].wireAllAxons(layers[0]);
		assertTrue(Arrays.equals(layers[1].checkfire(), new float[]{0.0f}));
		layers[1].update();
		assertTrue(Arrays.equals(layers[1].checkfire(), new float[]{1.0f}));
		
	}
	@Test
	public void testOutputLayer(){
		OutputLayer olay = new OutputLayer(1, Perceptron.class);
		Perceptron alwaysOn = new Perceptron();
		alwaysOn.fire(true);
		olay.setNeuron(0, alwaysOn);
		assertTrue(Arrays.equals(olay.output(), new boolean[]{true}));
		
	}
	@Test
	public void testPerceptronNetwork1(){
		//start off the config
		Config c = new Config();
		//use perceptrons for the inner layers
		c.setNeuronTypes(Perceptron.class);
		//set the layer lengths to 1
		c.setAllLayersLength(1);
		//make it a 102 layer network
		c.setNumberOfHiddenLayers(100);
		//this is sort of a cumbersome structure, kind of unfortunate
		//but at the same time, more likely to have a few large layers
		//then many small ones like this weird network
		float[][] innerweights = new float[100][1];
		//silly loop filling it with 1.0f
		for (int i = 0; i < innerweights.length; i++)
			innerweights[i][0] = 1.0f;
		
		float[] outputweight = new float[]{1.0f};
		//cumbersome way to set default weights, but here you have it
		c.setInitialWeights(innerweights, outputweight);
		Network line = new Network(c);
		boolean[] singleTrue = new boolean[]{true};
		IO input = new IO(singleTrue, 1);
		
		line.setInput(input);
		line.run();
		
		assertTrue(Arrays.equals(line.getOutput(), singleTrue));
	}
	//hand built 1-hiddenlayer perceptron network to solve the infamous
	//XOR problem
	private Network setUpXORNetwork(){
		Network n;
		Config c = new Config();
		Data d = new Data(new File(Tester.XORpath));
		c.setInputLayerLength(3);
		c.setHiddenLayerLength(2);
		c.setOutputLayerLength(1);
		c.setNumberOfHiddenLayers(1);
		c.setNeuronTypes(Perceptron.class);
		c.setData(d);
		n = new Network(c);
		HashMap<Neuron, Double> newWeights = new HashMap<>();
		newWeights.put(n.getNeuron(0, 1), .75);
		newWeights.put(n.getNeuron(0, 2), .75);
		newWeights.put(n.getNeuron(0, 0), 0.);
		n.setSingleLearnonCustomWeights(1, 0, newWeights);
		((Perceptron)n.getNeuron(1, 0)).setBias(-.5f);
		newWeights = new HashMap<>();
		newWeights.put(n.getNeuron(0, 1), -.75);
		newWeights.put(n.getNeuron(0, 2), -.75);
		newWeights.put(n.getNeuron(0, 0), 0.);
		n.setSingleLearnonCustomWeights(1,1, newWeights);
		((Perceptron)n.getNeuron(1, 1)).setBias(1f);
		newWeights = new HashMap<>();
		newWeights.put(n.getNeuron(1, 0), .75);
		newWeights.put(n.getNeuron(1, 1), .75);
		n.setSingleLearnonCustomWeights(2,0, newWeights);
		return n;
	}
	
	private Network setUpANDNetwork(){
		Data newData = new Data(new File(Tester.ANDpath));
		Config c = new Config();
		c.setNeuronTypes(Perceptron.class);
		c.setData(newData);
		c.setAllLayersLength(3);
		//overriding the previously set length of 3 for the output
		c.setOutputLayerLength(1);
		c.setNumberOfHiddenLayers(1);
		
		return new Network(c);
	}
	/*
	 * ANDdataset.txt has 4 cases:
	 * 111 : 1
	 * 110 : 0
	 * 101 : 0
	 * 100 : 0
	 * The dataset is checking if the network can fire for
	 * the AND of the latter two values
	 * the first value is a bias input
	 */
	@Test
	//the first case is 11: so ANDwork should give true
	public void testPerceptronNetwork2(){
		Network ANDwork = this.setUpANDNetwork();
		ANDwork.run();
		assertTrue(Arrays.equals(ANDwork.getOutput(), new boolean[]{true}));
		
		reset();
	}
	@Test
	//the rest of the cases aren't 11, so it should give false
	public void testPerceptronNetwork3(){
		Network ANDwork = this.setUpANDNetwork();
		int inputCount = 0;
		ANDwork.pass();
		
		while(ANDwork.dataLeft()){
			ANDwork.run();
			assertTrue(Arrays.equals(ANDwork.getOutput(), new boolean[]{false}));
			inputCount++;
		}
		
		assertTrue(inputCount==3);
		reset();
	}
	@Test
	//testing XOR this time
	public void testPerceptronNetwork4(){
		Network XORwork = setUpXORNetwork();
		boolean[] results = new boolean[4];
		int testnum = 0;
		while(XORwork.dataLeft()){
			XORwork.run();
			results[testnum] = XORwork.getOutput(0);
			testnum++;
		}
		assertTrue(Arrays.equals(results, new boolean[]{false, true, true, false}));
	}
	@Test
	public void testNetworkDataLeftAndOverrunCount(){
		Network ANDwork = this.setUpANDNetwork();
		int inputCount = 0;
		
		while(ANDwork.dataLeft()){
			ANDwork.pass();
			inputCount++;
		}
		
		assertTrue(inputCount==4);
		assertTrue(ANDwork.getOverrunCount()==0);
		
		for(int i = 1; i<=4;i++){
			ANDwork.run();
			assertTrue(ANDwork.getOverrunCount()==i);
		}
		
		reset(); 
	}
	@Test
	public void testInnerLayerConstructorTypeException(){
		String exceptionMessage = "";
		Config c = new Config();
		c.setAllLayersLength(1);
		
		c.setNumberOfHiddenLayers(0);
		c.setNeuronTypes(Teston.class);
		try{
			Network n = new Network(c);
		}catch(Exception e){
			exceptionMessage = e.getMessage();
		}
		assertTrue(exceptionMessage.equals("used wrong type for innerlayer"));
	}
	
}
