package testsuite;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;

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
	static String path;
	
	@Before
	public void setUp(){
		setPath();
		reset();
	}
	public void setPath(){
		Tester.path = "/Users/sparr/workspace/neuralnetwork/src/data/datasets.txt";
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
		p.manualFire(true);
		c.update();
		assertTrue(c.checkFire()==1.0);
		assertTrue(Perceptron.getNum()==2&&InputNeuron.getNum()==0);
		assertTrue(Neuron.getNum()==2);
		reset();
		
	}
	@Test
	public void testData(){
		Data test = new Data(new File(Tester.path));
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
		alwaysOn.manualFire(true);
		layers[0] = new InnerLayer(1);
		layers[0].setNeurons(new Perceptron[]{alwaysOn});
		assertTrue(Arrays.equals(layers[0].checkfire(), new float[]{1.0f}));
		layers[1] = new InnerLayer(1);
		((Perceptron) layers[1].grabAxon(0)).setDefaultWeight(2.0);
		layers[1].wireAllAxons(layers[0]);
		assertTrue(Arrays.equals(layers[1].checkfire(), new float[]{0.0f}));
		layers[1].update();
		assertTrue(Arrays.equals(layers[1].checkfire(), new float[]{1.0f}));
		
	}
	@Test
	public void testOutputLayer(){
		OutputLayer olay = new OutputLayer(1);
		Perceptron alwaysOn = new Perceptron();
		alwaysOn.manualFire(true);
		olay.setNeuron(alwaysOn, 0);
		assertTrue(Arrays.equals(olay.output(), new boolean[]{true}));
		
	}
	@Test
	public void testNetwork1(){
		Config c = new Config();
		//simple network
		c.setAllLayersLength(1);
		c.setNumberOfHiddenLayers(100);
		//this is sort of a cumbersome structure, kind of unfortunate
		//but at the same time, more likely to have a few large layers
		//then many small ones like this weird network
		float[][] innerweights = new float[100][1];
		//silly loop filling it with 1.0f
		for (int i = 0; i < innerweights.length; i++){
			innerweights[i][0] = 1.0f;
		}
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
	
	private Network setUpSevenNetwork(){
		Data newData = new Data(new File(Tester.path));
		Config c = new Config();
		c.setData(newData);
		c.setAllLayersLength(3);
		c.setOutputLayerLength(1);
		c.setNumberOfHiddenLayers(1);
		Network New = new Network(c);
		return New;
	}
	
	@Test
	public void testNetwork2(){
		Network Sevenwork = this.setUpSevenNetwork();
		Sevenwork.run();
		assertTrue(Arrays.equals(Sevenwork.getOutput(), new boolean[]{true}));
		reset();
	}
	@Test
	public void testNetworkDataLeftAndOverrunCount(){
		Network Sevenwork = this.setUpSevenNetwork();
		int inputCount = 0;
		while(Sevenwork.dataLeft()){
			Sevenwork.pass();
			inputCount++;
		}
		assertTrue(inputCount==4);
		assertTrue(Sevenwork.getOverrunCount()==0);
		for(int i = 1; i<=4;i++){
			Sevenwork.run();
			System.out.println(Sevenwork.getOverrunCount());
			assertTrue(Sevenwork.getOverrunCount()==i);
		}
		
	}
	
}
