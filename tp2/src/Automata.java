import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Automata {
	
	private State m_s0;
	private HashSet<String> m_words = new HashSet<String>(); // final states
	private Queue<String> m_mostRecentWords = new LinkedList<String>();
	
	public Automata(String lexiconFileName) throws IOException
	{
		setDataFromFile(lexiconFileName);
	}
	
	public void setDataFromFile(String fileName) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try 
		{
			for(String line = br.readLine(); line != null; line = br.readLine()) 
			{
				m_words.add(line);
			}
		}
		finally{
			br.close();
		}
		
		
	}
	
}
