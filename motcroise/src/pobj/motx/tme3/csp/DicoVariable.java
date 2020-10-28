package pobj.motx.tme3.csp;

import java.util.List;

import pobj.motx.tme2.GrillePotentiel;

public class DicoVariable implements IVariable {
	/**
	 * indice de l'emplacement du mot correspondant
	 */
	private int indice;
	
	private GrillePotentiel grille;
	
	public DicoVariable(int i, GrillePotentiel g) {
		indice =i;
		grille=g;
	}
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("mot à l'emplacement ");
		sb.append(indice);
		/*sb.append("de la grille ");
		sb.append(grille.toString());*/
		sb.append("de domaine: ");
		for(String s: getDomain()) {
			sb.append(s);
		}
		
		
		return sb.toString();
	}
	public int getIndice() {
		return indice;
	}
	
	
	@Override
	public List<String> getDomain() {
		List<String> liste=grille.getDico(indice).getMots();
		return liste;
	}

}
