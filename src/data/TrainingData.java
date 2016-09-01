package data;

import io.IO;

import java.io.File;


public class TrainingData extends Data {

	public TrainingData(File text) {
		super(text);
		
		for(int i = 0; i < super.cases.length; i++)
			super.cases[i] = new IO(parseLine(sc.next()),
									parseLine(sc.next()) );
		sc.close();
		
	}

}
