package testsuite;

import static org.junit.Assert.*;

import java.io.File;

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
	public void testNetwork(){
		Data newData = new Data(new File(Tester.path));
		Network New = new Network(1, 3, newData);
		New.run();
		for (boolean out : New.getOutput())
			assertTrue(out);
		reset();
	}
	
}
