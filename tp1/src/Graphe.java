import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Graphe {

	// On ne peut pas avoir plusieurs fois le meme noeud, donc un set
	// est plus approprie qu'une map
	private Set<Noeud> noeuds = new HashSet<>();
	
	public void ajouterNoeud(Noeud noeud){
		this.noeuds.add(noeud);
	}
	
	public Set<Noeud> obtenirNoeuds(){
		return this.noeuds;
	}
	
}
