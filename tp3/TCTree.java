
public class TCTree<C extends Comparable<C>, V> extends TableCorrespondance<C, V> {
	
	private class Paire<C extends Comparable<C>, V> {
		Paire(C c, V v){
			this.cle = c;
			this.val = v;
		}
		C cle;
		V val;
	}
	
	private class ABR<C extends Comparable<C>, V>{
		
		ABR<C, V> g;
		ABR<C, V> d;
		C cle;
		V val;
		
		ABR(){}
		
		ABR(C cle, V val) {
			this.cle = cle;
			this.val = val;
		}
		
		public void insert(C c, V v){
			int comp = cle.compareTo(c);
			if(comp < 0) {
				g.insert(c, v);
			}
			else if(comp > 0){
				d.insert(c, v);
			}
			else {
				val = v;
			}
		}
		
		public void delete(C c){
			int comp = cle.compareTo(c);
			if(comp == 0){
				this = null;
			}
		}
		
		public V get(C c){
			int comp = c.compareTo(cle);
			if(comp == 0){
				return val;
			}
			else if(comp < 0) {
				return g.get(c);
			}
			else {
				return d.get(c);
			}
		}
	}
	
	
	private ABR<C, V> abr = new ABR();
	
	public void associe (C cle, V val){
		abr.insert(cle, val);
	}
	
	public void  supprime (C cle){
		abr.delete(cle);
	}
	
	public V get(C cle){
		return abr.get(cle);
	}
	
	
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
