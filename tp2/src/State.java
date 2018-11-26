import java.util.HashSet;
import java.util.LinkedHashSet;

public class State {
	
	private String m_value;
	private LinkedHashSet<Word> m_possibleWords = new LinkedHashSet<Word>();
	private HashSet<State> m_nextStates = new HashSet<State>();
	
	public State(String value)
	{
		m_value = value;
		
	}
}
