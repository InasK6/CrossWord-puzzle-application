package pobj.motx.tme2;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un ensemble de lettres
 * @author inas
 *
 */
public class EnsembleLettre {
	/**
	 * Liste de charactères ou chaque String représente une lettre
	 */
	public List<Character> ensemble;
	/**
	 * @return ensemble
	 */
	public List<Character> getEnsemble() {
		return ensemble;
	}

	public EnsembleLettre() {
		ensemble=new ArrayList<Character>();
	}
	public void add(Character c) {
		if(!ensemble.contains(c)) {
			ensemble.add(c);
		}
		
	}
	
	public boolean contains(Character c) {
		boolean b=false;
		for(Character car : ensemble) {
			b=b||(car.equals(c));
		}
		return b;
	}
	public int size() {
		return ensemble.size();
	}
	
	public EnsembleLettre Intersection( EnsembleLettre l2) {
		EnsembleLettre s=new EnsembleLettre();
		for(Character car: this.getEnsemble()) {
			if(l2.contains(car)) {
				s.add(car);
			}
		}
		
//		ensemble.retainAll(l2.ensemble);
//		return this;
		return s;
	}
}
