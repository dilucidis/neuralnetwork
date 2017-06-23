package perceptual_network;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import neurons.*;

public class InnerLayer extends Layer {
	private static int num = 0;
	protected Learnon[] bank;
	public InnerLayer(){
		super();
		num++;
	}
	//sigh watch this
	public InnerLayer(int size, Class<? extends Learnon> cl) {
		//this should fix things?
		super.bank = new Learnon[size]; 
		//enforce learnons in innerlayer
		bank = ((Learnon[])super.bank);
		try{
			Constructor<? extends Learnon> ctor = cl.getConstructor();
			for(int i = 0; i<size; i++)
				bank[i] = (Learnon)ctor.newInstance();
		}catch(NoSuchMethodException  | InvocationTargetException |
			   IllegalAccessException | InstantiationException | ClassCastException e){
			//e.printStackTrace();  for debugging purposes, uncomment this
			//if the below line is causing exceptions
			throw new RuntimeException("used wrong type for innerlayer");
			
		}
		num++;
	}
	
	public static int getNum(){
		return num;
	}
	
	public static void resetNum(){
		num=0;
	}
	
	public void wireAxon(Neuron p, int y){
		bank[y].addInput(p);
	}
	
	public void wireAxons(Layer l, int y){
		bank[y].addInputs(l.bank);
	}
	
	public void wireAllAxons(Layer l){
		for(int i = 0; i < bank.length; i++){
			this.wireAxons(l, i);
		}
	}
	public void setAllRandom(){
		for(int i = 0; i < bank.length; i++)
			bank[i].setRandom(true);
	}
	public void setDefaultWeights(float[] weights){
		for(int i = 0; i < weights.length; i++)
			bank[i].setDefaultWeight(weights[i]);
	}
	public void setSingleLearnonCustomWeights(int index, HashMap<Neuron, Double> newWeights){
		bank[index].addInputsAndCustomWeights(newWeights);
	}

}
