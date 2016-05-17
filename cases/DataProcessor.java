package cases;
import java.io.*;
public class DataProcessor {
	private static File out=new File("src/cases/outDB.txt");
	private static FileWriter fw;
	private static BufferedWriter bw;
	private static PrintWriter pw;
	
	private static void write(String move) {
		try{
			fw=new FileWriter(out, true);
			bw=new BufferedWriter(fw);
			pw=new PrintWriter(bw);
			pw.print("\n"+move);
			pw.close();
			bw.close();
			fw.close();
		}catch(IOException e){
			System.err.println("IOException (Problem writing to file): "+e.getMessage());
		}
	}

	public static void main(String[] args) {
		File in=new File("src/cases/in.txt");
		File db=new File("src/cases/inDB.txt");
		FileReader fr; FileWriter w;
		BufferedReader br; BufferedWriter b;
		PrintWriter p;
		String line;
		Generator gen;
		
		try {
			fr=new FileReader(in);
			br=new BufferedReader(fr);
			w=new FileWriter(db, true);
			b=new BufferedWriter(w);
			p=new PrintWriter(b);
			while((line=br.readLine())!=null){
				p.print("\n"+line);
				gen=new Generator(line);
				write(gen.getOut());
			}p.close();
			br.close(); b.close();
			fr.close(); w.close();
		}catch(FileNotFoundException e){
			System.err.println("FileNotFoundException: "+e.getMessage());
		}catch(IOException e){
			System.err.println("IOException (Problem reading file): "+e.getMessage());
		}
	}

}
