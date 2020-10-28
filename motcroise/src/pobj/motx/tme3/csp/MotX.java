package pobj.motx.tme3.csp;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme1.Emplacement;
import pobj.motx.tme2.GrillePotentiel;

public class MotX implements ICSP {
	private List<IVariable> dicoVar=new ArrayList<IVariable>();
	private GrillePotentiel gp;
	public MotX(GrillePotentiel gp) {
		this.gp=gp;
		initDicoVar();
	}

	private void initDicoVar() {
		List<Emplacement> liste=gp.getPlaces();
		for(int i=0;i< liste.size(); i++) {
			if(liste.get(i).hasCaseVide()) {
				dicoVar.add(new DicoVariable(i,gp));
			}
		}
	}
	/**
	 * @return liste des variables du problèmes courant
	 */
	@Override
	public List<IVariable> getVars() {
		
		return dicoVar;
	}
	/**
	 * teste si le problème est encore satisfiable
	 */
	@Override
	public boolean isConsistent() {
		
		return gp.getProp();
	}
	/**
	 * affecte une des variables du problème
	 */
	@Override
	public ICSP assign(IVariable vi, String val) {
		if(! (vi instanceof DicoVariable)) {
			return null;
		}
		else {
			DicoVariable d=(DicoVariable) vi;
			return new MotX(gp.fixer(d.getIndice(), val));
		}
	}
	
	/*public boolean resoudre() {
		boolean b=false;
		for()
		return b;
	}*/
	public String toString() {
		return gp.getGP().getGrille().toString();
	}
}
