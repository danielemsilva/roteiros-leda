package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> 
	implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {
		super();
		this.previous = null;
	}

	public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insertFirst(T element) {
		if (this.data == null) {
			this.data = element;
			this.previous = new RecursiveDoubleLinkedListImpl<>();
		} else {
			this.previous.insertFirst(element);
		}
	}
	
	@Override
	public void insert(T element) {
		if (this.data == null) {
			this.data = element;
			this.next = new RecursiveSingleLinkedListImpl<>();
		} else {
			this.next.insert(element);
		}
	}

	@Override
	public void removeFirst() {
		if (this.data == null) {
			this.next = null;
		} else {
			this.previous.removeFirst();
		}
	}

	@Override
	public void removeLast() {
		if (this.data == null) {
			this.previous = null;
		} else {
			RecursiveDoubleLinkedListImpl<T> next = (RecursiveDoubleLinkedListImpl<T>) this.next;
			next.data = this.next.data;
			next.previous = this;
			next.removeLast();
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
