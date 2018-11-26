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
	
	public String getValue()
	{
		return m_value;
	}
	
	public void addNextState(State state) {
		
		for(State nextState : m_nextStates) {
			//compare le value du this state avec les this.value.length() premiers lettres de state.value
			if((state.getValue().substring(0, nextState.getValue().length())).equals(nextState.getValue())){
				nextState.addNextState(state);
			}
		}
		
		if(this.getValue().length() + 1 == state.getValue().length()) {
			this.m_nextStates.add(state);
		}

	}
}