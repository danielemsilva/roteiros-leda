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
		return height(this.getRoot());
	}

	protected int height(BSTNode<T> node) {
		if (node.isEmpty()) {
			return -1;
		} else {
			int height;
			int right = height((BSTNode<T>) node.getRight());
			int left = height((BSTNode<T>) node.getLeft());
			if (right >= left) {
				height = right;
			} else {
				height = left;
			}
			return 1 + height;
		}
	}

	@Override
	public BSTNode<T> search(T element) {
		if (element == null || this.isEmpty()) {
			return new BSTNode<T>();
		} else {
			return search(element, this.getRoot());
		}
	}

	private BSTNode<T> search(T element, BSTNode<T> node) {
		if (node.getData().equals(element)) {
			return node;
		} else if (!node.getLeft().isEmpty() && node.getData().compareTo(element) > 0) {
			return search(element, (BSTNode<T>) node.getLeft());
		} else if (!node.getRight().isEmpty() && node.getData().compareTo(element) < 0) {
			return search(element, (BSTNode<T>) node.getRight());
		} else {
			return new BSTNode<T>();
		}
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			insert(this.root, element);
		}
	}

	private void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			// Defines left and right nodes
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			// Defines the parent of the left and right nodes
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		} else if (node.getData().compareTo(element) > 0) {
			insert((BSTNode<T>) node.getLeft(), element);
		} else if (node.getData().compareTo(element) < 0) {
			insert((BSTNode<T>) node.getRight(), element);
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if (this.isEmpty()) {
			return null;
		}
		return maximum(this.root);
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if (node.getRight().isEmpty()) {
			return node;
		}
		return maximum((BSTNode<T>) node.getRight());
	}

	@Override
	public BSTNode<T> minimum() {
		if (this.isEmpty()) {
			return null;
		}
		return minimum(this.root);
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if (node.getLeft().isEmpty()) {
			return (BSTNode<T>) node;
		}
		return minimum((BSTNode<T>) node.getLeft());
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = search(element);
		if (element == null || node.isEmpty()) {
			return null;
		}
		if (!node.getRight().isEmpty()) {
			return minimum((BSTNode<T>) node.getRight());
		} else {
			return sucessor(node, (BSTNode<T>) node.getParent());
		}
	}

	private BSTNode<T> sucessor(BSTNode<T> node, BSTNode<T> parent) {
		if (parent == null) {
			return null;
		}
		if (parent.getData().compareTo(node.getData()) > 0) {
			return (BSTNode<T>) parent;
		}
		return sucessor(node, (BSTNode<T>) parent.getParent());
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = search(element);
		if (element == null || node.isEmpty()) {
			return null;
		} else {
			if (!node.getLeft().isEmpty()) {
				return maximum((BSTNode<T>) node.getLeft());
			} else {
				return predecessor(node, (BSTNode<T>) node.getParent());
			}
		}
	}

	private BSTNode<T> predecessor(BSTNode<T> node, BSTNode<T> parent) {
		if (parent == null) {
			return null;
		}
		if (parent.getData().compareTo(node.getData()) < 0) {
			return (BSTNode<T>) parent;
		}
		return predecessor(node, (BSTNode<T>) parent.getParent());
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		if (element != null && !node.isEmpty()) {
			remove(node);
		}
	}

	private void remove(BSTNode<T> node) {
		// Leaf node
		if (node.isLeaf()) {
			node.setData(null);
			node.setLeft(null);
			node.setRight(null);
		}
		// Only one child left. Remove right node
		else if (node.getLeft().isEmpty()) {
			node.setData(node.getRight().getData());

			node.setLeft(node.getRight().getLeft());
			node.setRight(node.getRight().getRight());

			node.getRight().setParent(node);
			node.getLeft().setParent(node);
		}
		// Only one child right. Remove left node
		else if (node.getRight().isEmpty()) {
			node.setData(node.getLeft().getData());

			node.setRight(node.getLeft().getRight());
			node.setLeft(node.getLeft().getLeft());

			node.getRight().setParent(node);
			node.getLeft().setParent(node);
		} else {
			T data = node.getData();
			BSTNode<T> sucessor = sucessor(data);
			node.setData(sucessor.getData());
			sucessor.setData(data);
			remove((BSTNode<T>) sucessor);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		preOrder(this.root, array, 0);
		return array;
	}

	private int preOrder(BSTNode<T> node, T[] array, int index) {
		if (!node.isEmpty()) {
			array[index] = node.getData();
			index = preOrder((BSTNode<T>) node.getLeft(), array, ++index);
			index = preOrder((BSTNode<T>) node.getRight(), array, index);
		}
		return index;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] order() {
		T[] array = (T[]) new Comparable[this.size()];
		order(this.root, array, 0);
		return array;
	}

	private int order(BSTNode<T> node, T[] array, int index) {
		if (!node.isEmpty()) {
			index = order((BSTNode<T>) node.getLeft(), array, index);
			array[index++] = node.getData();
			index = order((BSTNode<T>) node.getRight(), array, index);
		}
		return index;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] postOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		postOrder(this.root, array, 0);
		return array;
	}

	private int postOrder(BSTNode<T> node, T[] array, int index) {
		if (!node.isEmpty()) {
			index = postOrder((BSTNode<T>) node.getLeft(), array, index);
			index = postOrder((BSTNode<T>) node.getRight(), array, index);
			array[index++] = node.getData();
		}
		return index;
	}

	/**
	 * This method is already implemented using recursion. You must understand how
	 * it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
