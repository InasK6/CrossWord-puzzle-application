package pobj.motx.tme2;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme1.Emplacement;
import pobj.motx.tme1.GrillePlaces;

public class GrillePotentiel {
	/**
	 * Stocke la grille courante
	 */
	private GrillePlaces g;
	/**
	 * stocke le dictionnaire français complet
	 */
	private Dictionnaire dico;
	/**
	 * stocke le domaine de chaque emplacement 
	 */
	private List<Dictionnaire> motsPot;
	/**
	 * liste des contraintes qui peuvent être appliquées à la grille
	 */
	private List<IContrainte> contraintes=new ArrayList<IContrainte>();

	private boolean prop;
	public GrillePotentiel(GrillePlaces grille, Dictionnaire dicoComplet) {
		g=grille;
		dico=dicoComplet;
		motsPot=new ArrayList<Dictionnaire>();

		init();
		filtreLettre();
		detectConstraint();		
		prop=propage();
		
	}
	
	public GrillePotentiel( GrillePlaces grille, Dictionnaire dico, List<Dictionnaire> l) {
		g=grille;
		this.dico=dico;
		motsPot=l;
		
		init2(l);
		filtreLettre();
		detectConstraint();		
		prop=propage();
		
	}
	//public GrillePotentiel
	public GrillePlaces getGP() {
		return g;
	}
	public boolean getProp() {
		return prop;
	}
	public List<Emplacement> getPlaces() {
		return g.getPlaces();
	}
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("Grille places: \n");
		sb.append(g.toString());
		sb.append("avec motPots: \n");
		for(Dictionnaire d: motsPot) {
			sb.append(d.toString());
		}
		
		
		return sb.toString();
	}
	/**
	 * 
	 * @return liste des contraintes sur la grille
	 */
	public List<IContrainte> getContraintes(){
		return contraintes;
	}
	
	/**
	 * 
	 * @return true si au moins un emplacement a un domaine potentiel vide
	 */
	public boolean isDead() {
		boolean b=false;
		for(Dictionnaire d: motsPot) {
			b=b||(d.size()==0);
		}
		return b;
	}
	
	public List<Dictionnaire> getMotsPot(){
		return motsPot;
	}
	/**
	 * 
	 * @param m indice de l'emplacement ou on veut mettre le mot dans la grille
	 * @param soluce le mot qu'on veut mettre dans la grille
	 * @return nouvelle grille potentielle avec le mot soluce fixé
	 */
	public GrillePotentiel fixer(int m, String soluce) {
		GrillePlaces p=g.fixer(m, soluce);
		return new GrillePotentiel(p,dico, motsPot);
	}
	/**
	 * 
	 * @param m1 indice de l'emplacement
	 * @param c1 indice de la case
	 * @return
	 */
	public EnsembleLettre getLettresCaseEmplacement(int m1, int c1) {
		Dictionnaire dico1=motsPot.get(m1);
		EnsembleLettre l1=dico1.getEnsembleLettreDico(c1);
		return l1;
	}
	
	public Dictionnaire getDico(int m1) {
		return motsPot.get(m1);
	}
	
	private boolean propage() {
		int nb;
		while(true) {
			nb=0;
			for(IContrainte c: contraintes) {
				nb+=c.reduce(this);
			}
			if(nb==0) {
				return true;
			}

			if(isDead()) {
				return false;
			}
		}
		
	}
	/**
	 * initialisation des domaines des emplacements
	 */
	private void init() {
		
		List<Emplacement> l=g.getPlaces();
		// pour chaque emplacement, on crée un nouveau dictionnaire
		for(int i=0; i<l.size(); i++) {
			Dictionnaire d=dico.copy();
			d.filtreLongueur(l.get(i).size());
			motsPot.add(d);
		}
	}
	/**
	 * Initialisation 2 avec les dicos mis à jour
	 */
	private void init2(List<Dictionnaire> list) {
		
		List<Emplacement> l=g.getPlaces();
		// pour chaque emplacement, on crée un nouveau dictionnaire
		for(int i=0; i<l.size(); i++) {
			Dictionnaire d=list.get(i).copy();
			d.filtreLongueur(l.get(i).size());
			motsPot.add(d);
		}
	}
	/**
	 * Pour chaque emplacement on vérifie s'il y a une lettre dans la case
	 * dans ce cas on filtre le dico
	 */
	private void filtreLettre() {
		List<Emplacement> l=g.getPlaces();
		for (int i=0; i< l.size(); i++) {
			Emplacement e=l.get(i);
			for(int j=0; j<e.size(); j++) {
				if(!(e.getLettres().get(j).isVide() || e.getLettres().get(j).isPleine() )) {
					motsPot.get(i).filtreParLettre(e.getLettres().get(j).getChar(), j);
				}
			}
		}
	}
	/*
	 * Detection de contraintes entre deux mots
	 * s'il y a un croisement entre deux mots, 
	 * on rajoute une contrainte de croisement
	 */
	private void detectConstraint() {
		int valeur=g.getNbHorizontal();
		List<Emplacement> liste=g.getPlaces();
		for(int e1=0;e1< valeur; e1++) {
			for(int e2=valeur; e2<liste.size();e2++) {
				//On cherche s'il y a une case en commun entre e1 et e2
				for(int i=0; i<liste.get(e1).size();i++) {
					for(int j=0; j<liste.get(e2).size();j++) {
						if( liste.get(e1).getLettres().get(i).isVide() && liste.get(e1).getLettres().get(i)==liste.get(e2).getLettres().get(j)) {
							CroixContrainte cr=new CroixContrainte(e1,i,e2,j);
							contraintes.add(cr);
						}
					}
				}
			}
		}
	}
}
