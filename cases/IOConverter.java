package cases;

import io.IO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;

public class IOConverter {
	private File in, out;
	private FileReader fr1,fr2;
	private BufferedReader br1,br2;
	private String inLine,outLine;
	private IO[] ioList;
	private boolean[] input,output;
	private LineNumberReader lnr;
	
	public IOConverter(String inLoc, String outLoc) throws IOException {
		lnr = new LineNumberReader(new FileReader(new File(inLoc)));
		lnr.skip(Long.MAX_VALUE); //skips to last character before Long.MAX_VALUE characters.
		in=new File(inLoc);
		out=new File(outLoc);
		ioList=new IO[lnr.getLineNumber()+1];
		
		try {
			fr1=new FileReader(in);
			br1=new BufferedReader(fr1);
			fr2=new FileReader(out);
			br2=new BufferedReader(fr2);
			for(int i=0;(inLine=br1.readLine())!=null||(outLine=br2.readLine())!=null;i++){
				convert(inLine,outLine,i);
			}
			br1.close(); br2.close();
			fr1.close(); fr2.close();
		}catch(FileNotFoundException e){
			System.err.println("FileNotFoundException: "+e.getMessage());
		}catch(IOException e){
			System.err.println("IOException (Problem reading file): "+e.getMessage());
		}
	}

	private void convert(String inLine2, String outLine2, int index) {
		input=new boolean[inLine2.length()];
		output=new boolean[outLine2.length()];
		for (int i=0;i<inLine2.length();i++)
			input[i]=(Integer.parseInt(""+inLine2.charAt(i))!=0);
		for (int i=0;i<outLine2.length();i++)
			output[i]=(Integer.parseInt(""+outLine2.charAt(i))!=0);
		ioList[index]=new IO(input, output);
	}
	
	public IO[] get(){
		return ioList;
	}
}
