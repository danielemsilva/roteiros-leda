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
		return this.data == null;
	}

	@Override
	public int size() {
		if (isEmpty()) {
			return 0;
		}
		return 1 + this.getNext().size();
	}

	@Override
	public T search(T element) {
		if (isEmpty() || element == null) {
			return null;
		}
		if (this.data.equals(element)) {
			return element;
		}
		return this.next.search(element);
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()) {
				this.data = element;
				this.next = new RecursiveSingleLinkedListImpl<T>();
			} else {
				this.next.insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null && !isEmpty()) {
			if (this.getData().equals(element)) {
				this.data = this.next.data;
				this.next = this.next.next;
			} else {
				this.next.remove(element);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[this.size()];
		return toArray(array, 0, this);
	}

	private T[] toArray(T[] array, int index, RecursiveSingleLinkedListImpl<T> temp) {
		if (temp.isEmpty()) {
			return array;
		} else {
			array[index] = temp.data;
			return toArray(array, index + 1, temp.next);
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
