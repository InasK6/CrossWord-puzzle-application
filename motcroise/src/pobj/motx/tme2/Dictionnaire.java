package pobj.motx.tme2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Un ensemble de mots. ( liste de mots représentés par des String)
 *
 */
public class Dictionnaire {

	// stockage des mots
	private List<String> mots = new ArrayList<>();
	
	private EnsembleLettre[] cache=null;

	/**
	 * Ajoute un mot au Dictionnaire, en dernière position.
	 * @param mot à ajouter, il sera stocké en minuscules (lowerCase)
	 */
	public void add(String mot) {
		mots.add(mot.toLowerCase());
	}

	/**
	 * Taille du dictionnaire, c'est à dire nombre de mots qu'il contient.
	 * @return la taille
	 */
	public int size() {
		return mots.size();
	}
	
	/**
	 * Accès au i-eme mot du dictionnaire.
	 * @param i l'index du mot recherché, compris entre 0 et size-1.
	 * @return le mot à cet index
	 */
	public String get(int i) {
		return mots.get(i);
	}

	/**
	 * Rend une copie de ce Dictionnaire.
	 * @return une copie identique de ce Dictionnaire
	 */
	public Dictionnaire copy () {
		Dictionnaire copy = new Dictionnaire();
		copy.mots.addAll(mots);
		copy.cache=this.cache;
		return copy;
	}

	/**
	 * Retire les mots qui ne font pas exactement "len" caractères de long.
	 * Attention cette opération modifie le Dictionnaire, utiliser copy() afin de filtrer pour ne pas perdre d'information.
	 * @param len la longueur voulue 
	 * @return le nombre de mots supprimés
	 */
	public int filtreLongueur(int len) {
		List<String> cible = new ArrayList<>();
		int cpt=0;
		for (String mot : mots) {
			if (mot.length() == len)
				cible.add(mot);
			else
				cpt++;
		}
		mots = cible;
		if(cpt>0) {
			cache=null;
		}
		return cpt;
	}

	
	@Override
	public String toString() {
		if (size() == 1) {
			return mots.get(0);
		} else {
			return "Dico size =" + size();
		}
	}
	public static Dictionnaire loadDictionnaire(String path) {
		Dictionnaire d=new Dictionnaire();
		try(BufferedReader br=new BufferedReader(new FileReader(path))) {
			for(String line=br.readLine(); line!=null; line=br.readLine()) {
				//ajout du mot lu au dictionnaire
				d.add(line);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	public int filtreParLettre(char c, int i) {
		int nb=0;
		List<String> list=new ArrayList<String>();
		for(String s: mots) {
			if(s.charAt(i)==c) {
				list.add(s);
			}
			else 
				nb++;
		}
		mots=list;
		if(nb>0) {
			cache=null;
		}
		return nb;
		
	}
	/**
	 * @param position indice de la case dont on cherche l'ensemble des lettres possibles
	 * @return l'ensemble de lettres à une position 
	 */
	public EnsembleLettre getEnsembleLettreDico(int position) {
		EnsembleLettre e=new EnsembleLettre();
		/**
		 * on sait qu'il y aura eu un traitement 
		 * au préalable qui aura limité le nombre 
		 * de mots du dictionnaire de français à ceux ayant la taille de notre case 
		 */
		if(mots.size()==0) {
			return null;
		}
		
		if (cache==null) {
			cache=new EnsembleLettre[mots.get(0).length()];
		}
		if(cache[position]==null) {
			for ( String s: mots) {
				e.add((s.charAt(position)));
			}
			cache[position]=e;
		}
		else {
			e=cache[position];
		}
		
		return e;
	}
	/**
	 * filtrer le dictionnaire par rapport à un indice
	 * et un ensemble de lettre possible
	 * @param position position de la lettre dans le mot
	 * @param e ensemble de lettre possibles pour cette position
	 */
	
	public int filtreParEnsembleLettre(int position,EnsembleLettre e) {
		List<String> liste=new ArrayList<String>();
		int nb=0;
		for(String mot: mots) {
			if(e.contains(mot.charAt(position))) {
				liste.add(mot);
			}
			else {
				nb++;
			}
		}
		mots=liste;
		if(nb>0) {
			cache=null;
		}
		return nb;
	}
	
	/**
	 * @return l'ensemble des mots du dico
	 */
	public List<String> getMots(){
		return mots;
	}
	
}
