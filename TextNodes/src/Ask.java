
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.ws.util.StringUtils;



public class Ask {
	public static void main(String[] args) {
		//create node system from file
		try {
			Node myNode = new Node(readFile("text"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//launch the GUI
		UserInterface.launch(UserInterface.class, args);
	
	
	}
	
	
	private static List<String> readFile(String filename) throws Exception {
		String line = null;
		List<String> records = new ArrayList<String>();

		// wrap a BufferedReader around FileReader
		BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));

		// use the readLine method of the BufferedReader to read one line at a
		// time.
		// the readLine method returns null when there is nothing else to read.
		while ((line = bufferedReader.readLine()) != null) {
			records.add(line);
		}

		// close the BufferedReader when we're done
		bufferedReader.close();
		return records;
	}

	
}
