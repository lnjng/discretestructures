import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class State {
	
	private String m_value;
	private HashSet<State> m_nextStates = new HashSet<State>();
	private boolean m_isWord = false;
	private int m_timesUsed = 0;
	private int m_isRecent = 0;
	
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
	
	public void getPossibleWords(LinkedList<State> possibleWords){
		if(this.m_isWord) {
			possibleWords.add(this);
			
		}
		for(State state : m_nextStates) {
			state.getPossibleWords(possibleWords);
		}
	}
	
	public void incrementTimesUsed() {
		m_timesUsed++;
	}
	
	public int getTimesUsed() {
		return m_timesUsed;
	}
	
	public void setIsRecent(int recent) {
		m_isRecent = recent;
	}
	
	public int getIsRecent() {
		return m_isRecent;
	}
}
