package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {
		this.data = null;
		this.next = null;
	}

	public RecursiveSingleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public int size() {
		if (this.data == null) {
			return 0;
		}
		return 1 + this.next.size();
	}

	@Override
	public T search(T element) {
		if (this.data == null || this.data.equals(element)) {
			return this.data;
		}
		return this.next.search(element);
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
	public void remove(T element) {
		if (this.data != null) {
			if (this.data.equals(element)) {
				this.data = this.next.data;
				this.next = this.next.next;
			} else {
				this.next.remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[this.size()];
		toArray(array, 0, this);
		return array;
	}
	
	private void toArray(T[] array, int index, RecursiveSingleLinkedListImpl<T> list) {
		if (!list.isEmpty()) {
			array[index] = list.data;
			toArray(array, index + 1, list.next);
		}
	}
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
