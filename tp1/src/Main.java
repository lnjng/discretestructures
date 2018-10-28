import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class Main {
	
	private static Graphe graphe;
	private static ArrayList<Noeud> noeudsParsed;
	
	public static void main(String[] args) {
		
		/*Graphe graphe = creerGraphe("centresLocaux.txt");
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
		}*/
		
		
		/////////////////// INTERFACE ///////////////////////
		boolean quitter = false;
		while(!quitter) {
			
			System.out.println("(a) Mettre à jour la carte.\n(b) Déterminer le plus court chemin sécuritaire.\n(c) Extraire un sous-graphe.\n(d) Quitter.");
			// Option reader
	        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
	        String option = "";
			try {
				option = reader.readLine();
			} 
			catch (IOException e) {
				e.printStackTrace();
			} 
			
			switch (option) {
				case "a": 	String nomFichier = "";
							System.out.println("Entrez le nom du fichier avec son extension: ");
							try {
								nomFichier = reader.readLine();
							} 
							catch (IOException e) {
								e.printStackTrace();
							}
							graphe = creerGraphe(nomFichier);
							break;
				case "b":	if (graphe.obtenirNoeuds().isEmpty()) {
								System.out.println("Aucune carte trouvée, essayez l'option (a)");
								break;
							}
							else {
								System.out.println("Entrez la catégorie d'urgence (1, 2 ou 3): ");
								String categorie = "";
								try {
									categorie = reader.readLine();
								} catch (IOException e) {
									System.out.println(e);
								}
								System.out.println("Entrez le lieu d'origine (numero): ");
								String origine = "0";
								try {
									origine = reader.readLine();
								} catch (IOException e) {
									System.out.println(e);
								}
								System.out.println("Entrez le lieu de destination (numero): ");
								String destination = "0";
								try {
									destination = reader.readLine();
								} catch (IOException e) {
									System.out.println(e);
								}
								LinkedList<Noeud> chemin = plusCourtChemin(categorie, origine, destination);
								System.out.println(origine);
								for(int i = 0 ; i < chemin.size() ; i++) {
									System.out.println(" -> " + chemin.get(i).obtenirNom());
								}
								System.out.println("->" + destination);
								System.out.println("DISTANCE: " + graphe.obtenirNoeudSelonNumero(destination).obtenirDistance());
							}
							break;
							
				case "c":	if (graphe.obtenirNoeuds().isEmpty()) {
								System.out.println("Aucune carte trouvée, essayez l'option (a)");
								break;
							}
							break;
				case "d":	quitter = true;
			}
			
			
			
		}
		
		
	}
	
	
	public static LinkedList<Noeud> plusCourtChemin(String categorie, String origine, String destination){
		LinkedList<Noeud> chemin = new LinkedList<Noeud>();
		graphe.reinitialiserNoeuds();
		Integer origineIndex = Integer.parseInt(origine);
		//////// ICI CREER UN AUTRE GRAPHE POUR NE PAS MODIFIER GRAPHE D'ORIGINE??/////////
		graphe = AlgorithmeDijkstra.caculerCheminLePlusCourtDeSource(graphe, noeudsParsed.get(origineIndex));
		Noeud noeudDestination = graphe.obtenirNoeudSelonNumero(destination);
		chemin = noeudDestination.obtenirCheminLePlusCourt();
		return chemin;
	}
	
	public static Graphe creerGraphe(String nomFichier){
		graphe = new Graphe();
		noeudsParsed = new ArrayList<Noeud>();
			try{
				// https://stackoverflow.com/questions/4716503/reading-a-plain-text-file-in-java
				BufferedReader br = new BufferedReader(new FileReader("resources/"+nomFichier));
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
			return graphe;
	} 

}

