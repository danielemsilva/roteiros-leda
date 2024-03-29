package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BTNode<T> aux = node.getRight();

		node.setRight(aux.getLeft());
		aux.getLeft().setParent(node);
		aux.setParent(node.getParent());
		aux.setLeft(node);
		node.setParent(aux);

		return (BSTNode<T>) aux;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BTNode<T> aux = node.getLeft();

		node.setLeft(aux.getRight());
		aux.getRight().setParent(node);
		aux.setParent(node.getParent());
		aux.setRight(node);
		node.setParent(aux);

		return (BSTNode<T>) aux;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
