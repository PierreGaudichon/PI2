import java.util.ArrayList;
import java.util.ListIterator;

public class TClist<C extends Comparable<C>, V> extends TableCorrespondance<C, V>{
	
	private class Paire<C, V> {
		Paire(C c, V v){
			this.cle = c;
			this.val = v;
		}
		C cle;
		V val;
	}
	
	ArrayList<Paire<C, V>> list = new ArrayList<Paire<C, V>>();
	
	public void associe(C cle, V val){
		System.out.println(get(cle));
		list.remove(new Paire<C, V>(cle, get(cle)));
		Paire<C, V> p = new Paire<C, V>(cle, val);
		list.add(p);
	}
	
	public void supprime(C cle) {
		ListIterator<Paire<C, V>> i = list.listIterator();
		while(i.hasNext()){
			Paire<C, V> p = i.next();
			if(p.cle == cle){
				list.remove(p);
			}
		}
	}
	
	public V get(C cle) {
		ListIterator<Paire<C, V>> i = list.listIterator();
		while(i.hasNext()){
			Paire<C, V> p = i.next();
			if(p.cle.equals(cle)){
				return p.val;
			}
		}
		return null;
	}

	
	public static void main(String[] args){
		TClist<String, Integer> list = new TClist<>();
		list.associe("foo", 4);
		System.out.println(list.get("foo"));
		list.associe("foo", 5);
		System.out.println(list.get("foo"));
		
	}

}