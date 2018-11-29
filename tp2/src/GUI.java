import java.awt.*;
import java.awt.event.*;
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
		scrollPossibleWordsPane = new JScrollPane(possibleWordsPane);
		scrollPossibleWordsPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0,0));
		container.add(scrollPossibleWordsPane, BorderLayout.SOUTH);
		  
		  
		automaton = lex;
		this.setVisible(true);
		  
	}
	  
	public void keyTyped(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_DELETE) {
			automaton.previousState();
		}
		else if(e.getKeyChar() == ' ' || e.getKeyChar() == ','){
			automaton.reinitializeState();
		}
		else {
			automaton.nextState(e.getKeyChar());
			//showPossibleWords(automaton.getCurrentStatePossibleWords());
			possibleWordsPane.setText(automaton.getCurrentState().getValue());
		}
	}
	/** Handle the key-pressed event from the text field. */
	public void keyPressed(KeyEvent e) {
	}
	
	/** Handle the key-released event from the text field. */
	public void keyReleased(KeyEvent e) {
	}
	  
	public void showPossibleWords() {
		  
	}
	  
	public void actionPerformed(ActionEvent e) 
	{
	
	}
}
