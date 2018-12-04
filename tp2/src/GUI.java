/***
 * GUI class
 * 
 * @author dihn, huyen trang 1846776
 * 			jiang, helene 1854909
 *
 */

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import javax.swing.*;

public class GUI extends JFrame implements KeyListener {
	
	private JMenuBar menuBar;
	private JTextArea textArea;
	private JScrollPane scrollTextPane;
	private JTextField  possibleWordsPane;
	private JScrollPane scrollPossibleWordsPane;
	private Automaton automaton;
	  
	/***
	 * constructor
	 * @param lex
	 */
	public GUI(Automaton lex){		
		//window
		super("Éditeur de texte");
		setSize(750, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container = this.getContentPane();
		container.setLayout(new BorderLayout());
		  
		//text area
		textArea = new JTextArea();
		textArea.setFont(new Font("Arial", 0, 24));
		textArea.setLineWrap(true);
		textArea.setFocusable(true);
		textArea.addKeyListener(this);
		scrollTextPane = new JScrollPane(textArea);
		container.add(scrollTextPane);
		
		//lower bar that contains all the possible words
		possibleWordsPane = new JTextField("mots possibles ici");
		possibleWordsPane.setFont(new Font("Arial",0, 22));
		possibleWordsPane.setLayout(new GridLayout(0,1));
		possibleWordsPane.setEditable(false);
		scrollPossibleWordsPane = new JScrollPane(possibleWordsPane);
		scrollPossibleWordsPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0,0));
		container.add(scrollPossibleWordsPane, BorderLayout.SOUTH);
		  	
		//button that can be used to show the labels of a word
        JButton button = new JButton();
        button.setText("Trouver les labels d'un mot");
        JOptionPane pu = new JOptionPane();
        button.addActionListener(new java.awt.event.ActionListener() {
	        @Override
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            String wordWanted = pu.showInputDialog(container,
	                    "Entrez le mot", null);
	            showLabels(wordWanted, container);
	            
	        }
        });
        container.add(button, BorderLayout.NORTH);
        
		automaton = lex;
		this.setVisible(true);	  
	}
	
	/***
	 * pop-up in which it shows the labels of a word,
	 * if the word exists
	 * 
	 * @param word
	 * @param frame
	 */
	public void showLabels(String word, Container frame) {
		State stateWord = automaton.getStateFromValue(word);
	    JOptionPane.showMessageDialog(frame, 
	        "Nombre de fois utilise : " + stateWord.getTimesUsed() + " \n Recemment utilise : " + stateWord.getIsRecent(),
	        "Pour le mot " + word,
	        JOptionPane.QUESTION_MESSAGE, 
	        null);
	}

	/***
	 * event listener who handles when a key
	 * is typed
	 */
	public void keyTyped(KeyEvent e) {
	}
	
	/***
	 * event listener who handles when a key is
	 * pressed
	 */
	public void keyPressed(KeyEvent e) {
	}
	
	/***
	 * event listener who handles when a key
	 * is released. When a key is released, it
	 * changes the label of a word if necessary, and updates
	 * the display of the lower bar that shows the possible
	 * words.
	 * 
	 */
	public void keyReleased(KeyEvent e) {
		// ici, //s marche pas pcq ca va reconnaitre "s".. weirdly
		String punctuation = "([.,!?:;'\"-]|)+ \t \n \b ";
		
		// if the user is not backspacing
		if(!(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)) {
			String[] wordsSplitByPunc = textArea.getText().split("([.,!?:;'\"-]|\\s)+");
			String lastWord = wordsSplitByPunc[wordsSplitByPunc.length - 1];
			
			// if the the word is a recognized word, words are only recognized the moment a punctuation of some sort follows
			if (automaton.getLexiconWords().contains(lastWord) && punctuation.contains(String.valueOf(e.getKeyChar())) ) {
				////////////////////
				System.out.println("\n punctuation (" + e.getKeyChar() +") ! word recognized! \n");
				////////////////////
				
				automaton.addToLastUsedWords(lastWord);
				
				// increment only if the before last char entered is not a punctuation
				String text = textArea.getText();
				if (!punctuation.contains(text.substring(text.length() - 2, text.length() - 1))) {
					automaton.getStateFromValue(lastWord).incrementTimesUsed();	
					///////////////
					System.out.println(lastWord + " INCREMENTED to " + automaton.getStateFromValue(lastWord).getTimesUsed() + "\n" );
					//////////////
				}
			}
			
			
			if(lastWord.length() != 0) {
				showPossibleWords(lastWord);
			}
			//if it's empty, then show nothing
			else {
				possibleWordsPane.setText("");
			}
			
		}
		// backspacing removes suggestions
		else {
			possibleWordsPane.setText("");
		}	
	}
	  
	/**
	 * changes the display of the lower bar that shows the possible
	 * words from the user's input 
	 * @param word
	 */
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
}
