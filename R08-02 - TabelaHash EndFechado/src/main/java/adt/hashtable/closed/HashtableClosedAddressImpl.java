package adt.hashtable.closed;

import util.Util;

import java.util.LinkedList;

import adt.hashtable.hashfunction.*;

public class HashtableClosedAddressImpl<T> extends AbstractHashtableClosedAddress<T> {

	/**
	 * A hash table with closed address works with a hash function with closed
	 * address. Such a function can follow one of these methods: DIVISION or
	 * MULTIPLICATION. In the DIVISION method, it is useful to change the size of
	 * the table to an integer that is prime. This can be achieved by producing such
	 * a prime number that is bigger and close to the desired size.
	 * 
	 * For doing that, you have auxiliary methods: Util.isPrime and getPrimeAbove as
	 * documented bellow.
	 * 
	 * The length of the internal table must be the immediate prime number greater
	 * than the given size (or the given size, if it is already prime). For example,
	 * if size=10 then the length must be 11. If size=20, the length must be 23. You
	 * must implement this idea in the auxiliary method getPrimeAbove(int size) and
	 * use it.
	 * 
	 * @param desiredSize
	 * @param method
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashtableClosedAddressImpl(int desiredSize, HashFunctionClosedAddressMethod method) {
		int realSize = desiredSize;

		if (method == HashFunctionClosedAddressMethod.DIVISION) {
			realSize = this.getPrimeAbove(desiredSize); // real size must the
														// the immediate prime
														// above
		}
		initiateInternalTable(realSize);
		HashFunction function = HashFunctionFactory.createHashFunction(method, realSize);
		this.hashFunction = function;
	}

	// AUXILIARY
	/**
	 * It returns the prime number that is closest (and greater) to the given
	 * number. If the given number is prime, it is returned. You can use the method
	 * Util.isPrime to check if a number is prime.
	 */
	int getPrimeAbove(int number) {
		while (!Util.isPrime(number)) {
			number++;
		}
		return number;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insert(T element) {
		if (element != null) {
			// Receives the hash for this element
			int key = getHash(element);
			if (this.table[key] == null) {
				// There is no element in this position,
				// then a new list is assigned
				LinkedList<T> list = new LinkedList<>();
				list.add(element);
				this.table[key] = list;
			} else {
				// There is an element in this position already,
				// then it checks if the element is already in the list
				// and increases the collisions
				if (search(element) == null) {
					((LinkedList<T>) this.table[key]).add(element);
					this.COLLISIONS++;
				}
			}
			this.elements++;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void remove(T element) {
		if (element != null && search(element) != null) {
			// Receives the hash for this element
			int key = getHash(element);
			((LinkedList<T>) this.table[key]).remove(element);
			this.elements--;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T search(T element) {
		T result = null;
		if (element != null) {
			int key = getHash(element);
			if (this.table[key] != null && ((LinkedList<T>) this.table[key]).contains(element)) {
				result = element;
			}
		}
		return result;
	}

	@Override
	public int indexOf(T element) {
		int index = -1;
		if (element != null) {
			// Receives the hash for this element
			int key = getHash(element);
			// Checks whether the element exists in that position
			// and whether the element is contained in the list
			if (this.table[key] != null && search(element) != null) {
				index = key;
			}
		}
		return index;
	}

	private int getHash(T element) {
		return ((HashFunctionClosedAddress<T>) this.hashFunction).hash(element);
	}

}
