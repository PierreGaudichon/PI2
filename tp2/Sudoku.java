public class Sudoku {
	/*
	 *
	 * API, TP n°2
	 * Groupe 4
	 * Binome : Serre Fanny, GAUDICHON Pierre
	 *
	 */
	static final int n = 3 ;		// taille des regions
	/*
	 * Terminologie
	 *
	 * m est un plateau (de sudoku) si
	 * 	- m est un int [][] ne contenant que des entiers compris entre 0 et 9
	 * 	- m.length = n^2
	 *  - m[i].length = n^2 pour tous les i de 0 � n^2-1
	 *
	 */

	static String enClair (int [][] m) {
		/*
		 * Prerequis : m est un plateau de sudoku
		 * Resultat : une cha�ne dont l'affichage permet de visualiser m
		 *
		 */
		String r = "" ;
		for (int i = 0; i < n*n ; i++) {
			for (int j = 0; j < n*n ; j++) {
				r = r + m[i][j] + " " ;
				if (j%n == n-1) {r = r + "  ";}
			}
			if (i%n == n-1) {r = r + "\n";}
			r = r + "\n";
		}
		r = r + " " ;
		return r ;
	} // enClair

	static int [][] aPartirDe (String s) {
		/*
		 * Prerequis : s est une cha�ne contenant au moins n^4 chiffres decimaux
		 * Resultat : un plateau de sudoku initialise avec les n^4 premiers chiffres
		 * decimaux de s (les chiffres sont consideres comme ranges par lignes).
		 */
		int [][] m = new int [n*n][n*n] ;
		int k = 0 ;
		for (int i = 0; i < m.length ; i++) {
			for (int j = 0; j < m[i].length ; j++) {
				while ("0123456789".indexOf(s.charAt(k))==-1) {k++;}
				m[i][j] = (int) s.charAt(k) - (int) '0' ;
				k++ ;
			}
		}
		return m ;
	} // aPartirDe

	static boolean presentLigne (int [][] m, int v, int i) {
		/*
		 * Prerequis :
		 *  - m est un plateau de sudoku
		 *  - v est compris entre 1 et n^2
		 *  - i est compris entre 0 et n^2-1
		 * Resultat : dans m, v est present dans la ligne i
		 *
		 */
		for (Int j = 0; j < m[i].length; j++) {
			if(m[i][j] == v) {
				return true;
			}
		}
		return false;
	} // presentLigne

	static boolean presentColonne (int [][] m, int v, int j) {
		/*
		 * Prerequis :
		 *  - m est un plateau de sudoku
		 *  - v est compris entre 1 et n^2
		 *  - j est compris entre 0 et n^2-1
		 * Resultat : dans m, v est present dans la colonne j
		 *
		 */
		for(Int i; i < m.length; i++) {
			if(m[i][j] == v) {
				return true;
			}
		}
		return false;
	} // presentColonne

	static boolean presentRegion  (int [][] m, int v, int i, int j) {
		/*
		 * Prerequis :
		 *  - m est un plateau de sudoku
		 *  - v est compris entre 1 et n^2
		 *  - i et j sont compris entre 0 et n^2-1
		 * Resultat : dans m, v est present dans la region contenant la case <i, j>
		 *
		 */
		for(Int i_ = i - i%3; i_ < i_+n; i_++) {
			for (Int j_ = j - i%3; i_ < i_+n; i_++) {
				if(m[i_][j_] == v) {
					return true;
				}
			}
		}
		return false;
	} // presentRegion

	static boolean [] lesPossiblesEn (int [][] m, int i, int j) {
		/*
		 * Prerequis :
		 *  - m est un plateau de sudoku
		 *  - i et j sont compris entre 0 et n^2-1
		 *  - m[i][j] vaut 0
		 * Resultat : un tableau r de longueur n^2+1 tel que, dans la tranche r[1..n^2]
		 * r[v] indique si v peut etre place en <i, j>
		 *
		 */
		boolean[] r = boolean[n^2 + 1];
		r[0] = false;
		Int v = m[i][j];
		for(Int k = 1; k < n^2; k++) {
			r[k] != (presentLigne(m, v, i) || presentColonne(m, v, j) || presentRegion(m, v, i, j));
		}
	} // lesPossiblesEn

	static String enClair (boolean[] t) {
		/*
		 * Prerequis : t.length != 0
		 * Resultat :
		 * une cha�ne contenant tous les indices i de la tranche [1..t.length-1] tels
		 * que t[i] soit vrai
		 */
		String r = "{" ;
		for (int i = 1; i < t.length; i++) {
			if (t[i]) {r = r + i + ", " ; }
		}
		if (r.length() != 1) {r = r.substring(0, r.length()-2);}
		return r + "}" ;
	} // enClair

	static int toutSeul (int [][] m, int i, int j) {
		/*
		 * Prerequis :
		 *  - m est un plateau de sudoku
		 *  - i et j sont compris entre 0 et n^2-1
		 *  - m[i][j] vaut 0
		 * Resultat :
		 *  - v 	si la seule valeur possible pour <i, j> est v
		 *  - -1 	dans les autres cas
		 *
		 */
		boolean[] r = lesPossiblesEn(m, i, j);
		Int v = -1;
		for(Int k = 0; k < m.length(); k++) {
			if(v != -1) {
				return -1;
			} else {

			}
		}
	} // toutSeul // PAS FINI

	static void essais (String grille) {
		/*
		 * Prerequis : grille represente une grille de sudoku
		 * (les chiffres sont consideres comme ranges par lignes)
		 *
		 * Effet :
		 * 1) affiche en clair la grille
		 * 2) affecte, tant que faire se peut, toutes les cases pour lesquelles il n'y
		 *    a qu'une seule possibilite
		 * 3) affiche en clair le resultat final
		 */
		int[][] m = aPartirDe(grille);
		System.out.println("Probleme\n\n"+enClair(m));

		boolean changes = true;
		while(changes) {
			changes = false;
			for(Int i = 0; i < m.length(); i++) {
				for(Int j = 0; j < m.length(); j++) {
					if(m[i][j] == 0){
						Int t = toutSeul(m, i, j);
						if(t != -1) {
							m[i][j] = t;
							System.out.println(
								"On modifie la case ["
								+ i + "," + j "] "
								+ "par la valeur :"
								+ t + "."
								)
							changes = true;
						}
					}
				}
			}
		}

		System.out.println("Il se peut qu'on ait avance\n\n"+enClair(m));
	} // essais

	public static void test(String s, boolean t) {
		if(!t) {
			System.out.println("Failed : " + s);
		}
	}

	public static void main(String[] args) {
		String grille1 =
			"040 001 006 \n" +
			"007 900 800 \n" +
			"190 086 074 \n" +
			"            \n" +
			"200 690 010 \n" +
			"030 405 090 \n" +
			"060 017 003 \n" +
			"            \n" +
			"910 750 042 \n" +
			"008 002 700 \n" +
			"400 300 080   " ;
		String grille2 =
			"030 000 006 \n" +
			"000 702 300 \n" +
			"104 038 000 \n" +
			"            \n" +
			"300 020 810 \n" +
			"918 000 265 \n" +
			"062 050 007 \n" +
			"            \n" +
			"000 140 708 \n" +
			"001 209 000 \n" +
			"800 000 020   " ;

		// TESTS

			// Partie 1
			test("presentLine 1", presentLine(grille1, 0, 0) == true);
			test("presentLine 2", presentLine(grille1, 0, 4) == true);

			test("presentColone 1", presentColonne(grille1, 0, 0) == true);
			test("presentColone 2", presentColonne(grille1, 0, 4) == true);

			test("presentRegion 1", presentRegion(grille1, 0, 0, 0) == true);
			test("presentRegion 2", presentRegion(grille1, 0, 4, 4) == true);

			// Partie 2
			// to complete
			test("lesPossiblesEn 1", lesPossiblesEn(m, 0, 0) ==
			     [false, false, false, false, false, false, false, false, false])

			// to complete
			test("toutSeul 1", toutSeul(m, 0, 0) == -1)

		// END

		essais(grille1) ;
		essais(grille2) ;

		// PAS FAIT :
		//		la partie n != 3
	}

}
