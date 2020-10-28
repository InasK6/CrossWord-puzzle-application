package pobj.motx.tme2;

public class CroixContrainte implements IContrainte {
	/**
	 * indice du premier emplacement dans la grille
	 */
	private int m1;
	/**
	 * indice du deuxième emplacement dans la grille
	 */
	private int m2;
	/**
	 * indice de la case de l'emplacement m1
	 *  ou a lieu le croisement 
	 */
	private int e1;
	/**
	 * indice de la case de l'emplacement m2
	 *  ou a lieu le croisement 
	 */
	private int e2;
	
	public CroixContrainte(int m1, int e1, int m2, int e2) {
		this.m1=m1;
		this.m2=m2;
		this.e1=e1;
		this.e2=e2;
		
	}

	@Override
	/**
	 * Réduction selon l'ensemble de lettres possibles 
	 * à une position
	 * @param grille grille
	 */
	public int reduce(GrillePotentiel grille) {
		//nombre de lettres filtrées
		int nb=0;
		
		Dictionnaire dico1=grille.getDico(m1);
		Dictionnaire dico2=grille.getDico(m2);
		
		//Ensemble de lettres pouvant figurer dans la case e1
		//EnsembleLettre l1=grille.getLettresCaseEmplacement(m1, e1);
		//EnsembleLettre l2=grille.getLettresCaseEmplacement(m2, e2);
		EnsembleLettre l1=dico1.getEnsembleLettreDico(e1);
		EnsembleLettre l2=dico2.getEnsembleLettreDico(e2);
		
		//intersection l1&&l2
		EnsembleLettre s;
		if(l1!=null && l2!=null) {
			s=l1.Intersection(l2);
		
		
			int size=s.size();
			if(l1.size()>size) {
				nb+=dico1.filtreParEnsembleLettre(e1, s);
			}
			if(l2.size()>size) {
				nb+=dico2.filtreParEnsembleLettre(e2, s);
			}
		}
		return nb;
	}
	
	public boolean equals(Object o) {
		if(this==o) {
			return true;
		}
		if (!(o instanceof CroixContrainte)) {
			return false;
		}
		CroixContrainte croix=(CroixContrainte) o;
		return (m1==croix.m1 && e1==croix.e1 && m2==croix.m2 && e2==croix.e2 );
	}
	
	
}
