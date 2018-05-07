package sorting.divideAndConquer.threeWayQuicksort;

import sorting.AbstractSorting;
import util.Util;

public class ThreeWayQuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

	/**
	 * No algoritmo de quicksort, selecionamos um elemento como pivot, particionamos
	 * o array colocando os menores a esquerda do pivot e os maiores a direita do
	 * pivot, e depois aplicamos a mesma estrategia recursivamente na particao a
	 * esquerda do pivot e na particao a direita do pivot.
	 * 
	 * Considerando um array com muitoe elementos repetidos, a estrategia do
	 * quicksort pode ser otimizada para lidar de forma mais eficiente com isso.
	 * Essa melhoria eh conhecida como quicksort tree way e consiste da seguinte
	 * ideia: - selecione o pivot e particione o array de forma que * arr[l..i]
	 * contem elementos menores que o pivot * arr[i+1..j-1] contem elementos iguais
	 * ao pivot. * arr[j..r] contem elementos maiores do que o pivot.
	 * 
	 * Obviamente, ao final do particionamento, existe necessidade apenas de ordenar
	 * as particoes contendo elementos menores e maiores do que o pivot. Isso eh
	 * feito recursivamente.
	 **/
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
		if (leftIndex < rightIndex) {
			// This partition is the index of the pivot, ensuring that all
			// values greater than the pivot are to its right
			int particionRight = separate(array, leftIndex, rightIndex);
			// This partition ensures that all values smaller than the pivot
			// are to its left
			int particionLeft = separateWithPivot(array, leftIndex, particionRight);
			// Here is the first part of the array, with values smaller than the pivot
			sort(array, leftIndex, particionLeft - 1);
			// In this part are the values greater than the pivot
			sort(array, particionRight + 1, rightIndex);
		}
	}

	private int separate(T[] array, int leftIndex, int rightIndex) {
		// Assumes that the smallest element is in the last position
		T pivot = array[rightIndex];
		int j = leftIndex - 1;
		// Here will scroll through the array looking for the values smaller
		// or equal to the pivot and switching to the first positions
		for (int i = leftIndex; i < rightIndex; i++) {
			if (array[i].compareTo(pivot) <= 0) {
				j++;
				Util.swap(array, i, j);
			}
		}
		Util.swap(array, j + 1, rightIndex);
		// This index indicates the new position of the ordered pivot
		return j + 1;
	}

	private int separateWithPivot(T[] array, int leftIndex, int rightIndex) {
		// Assumes that the smallest element is in the last position
		T pivot = array[rightIndex];
		int j = leftIndex - 1;
		// Here will scroll through the array looking only for the values smaller
		// to the pivot and switching to the first positions
		for (int i = leftIndex; i < rightIndex; i++) {
			if (array[i].compareTo(pivot) < 0) {
				j++;
				Util.swap(array, i, j);
			}
		}
		// This index indicates the new position of the ordered pivot
		return j + 1;
	}

}
