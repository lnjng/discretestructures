import java.util.HashSet;

public class State {
	
	private String m_value;
	private HashSet<State> m_neighbouringStates;
	bool i
	
	public State(String letter, HashSet<State> neighbouringStates)
	{
		m_value = letter;
		m_neighbouringStates = neighbouringStates;
	}
}
