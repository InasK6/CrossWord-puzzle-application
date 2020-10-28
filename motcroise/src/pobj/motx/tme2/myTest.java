package pobj.motx.tme2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static pobj.motx.tme2.test.GrillePotentielTest.testNombrePot;

import pobj.motx.tme1.Grille;
import pobj.motx.tme1.GrilleLoader;
import pobj.motx.tme1.GrillePlaces;

public class myTest {

	public static void main(String[] args) {
		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/easy.grl");

		assertEquals(5, gr.nbCol());
		assertEquals(5, gr.nbLig());

		System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);

		GrillePotentiel gp = new GrillePotentiel(grille, gut);
		
		
		//assertTrue(!gp.isDead());

		assertEquals(2, gp.getContraintes().size());

		int[] expected = { 5916, 5688 , 5916};

		testNombrePot(gp, expected);

		System.out.println("Succès test GrillePotentiel : easy.");

	}

}
