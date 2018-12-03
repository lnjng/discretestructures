/***
 * State class
 * 
 * @author dihn, huyen trang 1846776
 * 			jiang, helene 1854909
 *
 */

import java.util.HashSet;
import java.util.LinkedList;

public class State {
	
	private String m_value;
	private HashSet<State> m_nextStates = new HashSet<State>();
	private boolean m_isWord = false;
	private int m_timesUsed = 0;
	private int m_isRecent = 0;
	
	/***
	 * Constructor
	 * @param value
	 */
	public State(String value)
	{
		m_value = value;
	}
	
	/***
	 * returns value of m_value
	 * @return
	 */
	public String getValue()
	{
		return m_value;
	}
	
	/***
	 * sets the boolean attribute isWord to param isWord
	 * @param isWord
	 */
	public void setIsWord(boolean isWord) {
		m_isWord = isWord;
	}
	
	/***
	 * returns value of m_isWord
	 * @return
	 */
	public boolean getIsWord() {
		return m_isWord;
	}
	
	/***
	 * recursive method in which it adds param state to m_nextStates if it
	 * really is a next state of this
	 * @param state
	 */
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
	
	/***
	 * returns m_nextStates
	 * @return
	 */
	public HashSet<State> getNextStates() {
		return m_nextStates;
	}
	
	/***
	 * recursive method that finds a state with its
	 * String value and returns it
	 * @param value
	 * @return
	 */
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
	
	/***
	 * recursive method that finds all the possible complete words (final states)
	 * of a certain state. puts all those final states in a LinkedList
	 * @param possibleWords
	 */
	public void getPossibleWords(LinkedList<State> possibleWords){
		if(this.m_isWord) {
			possibleWords.add(this);
			
		}
		for(State state : m_nextStates) {
			state.getPossibleWords(possibleWords);
		}
	}
	
	/***
	 * increments m_timesUsed
	 */
	public void incrementTimesUsed() {
		m_timesUsed++;
	}
	
	/***
	 * returns m_timesUsed
	 * @return
	 */
	public int getTimesUsed() {
		return m_timesUsed;
	}
	
	/***
	 * sets m_isRecent with the value of param recent
	 * @param recent
	 */
	public void setIsRecent(int recent) {
		m_isRecent = recent;
	}
	
	/***
	 * returns value of m_isRecent
	 * @return
	 */
	public int getIsRecent() {
		return m_isRecent;
	}
}
