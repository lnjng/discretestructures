import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
		Automaton automate = new Automaton();
		automate.generateTreeFromFile("resources/lexique6.txt");
	}

}
