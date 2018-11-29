import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Automaton {
	
	private State m_root = new State("");
	private HashSet<String> m_statesList = new HashSet<String>();
	private HashSet<String> m_words = new HashSet<String>(); // final states
	private ArrayDeque<String> m_mostRecentWords = new ArrayDeque<String>(5);
	
	//private State m_currentState = m_root;
	
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
	
	public State getStateFromValue(String value) {
		return m_root.findStateFromValue(value);
	}
	
	// Method used specifically when we want to know if the last word written is
	// a word in the lexicon (in GUI). The word is then "recognized", and the labels
	// are updated.
	public void addToLastUsedWords(String lastUsedWord) {
		// polls and removes recent state
		if (m_mostRecentWords.size() == 5) {
			String wordPolled = m_mostRecentWords.poll();
			getStateFromValue(wordPolled).setIsRecent(0);
		}
		m_mostRecentWords.add(lastUsedWord);
		this.getStateFromValue(lastUsedWord).setIsRecent(1);
		// pas le meilleur endroit pour le mettre
		this.getStateFromValue(lastUsedWord).incrementTimesUsed();
		
	}
	
	
	public HashSet<String> getLexiconWords() {
		return m_words;
	}
	
	public ArrayDeque<String> getMostRecentWords(){
		return m_mostRecentWords;
	}
	/*
	 * 	
	public State getCurrentState() {
		return m_currentState;
	}
	
	public void nextState(char chr) {
		String previousValue = m_currentState.getValue();
		for(State state : m_currentState.getNextStates()) {
			if((m_currentState.getValue() + chr).equals(state.getValue())){
				m_currentState = state;
			}
		}
		
		if(m_currentState.getValue().equals(previousValue)){
			
		}
	}
	
	public void previousState() {
		if(m_currentState != m_root) {
			m_currentState = this.getStateFromValue(m_currentState.getValue().substring(0, m_currentState.getValue().length() - 1));
		}
	}
	
	//idk encore pour linkedlist, well see
	public LinkedList<State> getCurrentStatePossibleWords() {
		return new LinkedList<State>();
	}
	
	public void reinitializeState() {
		m_currentState = m_root;
	}*/
}
