import java.io.*;
import java.util.Hashtable;
import java.util.Set;
import java.util.LinkedList;


public class WordCounter {

	public static String read(FileReader inFile) {
		StringBuilder string = new StringBuilder("");
		char c;
		try {
			while (inFile.ready()) {
				c = (char)inFile.read();
				if (c == '\n' || c == ' ' || c == '.' || c == ',' || c == '(' || c == ')' || c == '?')
					break;
				string.append(c);
			}
		} catch (IOException io){}

		return string.toString().toLowerCase();
	}

	public static String formatOutput(String s) {
		while (s.length() < 15)
			s = s + " ";
		return s;
	}

	public static void getCounts(String fileName) {
		try {
			FileReader inFile = new FileReader(fileName);
			FileWriter outFile = new FileWriter("outFile.out");
			
			Hashtable<String,Integer> table = new Hashtable<String,Integer>();
			String string;
			int max = 0;
			while (inFile.ready()) {
				string = read(inFile);
				if (table.containsKey(string)) {
					table.put(string, table.get(string)+1);
				} else {
					if (string.length() < 15)
						table.put(string, 1);
				}
				if (table.containsKey(string) && table.get(string) > max)
					max = table.get(string);

			}
			Set<String> keys = table.keySet();
			LinkedList<String>[] arr = new LinkedList[max];
			for (int i = 0; i < arr.length; i++)
				arr[i] = new LinkedList<String>();

			for (String s:keys) {
				arr[table.get(s)-1].add(s);
			}

			for (int i = 0; i < arr.length; i++)
				for (String s:arr[i])
					outFile.write(formatOutput(s) + (i+1) + "\n");
			
			inFile.close();
			outFile.close();
		} catch (IOException io) { System.out.println("File not found!");}
	}

	public static void main(String [] args) {
		if (args.length > 0)
			getCounts(args[0]);
	}
}