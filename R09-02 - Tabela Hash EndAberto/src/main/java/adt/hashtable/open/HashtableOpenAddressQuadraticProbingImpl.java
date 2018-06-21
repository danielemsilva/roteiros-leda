package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size, HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (element != null && search(element) == null) {
			if (this.isFull()) {
				throw new HashtableOverflowException();
			} else {
				int probe = 0;
				int posicao = this.getHash(element, probe);

				while (this.table[posicao] != null && !this.table[posicao].equals(new DELETED())) {
					probe++;
					posicao = this.getHash(element, probe);
					this.COLLISIONS++;
				}
				this.table[posicao] = element;
				this.elements++;
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null && this.search(element) != null) {
			int index = this.indexOf(element);
			this.table[index] = new DELETED();
			this.elements--;
		}
	}

	@Override
	public T search(T element) {
		T result = null;
		if (this.indexOf(element) != -1) {
			result = element;
		}
		return result;
	}

	@Override
	public int indexOf(T element) {
		if (!this.isEmpty() && element != null) {
			int probe = 0;
			int index = this.getHash(element, probe);

			while (this.table[index] != null && probe < this.capacity()) {
				if (this.table[index].equals(element)) {
					return index;
				}
				probe++;
				index = this.getHash(element, probe);
			}
		}
		return -1;
	}

	private int getHash(T element, int probe) {
		return ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, probe);
	}

}
