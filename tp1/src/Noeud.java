import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Noeud {

	// https://www.baeldung.com/java-dijkstra
	private String nom;
	private LinkedList<Noeud> cheminLePlusCourt = new LinkedList<>();
	// La distance du noeud par rapport a la source
	private Integer distance = Integer.MAX_VALUE;
	// Cette map associe le s
	Map<Noeud, Integer> noeudsAdjacents = new HashMap<>();
	private boolean possedeBorne;

	Noeud(String nom, boolean possedeBorne){
		this.nom = nom;
		this.possedeBorne = possedeBorne;
	}
	
	public void reinitialiserNoeud() {
		this.distance = Integer.MAX_VALUE;
	}
	
	public void ajouterDestination(Noeud destination, int distance){
		this.noeudsAdjacents.put(destination, distance);
	}

	public String obtenirNom(){
		return this.nom;
	}

	Integer obtenirDistance(){
		return this.distance;
	}

	public Map<Noeud, Integer> obtenirNoeudsAdjacents(){
		return this.noeudsAdjacents;
	}

	public boolean possedeBorne(){
		return this.possedeBorne;
	}

	public LinkedList<Noeud> obtenirCheminLePlusCourt(){
		return this.cheminLePlusCourt;
	}

	public void modifierDistance(Integer distance){
		this.distance = distance;
	}

	public void modifierCheminLePlusCourt(LinkedList<Noeud> cheminLePlusCourt){
		this.cheminLePlusCourt = cheminLePlusCourt;
	}
}
