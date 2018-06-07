package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new RecursiveDoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (element != null) {
			if (this.top.size() < this.size) {
				this.top.insert(element);
			} else {
				throw new StackOverflowException();
			}
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (this.top.size() > 0) {
			T aux = top();
			this.top.removeLast();
			return aux;
		} else {
			throw new StackUnderflowException();
		}
	}

	@Override
	public T top() {
		if (this.top.isEmpty()) {
			return null;
		}
		return top((RecursiveDoubleLinkedListImpl<T>) this.top);
	}

	private T top(RecursiveDoubleLinkedListImpl<T> temp) {
		if (temp.getNext().isEmpty()) {
			return temp.getData();
		}
		return top((RecursiveDoubleLinkedListImpl<T>) temp.getNext());
	}

	@Override
	public boolean isEmpty() {
		return ((RecursiveDoubleLinkedListImpl<T>) this.top).isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.size == ((RecursiveDoubleLinkedListImpl<T>) this.top).size();
	}

}
