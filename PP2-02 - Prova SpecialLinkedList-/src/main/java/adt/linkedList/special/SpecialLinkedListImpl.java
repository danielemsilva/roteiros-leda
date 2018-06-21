package adt.linkedList.special;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

public class SpecialLinkedListImpl<T> extends SingleLinkedListImpl<T> implements SpecialLinkedList<T> {

   @Override
   public void swap(T elem1, T elem2) {
      if (elem1 == null || elem2 == null) {
         return;
      }

      if (this.getHead().isNIL()) {
         return;
      }

      SingleLinkedListNode<T> aux1 = this.getHead();
      SingleLinkedListNode<T> aux2 = this.getHead();

      while (!aux1.getData().equals(elem1)) {
         aux1 = aux1.getNext();
      }

      while (!aux2.getData().equals(elem2)) {
         aux2 = aux2.getNext();
      }

      if (!aux1.isNIL() && !aux2.isNIL()) {
         T auxData = aux1.getData();
         aux1.setData(aux2.getData());
         aux2.setData(auxData);
      }
   }

   @Override
   public T elementFromTheEnd(int k) {
      return helper(this.getHead(), 0, k);
   }

   private T helper(SingleLinkedListNode<T> node, int index, int k) {
      if (index == k) {
         return node.getData();
      }

      if (node.getNext().isNIL()) {
         return helper(node, index + 1, k);
      }

      return helper(node.getNext(), index, k);
   }

   /*
   public static void main(String args[]) {
   	SpecialLinkedList<Integer> list1 = new SpecialLinkedListImpl<>();
   	list1.insert(1);
   	list1.insert(2);
   	list1.insert(3);
   	list1.insert(4);
   	list1.insert(5);
   	list1.swap(2, 5);
   	Assert.assertArrayEquals(new Integer[] { 1, 5, 3, 4, 2 }, list1.toArray());
   	list1.swap(1, 4);
   	Assert.assertArrayEquals(new Integer[] { 4, 5, 3, 1, 2 }, list1.toArray());
   	list1.swap(3, 4);
   	Assert.assertArrayEquals(new Integer[] { 3, 5, 4, 1, 2 }, list1.toArray());

   	// Testing with a empty list
   	SpecialLinkedList<Integer> list2 = new SpecialLinkedListImpl<>();
   	list2.swap(1, 2);
   	Assert.assertArrayEquals(new Integer[] {}, list2.toArray());

   	// Testing with null elements
   	list2.swap(1, null);
   	Assert.assertArrayEquals(new Integer[] {}, list2.toArray());

   	System.out.println(list1.elementFromTheEnd(4)); // 5
   	System.out.println(list1.elementFromTheEnd(3)); // 4
   	System.out.println(list1.elementFromTheEnd(1)); // 2
   }
    */
}
