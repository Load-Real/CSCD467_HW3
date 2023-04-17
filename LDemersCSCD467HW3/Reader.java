import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//Functions as the Producer
public class Reader extends Thread {
	//Constructor Vars
	SharedQueue Q;
	String fname;
	
	//Line-Related Vars
	String line;
	int lineCount = 0;
	
	//File-Related Vars
	FileReader fileReader;
	BufferedReader bufferedReader;
	
	//The variable of despair
	boolean isFileEnd = false;

	
	public Reader(SharedQueue Q, String fname) {
		this.Q = Q;
		this.fname = fname;
	}
	
	@Override
	public void run() {
		/*Read ONE LINE each time from the text file into a shared queue Q till the
		 * whole file has been read through
		 * 
		 * The size of Q is limited to MaxSize (by default 100 lines of strings)
		 */
		
		try {
			fileReader = new FileReader(fname);
			bufferedReader = new BufferedReader(fileReader);
			while ( (line = bufferedReader.readLine()) != null) {
				Q.enqueue(line);
				lineCount++;
			}
			isFileEnd = true;
			fileReader.close();
			bufferedReader.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
}
