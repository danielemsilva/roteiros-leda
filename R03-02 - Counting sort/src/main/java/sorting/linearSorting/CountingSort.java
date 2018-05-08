package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
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
		// Number of positions reserved
		int maxValue = searchMax(array);
		// This array will store the amount of elements in their respective indexes
		Integer[] countArray = new Integer[maxValue + 1];
		// This array will store the ordered elements
		Integer[] outputArray = new Integer[array.length];
		
		// Initializes the array with elements 0
		for (int i = 0; i < countArray.length; i++) {
			countArray[i] = 0;
		}
		// Stores the quantity of values in the corresponding index
		for (int i = 0; i < array.length; i++) {
			int index = array[i];
			countArray[index]++;
		}
		// Calculates the sum of a value with its previous
		for (int i = 1; i < countArray.length; i++) {
			countArray[i] = countArray[i] + countArray[i - 1];
		}
		// Build the output looking for indexes in the countArray
		for (int i = 0; i < array.length; i++) {
			int index = countArray[array[i]] - 1;
			outputArray[index] = array[i];
			// Decreases the value
			countArray[array[i]]--;
		}
		// Copy the outputArray to the array 
		for (int i = 0; i < outputArray.length; i++) {
			array[i] = outputArray[i];
		}
	}

	private int searchMax(Integer[] array) {
		// Search for the maximum value
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < array.length; i++) {
			if (array[i] >= max) {
				max = array[i];
			}
		}
		return max;
	}
	
}
