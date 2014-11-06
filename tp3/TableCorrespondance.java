
public abstract class TableCorrespondance<C extends Comparable<C>, V> {

	public abstract void associe (C cle, V val);
	public abstract void  supprime (C cle);
	public abstract V get (C cle);

}
