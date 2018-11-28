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
	//private JPanel possibleWordsPane;
	private JScrollPane scrollPossibleWordsPane;
	
	private Automaton lexicon;
	  
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
		  scrollTextPane = new JScrollPane(textArea);
		  container.add(scrollTextPane);
		  
		  //oo boi
		  //InputMap inputSpaceBar = new InputMap();
		  
		  
		  JTextArea possibleWordsPane = new JTextArea("mots possibles ici");
		  possibleWordsPane.setFont(new Font("Arial",0, 22));
		  possibleWordsPane.setLayout(new GridLayout(0,1));
		  scrollPossibleWordsPane = new JScrollPane(possibleWordsPane);
		  scrollPossibleWordsPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0,0));
		  container.add(scrollPossibleWordsPane, BorderLayout.SOUTH);
		  
		  
		  lexicon = lex;
		  this.setVisible(true);
		  
	  }
	  
	  public void keyTyped(KeyEvent e) {
		  showPossibleWords(e);
	  }
	  
	  public void showPossibleWords(KeyEvent e) {
		  
	  }
	  
	  public void actionPerformed(ActionEvent e) 
	  {

	  }
}
