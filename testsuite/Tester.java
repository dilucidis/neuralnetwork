package testsuite;

import perceptual_network.*;

public class Tester {

	/**
	 * @param args
	 */
	private static void testPerceptrons(){
		Perceptron p;
		p = new Perceptron();
		Perceptron p2 = p;
		Perceptron c;
		c = new Perceptron(p);
		p.setLearningRate(1);
		
		System.out.println(p.getLearningRate());
		System.out.println(p2.getLearningRate());
		System.out.println(c.getLearningRate());
		System.out.println(Perceptron.num);
		System.out.println("dank");
	}
	private static void testLayers(){
			Layer l = new InnerLayer();
			Layer[] layers = new Layer[5];
			for (int i = 0; i<layers.length;i++)
				layers[i] = new InnerLayer();
			System.out.println(Layer.num);
			System.out.println("dank");
		
	}
	public static void main(String[] args) {
	testPerceptrons();
	testLayers();
	}

}
