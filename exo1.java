public class exo1 {
	
	
	// estPresentEn :: Int x String (length > 0) x String -> Boolean
	//					k  ,   s1,      s    -> s1 est present dans s a l'indice k.
	static boolean estPresentEn(int k, String s1, String s) {
		if ((s1.length() > s.length()) || (k > s.length())) {
			return false;
		}
		boolean result = true;
		for (int i = k; i < k + s1.length(); i++) {
			result = result && (s.charAt(i) == s1.charAt(i - k));
		}
		return result;
	
	}
	
	
	// indiceDe :: String x String -> Int
	//				s1    ,  s     -> Renvoie l'indice de la premiere occurence de s1 dans s, si s1 n'est pas present : -1. 
	static int indiceDe (String s1, String s){
		for(int i = 0; i < s.length(); i++){
			if(estPresentEn(i, s1, s)){
				return i;
			}
		}
		return -1;
	}


	// TESTS
	public static void main(String[] args) {
		System.out.println(estPresentEn(0, "bon", "bonjour bonsoir")); // true
		System.out.println(estPresentEn(5, "bon", "bonjour bonsoir")); // false
		System.out.println(estPresentEn(8, "bon", "bonjour bonsoir")); // true
		System.out.println(estPresentEn(56, "bon", "bonjour bonsoir")); // false
		
		System.out.println( indiceDe("bon", "bonjour bonsoir") ); // 0
		System.out.println( indiceDe("bon", "onjour bonsoir") ); // 7

	}

}
