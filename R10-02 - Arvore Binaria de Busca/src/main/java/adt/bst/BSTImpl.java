package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public BSTNode<T> search(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public void insert(T element) {
		BSTNode<T> newNode = new BSTNode<T>();
		newNode.setData(element);
		if (this.root.isEmpty()) {
			this.root = newNode;
		} else {
			BSTNode<T> aux = this.root;
			while (aux != null) {
				if (aux.getData().compareTo(element) < 0 ) {
					aux = (BSTNode<T>) aux.getRight();
				} else {
					aux = (BSTNode<T>) aux.getLeft();
				}
			}
			aux = newNode;
		}
	}

	@Override
	public BSTNode<T> maximum() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public BSTNode<T> minimum() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public void remove(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] preOrder() {
		BSTNode<T> aux = new BSTNode<T>();
		T[] toArray = (T[]) new Object[size()];
		int index = -1;
		
		while (aux != null) {
			toArray[++index] = aux.getData();
			toArray[++index] = aux.getLeft().getData();
			toArray[++index] = aux.getRight().getData();
		}
		
		return toArray;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] order() {
		BSTNode<T> aux = new BSTNode<T>();
		T[] toArray = (T[]) new Object[size()];
		int index = -1;
		
		while (aux != null) {
			toArray[++index] = aux.getLeft().getData();
			toArray[++index] = aux.getData();
			toArray[++index] = aux.getRight().getData();
		}
		
		return toArray;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] postOrder() {
		BSTNode<T> aux = new BSTNode<T>();
		T[] toArray = (T[]) new Object[size()];
		int index = -1;
		
		while (aux != null) {
			toArray[++index] = aux.getLeft().getData();
			toArray[++index] = aux.getRight().getData();
			toArray[++index] = aux.getData();
		}
		
		return toArray;
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		if (!node.isEmpty()) {
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
