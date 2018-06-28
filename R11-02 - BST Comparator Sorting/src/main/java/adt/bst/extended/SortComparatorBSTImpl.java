package adt.bst.extended;

import java.util.Comparator;
import adt.bst.BSTImpl;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em
 * suas funcionalidades e possui um metodo de ordenar um array dado como
 * parametro, retornando o resultado do percurso desejado que produz o array
 * ordenado.
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;
	private BSTImpl<T> tree;

	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
		tree = new BSTImpl<>();
	}

	@Override
	public T[] sort(T[] array) {
		for (T e : array) {
			this.tree.insert(e);
		}
		return this.tree.order();
	}

	@Override
	public T[] reverseOrder() {
		return this.tree.reverseOrder();
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

}
