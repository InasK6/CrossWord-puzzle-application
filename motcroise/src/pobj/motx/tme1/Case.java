package pobj.motx.tme1;
/**
 * 
 * classe représentant une case de la grille
 *
 */
public class Case {
	/**
	 * ligne de la case
	 */
    private int lig;
    /**
     * colonne de la case
     */
    private int col;
    /**
     * caractère dans la case
     */
    private char c;
    /**
     * constructeur
     * @param lig ligne de la case
     * @param col  colonne de la case
     * @param c caractère dans la case
     */
    public Case(int lig ,int col,char c) {
    	this.lig=lig;
    	this.col=col;
    	this.c=c;
    }
    /**
     * 
     * @return colonne de la case
     */
    public int getCol() {return col;}
    /**
     * 
     * @return ligne de la case
     */
    public int getLig() {return lig;}
    /**
     * 
     * @return caractère présent dans la case
     */
    public char getChar() {
    	return c;
    }
    /**
     * met le caractère c dans la case
     * @param c caractère
     */
    public void setChar(char c) {
    	this.c=c;
    }
    /**
     * teste si la case est vide
     */
    public boolean isVide() {
    	return (c==' ');
    }
    /**
     * teste si la case est pleine !( vide ou avec une lettre déja placée) 
     */
    public boolean isPleine() {
    	return (c=='*');
    }
    
}
