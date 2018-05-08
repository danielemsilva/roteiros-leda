package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array == null || array.length == 0) {
			return;
		}
		if (leftIndex > rightIndex) {
			return;
		}
		if (leftIndex < 0 || rightIndex >= array.length) {
			return;
		}
		
		int maxPosValue = searchMaxPositive(array);
		int maxNegValue = searchMaxNegative(array);
		// This array will store the amount of elements in their respective indexes
		Integer[] countArray = new Integer[maxPosValue + maxNegValue + 2];
		// This array will store the ordered elements
		Integer[] outputArray = new Integer[array.length];
		
		// Initializes the array with elements 0
		for (int i = 0; i < countArray.length; i++) {
			countArray[i] = 0;
		}
		// Stores the quantity of values in the corresponding index
		for (int i = 0; i < array.length; i++) {
			int index = array[i] + maxNegValue;
			countArray[index]++;
		}
		// Calculates the sum of a value with its previous
		for (int i = 1; i < countArray.length; i++) {
			countArray[i] = countArray[i] + countArray[i - 1];
		}
		// Build the output looking for indexes in the countArray
		// added with the minimum negative value
		for (int i = 0; i < array.length; i++) {
			int index = --countArray[array[i] + maxNegValue];
			outputArray[index] = array[i];
		}
		// Copy the outputArray to the array 
		for (int i = 0; i < outputArray.length; i++) {
			array[i] = outputArray[i];
		}
	}

	private int searchMaxPositive(Integer[] array) {
		// Search for the maximum value
		int max = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] > 0) {
				if (array[i] > max) {
					max = array[i];
				}
			}
		}
		return max;
	}
	
	private int searchMaxNegative(Integer[] array) {
		// Search for the maximum value in absolute terms
		int max = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] < 0) {
				int number = Math.abs(array[i]);
				if (number > max) {
				max = number;
				}
			}
		}
		return max;
	}
	
}
