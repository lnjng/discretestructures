import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Automaton {
	
	private State m_root = new State("");
	// pour eviter la recursitevite
	private HashSet<String> m_statesList = new HashSet<String>();
	private HashSet<String> m_words = new HashSet<String>(); // final states
	private Queue<String> m_mostRecentWords = new LinkedList<String>();
	
	public Automaton()
	{
	}
	
	public void generateTreeFromFile(String fileName) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try 
		{
			for(String word = br.readLine(); word != null; word = br.readLine()) 
			{
				m_words.add(word);
				StringBuilder sb = new StringBuilder();
				for(int i = 0 ; i < word.length(); i++) 
				{
					sb.append(word.charAt(i));
					String strToAdd = sb.toString();
					if (!m_statesList.contains(strToAdd)) 
					{
						m_root.addNextState(new State(strToAdd));
						//System.out.println(sb.toString());
						m_statesList.add(strToAdd);
					}
				}
			}
		}
		catch(IOException e) 
		{
			throw e;
		}
		finally
		{
			br.close();
		}	
		
	}
	
	
}
