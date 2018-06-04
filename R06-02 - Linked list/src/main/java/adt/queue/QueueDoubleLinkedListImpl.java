package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

   protected DoubleLinkedList<T> list;
   protected int size;

   public QueueDoubleLinkedListImpl(int size) {
      this.size = size;
      this.list = new DoubleLinkedListImpl<T>();
   }

   @Override
   public void enqueue(T element) throws QueueOverflowException {
      if (this.isFull()) {
         throw new QueueOverflowException();
      }
      // Inserts the element at the end of the list.
      this.list.insert(element);
   }

   @Override
   public T dequeue() throws QueueUnderflowException {
      if (this.isEmpty()) {
         throw new QueueUnderflowException();
      }
      T removed = head();
      // Removes the element at the beginning of the list.
      this.list.removeFirst();
      return removed;
   }

   @Override
   public T head() {
      DoubleLinkedListImpl<T> list = (DoubleLinkedListImpl<T>) this.list;
      // The head is the first of the list.
      return list.getHead().getData();
   }

   @Override
   public boolean isEmpty() {
      return this.list.isEmpty();
   }

   @Override
   public boolean isFull() {
      return this.list.size() == this.size;
   }

}
