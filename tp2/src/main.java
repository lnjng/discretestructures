/***
 * main file
 * 
 * @author dihn, huyen trang 1846776
 * 			jiang, helene 1854909
 *
 */

import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
		Automaton automate = new Automaton();
		//Change the param to the lexicon you wish to use
		automate.generateTreeFromFile("resources/lexique6.txt");
		new GUI(automate);
	}

}
