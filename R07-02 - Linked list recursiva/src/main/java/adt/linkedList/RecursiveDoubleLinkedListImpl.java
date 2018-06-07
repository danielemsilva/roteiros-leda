package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {
	}

	public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			if (isEmpty()) {
				insert(element);
			} else {
				RecursiveDoubleLinkedListImpl<T> temp = new RecursiveDoubleLinkedListImpl<T>();
				temp.data = this.data;
				temp.next = this.next;
				this.next = temp;
				this.data = element;
				((RecursiveDoubleLinkedListImpl<T>) this.next).previous = this;
			}
		}
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()) {
				RecursiveDoubleLinkedListImpl<T> temp = new RecursiveDoubleLinkedListImpl<T>();
				temp.previous = this;
				this.next = temp;
				this.data = element;
			} else {
				this.next.insert(element);
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			if (this.next.isEmpty()) {
				this.data = null;
				this.next = null;
			} else {
				this.data = this.next.data;
				this.next = this.next.next;
			}
		}
	}

	@Override
	public void removeLast() {
		if(!isEmpty()) {
			if(this.next.isEmpty()) {
				this.data = null;
				this.next = null;
			} else {
				((RecursiveDoubleLinkedListImpl<T>) this.next).removeLast();
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
