package sorting.linearSorting;

import java.util.Arrays;

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
		int maxValue = searchMax(array);

		Integer[] countArray = new Integer[maxValue + 1];
		Integer[] outputArray = new Integer[array.length];
		
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
		array = Arrays.copyOf(outputArray, array.length);
	}

	private int searchMax(Integer[] array) {
		// Search for the maximum value
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < array.length; i++) {
			if (array[i] >= max) {
				max = array[i];
			}
		}
		System.out.println(max);
		return max;
	}

	public static void main(String[] args) {
		CountingSort classe = new CountingSort();
		Integer[] array = { 2, 4, 5, 6, 2, 9 };
		classe.sort(array, 0, array.length - 1);
		System.out.println(Arrays.toString(array));
	}
	
}
