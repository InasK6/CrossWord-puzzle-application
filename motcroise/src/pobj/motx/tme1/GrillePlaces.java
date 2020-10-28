package pobj.motx.tme1;

import java.util.ArrayList;
import java.util.List;

/** Classe qui explore une Grille pour trouver tous les emplacements de mots */
public class GrillePlaces {
	/** Grille à explorer*/
	private Grille grille;
	/** emplacements trouvés dans la grille */
	private List<Emplacement> places=new ArrayList<Emplacement>();
	/**
	 * nombre de mots horizontaux
	 */
	private int NbHorizontal;
	/** Constructeur 
	 * @param g grille à explorer
	 * */
	public GrillePlaces(Grille grille) {
		this.grille=grille;
		//initialisation de emplacement
		//On commence d'abord par parcourir ligne par ligne pour avoir les horizontaux
		for ( int i=0; i<grille.nbLig(); i++) {
			List<Case> l=getLig(i);
			cherchePlaces( l);
		}
		NbHorizontal=places.size();
		// On cherche colonne par colonne pour récuperer les mots verticaux
		for ( int i=0; i<grille.nbCol(); i++) {
			List<Case> l=getCol(i);
			cherchePlaces( l);
		}
	}
	/**
	 * @return emplacements de mots détectés
	 */
	public List<Emplacement> getPlaces(){
		return places;
	}
	
	/** 
	 * 
	 * @return  nombre de mots horizontaux
	 */
	public int getNbHorizontal() {
		
		return NbHorizontal;
	}
	
	public String toString() {
		StringBuilder sb=new StringBuilder();
		for(Emplacement e: places) {
			sb.append(" mot "+ e.toString());
		}
		return sb.toString();
	}
	/**
	 * 
	 * @param lig numéro de ligne
	 * @return rend les cases qui constituent la ligne lig
	 */
	private List<Case> getLig(int lig){
		List<Case> l=new ArrayList<Case>();
		for(int i=0; i<grille.nbCol(); i++) {
			l.add(grille.getCase(lig, i));
			
		}
		return l;
	}
	/**
	 * 
	 * @param col numéro de colonne
	 * @return rend les cases qui constituent la colonne col
	 */
	private List<Case> getCol(int col){
		List<Case> l=new ArrayList<Case>();
		for(int i=0; i<grille.nbLig(); i++) {
			l.add(grille.getCase(i, col));
			
		}
		return l;
	}
	/**
	 * Cherche des mots dans une ligne ou une colonne
	 * @param cases liste de cases dans une ligne ou colonne
	 */
	private void cherchePlaces( List<Case> cases) {
		Emplacement e;
		e=new Emplacement();
		for (int i=0; i<cases.size(); i++) {
			if(!( cases.get(i).isPleine())) {
				
				e.addCase(cases.get(i));
				
			}
			else {
				if(e.size()>=2) {
					places.add(e);
					
				}
				e=new Emplacement();
			}
		}
		if(e.size()>=2) {
			places.add(e);
			
		}
		
	}
	/**
	 * 
	 * @param m indice de l'emplacement à modifier
	 * @param soluce mot à stocker
	 * @return grille ou les cases de l'emplacement ma pour lettres soluce
	 */
	public GrillePlaces fixer(int m, String soluce) {
		Grille newGrille=grille.copy();
		GrillePlaces gp=new GrillePlaces(newGrille) ;
		List<Case> e=gp.places.get(m).getLettres();
		if(e.size()!=soluce.length()) {
			System.out.println("Erreur, vous voulez mettre un mot dans une case qui n'a pas le même nombre de lettres");
			 
		}
		else {
			for( int i=0; i<e.size(); i++) {
				e.get(i).setChar(soluce.charAt(i));
			}
		}
		
		
		return gp;
	}
	
	public Grille getGrille() {
		return grille;
	}
}
