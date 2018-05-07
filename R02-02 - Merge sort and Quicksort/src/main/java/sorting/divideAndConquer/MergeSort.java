package sorting.divideAndConquer;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	private void merge(T[] array, int leftIndex, int middle, int rightIndex) {
		// Creates an array equal to the original
		T[] auxArray = Arrays.copyOf(array, array.length);
		// This index is the beginning of the left array
		int i = leftIndex;
		// This index is the beginning of the right array
		int j = middle + 1;
		// This index will go through the array
		int k = leftIndex;
		// Here goes through the two arrays looking for the lowest value
		while (i <= middle && j <= rightIndex) {
			// The lowest is in the left array
			if (auxArray[i].compareTo(auxArray[j]) <= 0) {
				array[k] = auxArray[i];
				i++; // Increments the index to the next position of this array
			} else {
				array[k] = auxArray[j];
				j++;
			}
			k++;
		}
		// Add the rest of the left array, if there is any
		while (i <= middle) {
			array[k] = auxArray[i];
			i++;
			k++;
		}
		// Add the rest of the right array, if there is any
		while (j <= rightIndex) {
			array[k] = auxArray[j];
			j++;
			k++;
		}
	}

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		// Exceptional cases
		if (array == null || array.length == 0) {
			return;
		}
		if (leftIndex > rightIndex) {
			return;
		}
		if (leftIndex < 0 || rightIndex >= array.length) {
			return;
		}
		// leftIndex should not exceed the rightIndex
		if (leftIndex < rightIndex) {
			// Finds the middle position
			int middle = (leftIndex + rightIndex) / 2;
			// Separates the first half of the array
			sort(array, leftIndex, middle);
			// Separates the second half of the array
			sort(array, middle + 1, rightIndex);
			// Merges parts of the array to sort
			merge(array, leftIndex, middle, rightIndex);
		}
	}
}
