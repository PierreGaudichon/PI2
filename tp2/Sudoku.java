public class Sudoku {
	/*
	 *
	 * PI2, TP n°2
	 * Groupe 4
	 * Binome : Serre Fanny, GAUDICHON Pierre
	 *
	 */
	static final int n = 3 ; // taille des regions
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
		for (int j = 0; j < m[i].length; j++) {
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
		for(int i = 0; i < m.length; i++) {
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
		i = i - i%n;
		j = j - j%n;
		for(int i2 = 0; i2 < n; i2++) {
			for (int j2 = 0; j2 < n; j2++) {
				if(m[i+i2][j+j2] == v) {
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
		boolean[] r = new boolean[(int) Math.pow(n, 2) + 1];
		r[0] = false;
		//int v = m[i][j];
		for(int v = 1; v < Math.pow(n, 2)+1; v++) {
			r[v] = !(presentLigne(m, v, i) || presentColonne(m, v, j) || presentRegion(m, v, i, j));
		}
		return r;
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
		int c = 0;
		int v = -1;
		for(int a = 0; a < r.length; a++) {
			if(r[a]) {
				c++;
				v = a;
			}
		}
		if(c == 1) {
			return v;
		} else {
			return -1;
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
			for(int i = 0; i < m.length; i++) {
				for(int j = 0; j < m.length; j++) {
					if(m[i][j] == 0){
						int t = toutSeul(m, i, j);
						if(t != -1) {
							m[i][j] = t;
							System.out.println(
								"On modifie la case ["
								+ i + "," + j + "] "
								+ "par la valeur : "
								+ t + "."
								);
							changes = true;
						}
					}
				}
			}
		}

		System.out.println("Il se peut qu'on ait avance\n\n"+enClair(m));
	} // essais

	public static void test(String s, boolean t) {
		/*
		 * Prerequis :
		 *  - s est une chaine
		 *  - t est un boolean resultant d un test.
		 * Resultat : affiche si le test reussit ou non.
		 *
		 */
		if(!t) {
			System.out.println("Failed : " + s);
		} else {
			System.out.println("Success : " + s);
		}
	}
	
	public static boolean arrayEquals(boolean[] a, boolean[] b) {
		/*
		 * Prerequis :
		 *  - a est un tableau de boolean
		 *  - b est un tableau de boolean
		 * Resultat : les deux tableaux sont egaux.
		 *
		 */
		if(a.length != b.length) {
			return false;
		}
		for(int i = 0; i < a.length; i++) {
			if(a[i] != b[i]) {
				return false;
			}
		}
		return true;
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
		
			int[][] m = aPartirDe(grille1);
			int[][] m2 = aPartirDe(grille2);

			// Partie 1
			test("presentLigne 1", presentLigne(m, 4, 0) == true);
			test("presentLigne 2", presentLigne(m, 2, 0) == false);

			test("presentColonne 1", presentColonne(m, 1, 0) == true);
			test("presentColonne 2", presentColonne(m, 5, 0) == false);

			test("presentRegion 1", presentRegion(m, 4, 0, 0) == true);
			test("presentRegion 2", presentRegion(m, 5, 0, 0) == false);

			// Partie 2
			test("lesPossiblesEn 1", arrayEquals(lesPossiblesEn(m2, 0, 0),
				new boolean[]{false, false, true, false, false, true, false, true, false, false}));
			test("lesPossiblesEn 2", arrayEquals(lesPossiblesEn(m2, 0, 2),
				new boolean[]{false, false, false, false, false, true, false, true, false, true}));

			test("toutSeul 1", toutSeul(m, 0, 0) == -1);
			test("toutSeul 2", toutSeul(m, 4, 2) == 1);

		// END

		essais(grille1);
		essais(grille2);

		/*
		 * Pour que les algorithmes soient compatible avec n !=3, on a remplacé les occurences de 3 par des n.
		 * Il suffit maintenant de changer la valeur de n par une autre valeur en debut du programme. 
		 */
		
		/*
		 * Tests pour n = 4.
		 */
		
		/*String grille3 =
				"0400 0010 0060 0000\n" +
				"0070 9000 8000 0000\n" +
				"1900 0860 0740 0000\n" +
				"0000 0000 0000 0000\n" +
				"                   \n" +
				"2000 6900 0100 0000\n" +
				"0300 4050 0900 0000\n" +
				"0600 0170 0030 0000\n" +
				"0000 0000 0000 0000\n" +
				"                   \n" +
				"9100 7500 0420 0000\n" +
				"0080 0020 7000 0000\n" +
				"0000 0000 0000 0000\n" +
				"4000 3000 0800 0000  " ;
		
		essais(grille3);*/
		
		/*
		 * Exception in thread "main" java.lang.StringIndexOutOfBoundsException: String index out of range: 281
			at java.lang.String.charAt(String.java:658)
			at Sudoku.aPartirDe(Sudoku.java:49)
			at Sudoku.essais(Sudoku.java:185)
			at Sudoku.main(Sudoku.java:324)

		 */
		
		/*
		 * On voit ici que la fonction aPartirDe n'est pas prevue pour fonctionner si n !=3.
		 */
	}

}
