package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		// Checks if the head is empty.
		return getHead().isNIL();
	}

	@Override
	public int size() {
		SingleLinkedListNode<T> aux = getHead();
		int count = 0;
		// Counts all nodes until arriving in some empty.
		while (!aux.isNIL()) {
			count++;
			aux = aux.getNext();
		}
		return count;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> aux = getHead();
		T found = null;
		while (!aux.isNIL() && found == null) {
			if (aux.getData().equals(element)) {
				found = element;
			}
			aux = aux.getNext();
		}
		return found;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			SingleLinkedListNode<T> aux = getHead();
			// Look for the node that is empty.
			while (!aux.isNIL()) {
				aux = aux.getNext();
			}
			// Edits the value of the found node.
			aux.setData(element);
			// Indicates that the next node is empty.
			aux.setNext(new SingleLinkedListNode<>());
		}
	}

	@Override
	public void remove(T element) {
		if (search(element) != null) {
			SingleLinkedListNode<T> aux = getHead();
			SingleLinkedListNode<T> prev = aux;
			while (!aux.getData().equals(element)) {
				// Saves the previous node.
				prev = aux;
				aux = aux.getNext();
			}
			// Saves the next of the previous as the next of the deleted.
			prev.setNext(aux.getNext());
		}
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		// Creates a list of objects that will be modified to the node type.
		T[] array = (T[]) new Object[this.size()];
		// Index that will go through the created array.
		int index = 0;
		SingleLinkedListNode<T> aux = head;
		while (!aux.isNIL()) {
			array[index] = aux.getData();
			index++;
			aux = aux.getNext();
		}
		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}
	
}
