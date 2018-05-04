package sorting.divideAndConquer;

import sorting.AbstractSorting;
import util.Util;

/**
 * Quicksort is based on the divide-and-conquer paradigm. The algorithm chooses
 * a pivot element and rearranges the elements of the interval in such a way
 * that all elements lesser than the pivot go to the left part of the array and
 * all elements greater than the pivot, go to the right part of the array. Then
 * it recursively sorts the left and the right parts. Notice that if the list
 * has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

   private int separate(T[] array, int leftIndex, int rightIndex) {
      // Assumes that the smallest element is in the last position
      T pivot = array[rightIndex];

      int j = leftIndex - 1;
      // Here goes through the array looking for the smallest 
      // and switching to the first positions
      for (int i = leftIndex; i < rightIndex; i++) {
         if (array[i].compareTo(pivot) <= 0) {
            j++;
            Util.swap(array, i, j);
         }
      }
      Util.swap(array, j + 1, rightIndex);
      // This index indicates the number that is in the middle
      return j + 1;
   }

   @Override
   public void sort(T[] array, int leftIndex, int rightIndex) {
      if (leftIndex < rightIndex) {
         int particion = separate(array, leftIndex, rightIndex);
         // Here is the first part of the array, with values smaller than the pivot
         sort(array, leftIndex, particion - 1);
         // In this part are the values greater than the pivot
         sort(array, particion + 1, rightIndex);
      }
   }
}
