package pobj.motx.tme1;

public class Grille {
	/** matrice de hauteur lignes sur largeur colonnes */
	private Case[][] cases;

	/** Constructeur
	 * @param hauteur nombre de lignes
	 * @param largeur nombre de colonnes
	 * */
	public Grille( int hauteur, int largeur) {
		cases= new Case[hauteur][largeur];
		for (int i=0; i<hauteur; i++) {
			for(int j=0; j<largeur; j++) {
				cases[i][j]=new Case(i,j, ' ');
			}
		}
	}
	/**
	 * 
	 * @param lig ligne de la case
	 * @param col colonne de la case
	 * @return le contenu de la case à la ligne lig et à la colonne col
	 */
	public Case getCase(int lig,int col) {
		return cases[lig][col];
	}
	public String toString() {
		String affiche=GrilleLoader.serialize(this, false);
		return affiche;
	}
	/**
	 * 
	 * @return nombre de lignes de la grille
	 */
	public int nbLig() {
		return cases.length;
	}
	/**
	 * 
	 * @return nombre de colonnes de la grille
	 */
	public int nbCol() {
		return cases[0].length;
	}
	
	/**
	 * 
	 * @return une copie de la grille
	 */
	public Grille copy() {
	  Grille copy=new Grille (nbLig(),nbCol());
	  for(int i=0;i<nbLig();i++) {
		  for (int j=0;j<nbCol();j++) {
			  copy.cases[i][j]=new Case(i,j,cases[i][j].getChar());
		  }
	  }
	  return copy;
	}

}
