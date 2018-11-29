import java.util.HashSet;
import java.util.LinkedHashSet;

public class State {
	
	private String m_value;
	//private LinkedHashSet<Word> m_possibleWords = new LinkedHashSet<Word>();
	private HashSet<State> m_nextStates = new HashSet<State>();
	private boolean m_isWord = false;
	
	public State(String value)
	{
		m_value = value;
	}
	
	public String getValue()
	{
		return m_value;
	}
	
	public void setIsWord(boolean isWord) {
		m_isWord = isWord;
	}
	
	public boolean getIsWord() {
		return m_isWord;
	}
	
	public void addNextState(State state) {
		
		for(State nextState : m_nextStates) {
			//compare le value du this state avec les this.value.length() premiers lettres de state.value
			if(state.getValue().substring(0, nextState.getValue().length()).equals(nextState.getValue())){
				nextState.addNextState(state);
			}
		}
		
		if(this.getValue().length() + 1 == state.getValue().length()) {
			this.m_nextStates.add(state);
		}

	}
	
	public HashSet<State> getNextStates() {
		return m_nextStates;
	}
	
	public State findStateFromValue(String value) {
		State stateWanted = null;
		if(this.getValue().equals(value)) {
			stateWanted = this;
		}
		else if(this.getValue().equals(value.substring(0, this.getValue().length()))) {
			for(State state : m_nextStates) {
				stateWanted = state.findStateFromValue(value);
				if(stateWanted != null)
					break;
			}
		}
		return stateWanted;
	}
}
