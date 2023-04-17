import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Functions as the Consumer
public class Searcher extends Thread {
	SharedQueue Q;
	String pattern;
	int foundPatternCount;
	int lineCount;
	Reader reader;
	
	public Searcher(SharedQueue Q, String pattern, Reader reader) {
		this.Q = Q;
		this.pattern = pattern;
		this.reader = reader;
	}
	
	/*
	 * Fetches one line from Q, then searches the pattern in that line and accumulates the total number of occurences for the pattern
	 * it finds so far.
	 */
	public void run() {
		String queueElement = null;
		foundPatternCount = 0;
		lineCount = 0;
		
		while ( (queueElement = Q.dequeue()) != null) {
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(queueElement);
			while (m.find()) {
				foundPatternCount++;
			}
			if (reader.isFileEnd) { //I realize that this ending is super ugly
				break;
			}
		}
	}
}
