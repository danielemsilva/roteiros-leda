package adt.rbtree;

import java.util.ArrayList;

import adt.bst.BSTImpl;
import adt.bt.Util;
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
		return verifyChildrenRedNodes((RBNode<T>) root);
	}

	private boolean verifyChildrenRedNodes(RBNode<T> node) {
		if (!node.isEmpty() && node.getColour().equals(Colour.RED)) {
			RBNode<T> leftNode = (RBNode<T>) node.getLeft();
			RBNode<T> rightNode = (RBNode<T>) node.getRight();

			if (leftNode.getColour().equals(Colour.RED) || rightNode.getColour().equals(Colour.RED)) {
				return false;
			} else {
				return verifyChildrenRedNodes(leftNode) && verifyChildrenRedNodes(rightNode);
			}
		}
		return true;
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
		if (value != null) {
			insert(value, (RBNode<T>) root);
		}
	}

	private void insert(T value, RBNode<T> node) {
		if (node.isEmpty()) {
			node.setData(value);
			node.setLeft(new RBNode<T>());
			node.setRight(new RBNode<T>());
			
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
			node.setColour(Colour.RED);

			fixUpCase1(node);
		} else if (value.compareTo(node.getData()) < 0) {
			insert(value, (RBNode<T>) node.getLeft());
		} else {
			insert(value, (RBNode<T>) node.getRight());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public RBNode<T>[] rbPreOrder() {
		ArrayList<RBNode<T>> list = new ArrayList<>();
		rbPreOrder((RBNode<T>) root, list);

		RBNode<T>[] array = new RBNode[list.size()];
		return list.toArray(array);

	}

	private void rbPreOrder(RBNode<T> node, ArrayList<RBNode<T>> list) {
		if (!node.isEmpty()) {
			list.add(node);
			rbPreOrder((RBNode<T>) node.getLeft(), list);
			rbPreOrder((RBNode<T>) node.getRight(), list);
		}
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
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> grand = (RBNode<T>) parent.getParent();
		RBNode<T> uncle;

		if (grand.getLeft().equals(parent)) {
			uncle = (RBNode<T>) grand.getRight();
		} else {
			uncle = (RBNode<T>) grand.getLeft();
		}

		if (uncle.getColour().equals(Colour.RED)) {
			uncle.setColour(Colour.BLACK);
			parent.setColour(Colour.BLACK);
			grand.setColour(Colour.RED);
			fixUpCase1(grand);
		} else {
			fixUpCase4(node);
		}
	}

	protected void fixUpCase4(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> grand = (RBNode<T>) parent.getParent();

		if (grand.getLeft().equals(parent) && parent.getRight().equals(node)) {
			RBNode<T> aux = (RBNode<T>) Util.leftRotation(parent);
			if (aux.getParent() == null) {
				this.root = aux;
			}
			fixUpCase5((RBNode<T>) node.getLeft());
		} 
		else if (grand.getRight().equals(parent) && parent.getLeft().equals(node)) {
			RBNode<T> aux = (RBNode<T>) Util.rightRotation(parent);
			if (aux.getParent() == null) {
				this.root = aux;
			}
			fixUpCase5((RBNode<T>) node.getRight());
		} 
		else {
			fixUpCase5(node);
		}
	}

	protected void fixUpCase5(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		parent.setColour(Colour.BLACK);
		((RBNode<T>) parent.getParent()).setColour(Colour.RED);
		
		RBNode<T> aux;
		if (parent.getLeft().equals(node)) {
			aux = (RBNode<T>) Util.rightRotation((RBNode<T>) parent.getParent());
		} else {
			aux = (RBNode<T>) Util.leftRotation((RBNode<T>) parent.getParent());
		}
		
		if (aux.getParent() == null) {
			this.root = aux;
		}
	}
}
