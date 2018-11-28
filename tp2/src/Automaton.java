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
	
	private State currentState = m_root;
	
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
						State newState = new State(strToAdd);
						m_root.addNextState(newState);
						
						//if the state's value is the complete word, then it is a final state
						if(newState.getValue().length() == word.length()) {
							newState.setIsWord(true);
						}
						
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
