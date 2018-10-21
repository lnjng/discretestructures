import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class AlgorithmeDijkstra {

	
	
	public static Graphe caculerCheminLePlusCourtDeSource(Graphe graphe, Noeud noeudSource){
		noeudSource.modifierDistance(0);

		Set<Noeud> noeudsVisites = new HashSet<>();
		Set<Noeud> noeudsNonVisites = new HashSet<>();

		noeudsNonVisites.add(noeudSource);
		// On visite tous les noeuds
		while (noeudsNonVisites.size() != 0){
			Noeud noeudActif = obtenirNoeudPlusPetiteDistance(noeudsNonVisites);
			noeudsNonVisites.remove(noeudActif);
			for (Map.Entry<Noeud, Integer> paireNoeudAdjacent : noeudActif.obtenirNoeudsAdjacents().entrySet()){
				Noeud noeudAdjacent = paireNoeudAdjacent.getKey();
				Integer distanceArc = paireNoeudAdjacent.getValue();
				if (!noeudsNonVisites.contains(noeudAdjacent)){
					calculerDistanceMinimale(noeudAdjacent, distanceArc, noeudActif);
					noeudsNonVisites.add(noeudAdjacent);
				}
			}
		noeudsVisites.add(noeudActif);
		}
		return graphe;
	}

	private static Noeud obtenirNoeudPlusPetiteDistance(Set<Noeud> noeudsNonVisites){
		Noeud noeudPlusPetiteDistance = null;
		int distanceLaPlusPetite = Integer.MAX_VALUE;

		for (Noeud noeud : noeudsNonVisites){
			int distanceNoeud = noeud.obtenirDistance();
			if(distanceNoeud < distanceLaPlusPetite){
				distanceLaPlusPetite = distanceNoeud;
				noeudPlusPetiteDistance = noeud;
			}
		}
		return noeudPlusPetiteDistance;
	}

	private static void calculerDistanceMinimale(Noeud noeudAEvaluer, Integer distanceArc, Noeud noeudSource){
		Integer distanceSource = noeudSource.obtenirDistance();
		if (distanceSource + distanceArc < noeudAEvaluer.obtenirDistance()){
			// on modifie la distance du noeud si le chemin est meilleur
			noeudAEvaluer.modifierDistance(distanceSource + distanceArc);
			LinkedList<Noeud> cheminLePlusCourt = new LinkedList<>(noeudSource.obtenirCheminLePlusCourt());
			cheminLePlusCourt.add(noeudSource);
			noeudAEvaluer.modifierCheminLePlusCourt(cheminLePlusCourt);
		}
	}
	
}
