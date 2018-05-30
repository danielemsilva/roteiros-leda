package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		this.last = new DoubleLinkedListNode<>();
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			// Creates a new head that has previous.
			DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<>(this.head.getData(),
					(DoubleLinkedListNode<T>) this.head.getNext(), new DoubleLinkedListNode<>());
			// Creates a new node with previous being the head.
			DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>(element, newHead,
					new DoubleLinkedListNode<>());
			if (!this.head.isNIL()) {
				newHead.setPrevious(newNode);
			}
			this.head = newNode; // Changes head reference of superclass.
			if (this.last.isNIL()) {
				this.last = newNode; // If the list is empty, last will be the new node.
			}
		}
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			// Creates a new node with previous being the last.
			DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>(element, 
					new DoubleLinkedListNode<>(), this.last);
			if (!this.last.isNIL()) {
				this.last.setNext(newNode);
			}
			this.last = newNode; // Changes last reference.
			if (this.head.isNIL()) {
				this.head = newNode; // If the list is empty, head will be the new node.
			}
		}
	}

	@Override
	public void removeFirst() {
		if (size() != 0) {
			// Creates a new head that has previous.
			DoubleLinkedListNode<T> headNext = (DoubleLinkedListNode<T>) this.head.getNext();
			headNext.setPrevious(new DoubleLinkedListNode<>());
			this.head = headNext;
		}
	}

	@Override
	public void removeLast() {
		if (size() != 0) {
			this.last.previous.setNext(this.last.getNext());
			this.last = this.last.previous;
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
