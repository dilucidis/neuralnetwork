package data;

import io.IO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Data {
	protected Scanner sc;
	protected IO[] cases;
	public int currentCaseNumber = 0;
	public Data(File text) {
		try {
			sc = new Scanner(text);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int numOfCases = sc.nextInt();
		cases = new IO[numOfCases];
		for(int i = 0; i<cases.length;i++){
			cases[i] = new IO(parseLine(sc.next()),1);
			cases[i].setOutput(parseLine(sc.next()));
		}
			
		sc.close();
		
	}
	
	protected boolean[] parseLine(String line){
		boolean[] set = new boolean[line.length()];
		for(int i = 0; i < line.length();i++)
			set[i] = line.charAt(i)=='1';
		return set;
	}
	public IO nextDataSet(){
		currentCaseNumber++;
		if(currentCaseNumber<=cases.length)
			return cases[currentCaseNumber-1];
		else
			return null; //be careful with this: check if your dataset is null before you use it
	}
	public int casesLeft(){
		return cases.length-currentCaseNumber;
	}
	public int resetCaseNumber(){
		int temp = currentCaseNumber;
		currentCaseNumber = 0;
		return temp;
	}

}
