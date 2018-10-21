import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;


public class Main {

	public static void main(String[] args) {
		
		//public String creerGraphe(String nomFichier){
		Graphe graphe = new Graphe();
		ArrayList<Noeud> noeudsParsed = new ArrayList<Noeud>();
			try{
				// https://stackoverflow.com/questions/4716503/reading-a-plain-text-file-in-java
				BufferedReader br = new BufferedReader(new FileReader("resources/centresLocaux.txt"));
				try {
				    String line = br.readLine();
				    	// PRESENCE BORNES, initialisation de noeuds
					    while (!line.equals("")) {	
					    	String[] infoBorne = line.split(",");
					    	String numeroBorne = infoBorne[0];
					    	boolean bornePresente = infoBorne[1].equals("1");
					    	//System.out.print(numeroBorne + "," + bornePresente + "\n");
					    	Noeud noeud = new Noeud(numeroBorne, bornePresente);
					    	noeudsParsed.add(noeud);
					    	line = br.readLine();
					    }
					    
					    line = br.readLine();
					    while (line !=null) {
					    	String[] infoAretes = line.split(",");
					    	int noeud1 = Integer.parseInt(infoAretes[0]);
					    	int distanceArc = Integer.parseInt(infoAretes[2]);
					    	int noeud2 = Integer.parseInt(infoAretes[1]);
					    	noeudsParsed.get(noeud1-1).ajouterDestination(noeudsParsed.get(noeud2-1), distanceArc);
					    	line = br.readLine();
					    }
				} finally {
				    br.close();
				}
			}
		    catch (IOException ex){
		        System.out.println(ex);
		    }
		
		// ajout des noeuds dans le graphe
		for (int i = 0; i < noeudsParsed.size() ; i++) {
			graphe.ajouterNoeud(noeudsParsed.get(i));
		}
		
		//} 
		
		graphe = AlgorithmeDijkstra.caculerCheminLePlusCourtDeSource(graphe, noeudsParsed.get(0));
		
		Iterator<Noeud> it = graphe.obtenirNoeuds().iterator();
		while (it.hasNext()) {
			Noeud noeudDestination = it.next();
			LinkedList<Noeud> cheminLePlusCourt = noeudDestination.obtenirCheminLePlusCourt();
			System.out.println(noeudDestination.obtenirNom());
			for(int i = 0 ; i < cheminLePlusCourt.size() ; i++) {
			System.out.println(" -> " + cheminLePlusCourt.get(i).obtenirNom());
			}
			System.out.println(" -> " + noeudDestination.obtenirNom());
			System.out.println("\n");
		}
	}

}

