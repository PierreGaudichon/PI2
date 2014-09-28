public class exo2 {

	
	//  termine ::  int x  int ->  boolean
	//               m  ,   n  ->  renvoie vrai si n est a la fin de m, faux sinon
	static boolean termine(int m, int n) {
		while (n != 0) {
			if (m % 10 != n % 10) {
				return false;
			}
			m = m / 10;
			n = n / 10;
		}
		return true;
	}

	
	//   contient  ::  int  x  int  ->  boolean
	//                  m   ,   n   ->  renvoie vrai si m contient n, faux sinon
	static boolean contient(int m, int n) {
		if (n == 0) {
			while (m / 10 != 0) {
				if (m % 10 == 0) {
					return true;
				}
			}
			return false;
		}
		while (m != 0) {
			if (termine(m, n)) {
				return true;
			} else {
				m = m / 10;
			}
		}
		return false;
	}
	
	
	
    //    imageMiroir  ::  int   ->  int 
	//                      m    ->  renvoie l'image miroir de l'ecriture decimale d'un entier m
	static int imageMiroir(int m) {
		int result = m % 10;
		m = m / 10;
		while (m != 0) {
			result = result * 10 + m % 10;
			m = m / 10;
		}
		return result;
	}

	public static void main(String[] args) {

		
		// test de termine :
		System.out.println(termine(941, 41)); // true
		System.out.println(termine(941, 0)); // true
		System.out.println(termine(9418, 41)); // false
		// test de contient :
		System.out.println(contient(9413, 41)); // true
		System.out.println(contient(9413, 43)); // false

		
		// test de imageMiroir :
		System.out.println(imageMiroir(9418)); // 8149

	}

}
