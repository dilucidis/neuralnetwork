package cases;
import java.io.*;
public class DataProcessor {
	private static File out=new File("out.txt");
	private static FileWriter fw;
	private static BufferedWriter bw;
	
	private static void write(String move) {
		try{
			fw=new FileWriter(out);
			bw=new BufferedWriter(fw);
			bw.write(move);
			bw.newLine();
			bw.close();
			fw.close();
		}catch(IOException e){
			System.err.println("IOException (Problem writing to file): "+e.getMessage());
		}
	}

	public static void main(String[] args) {
		File in=new File("in.txt");
		FileReader fr; 
		BufferedReader br; 
		String line;
		Generator gen;
		
		try {
			fr=new FileReader(in);
			br=new BufferedReader(fr);
			while((line=br.readLine())!=null&&!(line=br.readLine()).equals("in")){
				gen=new Generator(line);
				write(gen.getOut());
			}
			br.close();
			fr.close();
		}catch(FileNotFoundException e){
			System.err.println("FileNotFoundException: "+e.getMessage());
		}catch(IOException e){
			System.err.println("IOException (Problem reading file): "+e.getMessage());
		}
	}

}
