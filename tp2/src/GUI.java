import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

public class GUI extends JFrame implements KeyListener {
	
	
	
	  //private JPanel panel;
	private JMenuBar menuBar;
	private JTextArea textArea;
	private JScrollPane scrollTextPane;
	private JTextField  possibleWordsPane;
	private JScrollPane scrollPossibleWordsPane;
	private Automaton automaton;
	  
	public GUI(Automaton lex){		  
		super("Éditeur de texte");
		setSize(750, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container = this.getContentPane();
		container.setLayout(new BorderLayout());
		//panel = new JPanel();
		//container.add(panel);
		  
		textArea = new JTextArea();
		textArea.setFont(new Font("Arial", 0, 24));
		textArea.setLineWrap(true);
		textArea.setFocusable(true);
		textArea.addKeyListener(this);
		scrollTextPane = new JScrollPane(textArea);
		container.add(scrollTextPane);
		  
		//oo boi
		//InputMap inputSpaceBar = new InputMap();
		  
		possibleWordsPane = new JTextField("mots possibles ici");
		possibleWordsPane.setFont(new Font("Arial",0, 22));
		possibleWordsPane.setLayout(new GridLayout(0,1));
		possibleWordsPane.setEditable(false);
		scrollPossibleWordsPane = new JScrollPane(possibleWordsPane);
		scrollPossibleWordsPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0,0));
		container.add(scrollPossibleWordsPane, BorderLayout.SOUTH);
		  
		
        JButton button = new JButton();
        button.setText("Trouver les labels d'un mot");
        JOptionPane pu = new JOptionPane();
        button.addActionListener(new java.awt.event.ActionListener() {
	        @Override
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            String wordWanted = pu.showInputDialog(container,
	                    "Entrez le mot", null);
	            showLabels(wordWanted);
	            
	        }
        });
        
        container.add(button, BorderLayout.NORTH);
		automaton = lex;
		this.setVisible(true);
		  
	}
	
	//////
	public void showLabels(String word) {
		JFrame frame = new JFrame("Input Dialog Example 3");
		State stateWord = automaton.getStateFromValue(word);
	    JOptionPane.showMessageDialog(frame, 
	        "Nombre de fois utilise : " + stateWord.getTimesUsed() + " \n Recemment utilise : " + stateWord.getIsRecent(),
	        "Pour le mot " + word,
	        JOptionPane.QUESTION_MESSAGE, 
	        null);
	}
	//////
	public void keyTyped(KeyEvent e) {
		/*if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
			automaton.previousState();
			
		}
		else if(e.getKeyChar() == ' ' || e.getKeyChar() == ','){
			automaton.reinitializeState();
		}
		else {
			automaton.nextState(e.getKeyChar());
			//showPossibleWords(automaton.getCurrentStatePossibleWords());
		}*/
									
					
	}
	/** Handle the key-pressed event from the text field. */
	public void keyPressed(KeyEvent e) {

	}
	
	static void swapReferences(int[] array, int a, int b){
	     int x = array[a];
	     array[a] = array[b];
	     array[b] = x;

	 }
	

	
	/** Handle the key-released event from the text field. */
	public void keyReleased(KeyEvent e) {

		//split the text with " "
				String[] wordsSplitBySpace = textArea.getText().split(" ");
				//split the last string with "," to get the final word 
				String[] wordsSplitByComma = wordsSplitBySpace[wordsSplitBySpace.length - 1].split(",");
				//the last word is going to be the last one in the array
				String lastWord = wordsSplitByComma[wordsSplitByComma.length - 1];
				

				// if the the word is a recognized word
				if (automaton.getLexiconWords().contains(lastWord) && e.getKeyChar() != ',' && e.getKeyChar() != ' ') {
					
					// if the word is recent
					if (automaton.getMostRecentWords().contains(lastWord)) {
						//swapReferences(automaton.getMostRecentWords().peek(), ,automaton.getMostRecentWords())
						automaton.getStateFromValue(lastWord).incrementTimesUsed();
					}
					
					// if the word isn't recent
					else {
						automaton.addToLastUsedWords(lastWord);

						
					}
					
					//automaton.getStateFromValue(lastWord).incrementTimesUsed();
					
				}
				
				//if there is a word that is not "empty"
				if(lastWord.length() != 0) {
					showPossibleWords(lastWord);
				}
				//if it's empty, then show nothing
				else {
					possibleWordsPane.setText("");
				}
	}
	  
	public void showPossibleWords(String word) {
		//init the list of possible words
		LinkedList<State> possibleWords = new LinkedList<State>();
		//getting the state from the word passed in
		State stateWanted = automaton.getStateFromValue(word);
		//if the word is in the lexicon, then it isnt null
		if(stateWanted != null) {
			//get all the possible words from the stateWanted
			stateWanted.getPossibleWords(possibleWords);
			StringBuilder sb = new StringBuilder();
			for(State state : possibleWords) {
				sb.append(state.getValue());
				sb.append(" ");
			}
			possibleWordsPane.setText(sb.toString());
		}
		//if the word isnt in the lexicon, then show nothing
		else {
			possibleWordsPane.setText("");
		}
	}
	  
	public void actionPerformed(ActionEvent e) 
	{
	
	}
}
