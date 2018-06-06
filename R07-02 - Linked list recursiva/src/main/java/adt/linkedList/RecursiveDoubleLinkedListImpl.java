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
		if (this.previous == null) {
			this.previous = new RecursiveDoubleLinkedListImpl<>();
			this.previous.data = element;
			this.previous.next = this;
		} else {
			this.previous.insertFirst(element);
		}
	}
	
	@Override
	public void insert(T element) {
		if (this.next == null) {
			RecursiveDoubleLinkedListImpl<T> next = new RecursiveDoubleLinkedListImpl<>();
			next.data = element;
			next.previous = this;
		} else {
			this.next.insert(element);
		}
	}

	@Override
	public void removeFirst() {
		if (this.previous.data == null) {
			RecursiveDoubleLinkedListImpl<T> previousNext = new RecursiveDoubleLinkedListImpl<>();
			previousNext.data = this.next.data;
			previousNext.next = this.next.next;
			previousNext.previous = new RecursiveDoubleLinkedListImpl<>();
		} else {
			this.previous.removeFirst();
		}
	}

	@Override
	public void removeLast() {
		RecursiveDoubleLinkedListImpl<T> next = (RecursiveDoubleLinkedListImpl<T>) this.next;
		if (next == null) {
			this.previous.next = next;
		} else {
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
