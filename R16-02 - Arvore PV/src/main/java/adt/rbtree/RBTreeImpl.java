package adt.rbtree;

import adt.bst.BSTImpl;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements RBTree<T> {

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}

	protected int blackHeight() {
		return blackHeight((RBNode<T>) root);
	}

	private int blackHeight(RBNode<T> node) {
		if (node.isEmpty()) {
			return 0;
		}
		if (node.getColour().equals(Colour.BLACK)) {
			return 1 + Math.max(blackHeight((RBNode<T>) node.getLeft()), blackHeight((RBNode<T>) node.getRight()));
		} else {
			return Math.max(blackHeight((RBNode<T>) node.getLeft()), blackHeight((RBNode<T>) node.getRight()));
		}
	}

	protected boolean verifyProperties() {
		boolean resp = verifyNodesColour() && verifyNILNodeColour() && verifyRootColour() && verifyChildrenOfRedNodes()
				&& verifyBlackHeight();

		return resp;
	}

	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed by
	 * the type Colour.
	 */
	private boolean verifyNodesColour() {
		return true; // already implemented
	}

	/**
	 * The colour of the root must be black.
	 */
	private boolean verifyRootColour() {
		return ((RBNode<T>) root).getColour() == Colour.BLACK; // already implemented
	}

	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour() {
		return true; // already implemented
	}

	/**
	 * Verifies the property for all RED nodes: the children of a red node must be
	 * BLACK.
	 */
	private boolean verifyChildrenOfRedNodes() {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	/**
	 * Verifies the black-height property from the root. The method blackHeight
	 * returns an exception if the black heights are different.
	 */
	private boolean verifyBlackHeight() {
		return verifyBlackHeight((RBNode<T>) root);
	}
	
	private boolean verifyBlackHeight(RBNode<T> node) {
		if (node.isEmpty()) {
			return true;
		}
		
		RBNode<T> leftNode = (RBNode<T>) node.getLeft();
		RBNode<T> rightNode = (RBNode<T>) node.getRight();
		if (blackHeight(leftNode) == blackHeight(rightNode)) {
			return verifyBlackHeight(leftNode) && verifyBlackHeight(rightNode);
		}
		return false;
	}

	@Override
	public void insert(T value) {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public RBNode<T>[] rbPreOrder() {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	// FIXUP methods
	protected void fixUpCase1(RBNode<T> node) {
		if (node.equals(root)) {
			node.setColour(Colour.BLACK);
		} else {
			fixUpCase2(node);
		}
	}

	protected void fixUpCase2(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		if (parent.getColour().equals(Colour.RED)) {
			fixUpCase3(node);
		}
	}

	protected void fixUpCase3(RBNode<T> node) {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	protected void fixUpCase4(RBNode<T> node) {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	protected void fixUpCase5(RBNode<T> node) {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}
}
