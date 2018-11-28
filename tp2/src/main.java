import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
		Automaton automate = new Automaton();
		automate.generateTreeFromFile("resources/lexique 1.txt");
		
		new GUI(automate);
	}

}
