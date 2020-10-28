package pobj.motx.tme1;

import java.util.ArrayList;
import java.util.List;
/**
 * Classe représentant des séquences contigues qui identifient les emplacements des mots dans la grille
 * 
 *
 */
public class Emplacement {
	/**
	 * cases contigues qui composent l'emplacement
	 */
	private List<Case> lettres;
	/**
	 * Constructeur
	 * @param l référence vers le mot associé à l'emplacement
	 */
	public Emplacement(ArrayList<Case> l) {
		lettres=l;
	}
	public Emplacement() {
		lettres=new ArrayList<Case>();
	}
	/**
	 * 
	 * @return true si l'emplacement contient une case vide
	 */
	public boolean hasCaseVide() {
		boolean b=false;
		for(Case c: lettres) {
			b=b||c.isVide();
		}
		return b;
	}
	/**
	 * Ajoute une case à un emplacement déja crée
	 * @param c case à ajouter
	 */
	public void addCase(Case c) {
		lettres.add(c);
	}
	
	/**
	 * affiche le mot à cet emplacement
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder sb=new StringBuilder();
		for(Case c: lettres) {
			sb.append(c.getChar());
		}
		return sb.toString();
	}
	/**
	 * 
	 * @return taille de l'emplacement de mot
	 */
	public int size() {
		return lettres.size();
	}
	
	public List<Case> getLettres(){
		return lettres;
	}

	
}
