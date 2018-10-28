import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
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
	
	public void reinitialiserNoeuds() {
		for (Noeud noeud : noeuds) {
			noeud.reinitialiserNoeud();
		}
	}
	
	public Noeud obtenirNoeudSelonNumero(String numero) {
		Iterator<Noeud> it = this.obtenirNoeuds().iterator();
		while (it.hasNext()) {
			Noeud noeud = it.next();
			if (noeud.obtenirNom().equals(numero)) {
				return noeud;
			}
		}
		return null;
	}
	
}
