package adt.linkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import adt.queue.*;

public class StudentQueueDoubleLinkedListTest {

	private QueueDoubleLinkedListImpl<Integer> lista1;
	private QueueDoubleLinkedListImpl<Integer> lista2;
	private QueueDoubleLinkedListImpl<Integer> lista3;

	@Before
	public void setUp() throws Exception {

		getImplementations();

		// Lista com 3 elementos.
		lista1.enqueue(3);
		lista1.enqueue(2);
		lista1.enqueue(1);

		// Lista com 1 elemento.
		lista2.enqueue(1);
	}

	private void getImplementations() {
		lista1 = new QueueDoubleLinkedListImpl<>(3);
		lista2 = new QueueDoubleLinkedListImpl<>(2);
		lista3 = new QueueDoubleLinkedListImpl<>(5);
	}

	@Test
	public void testTop() {
		Assert.assertEquals(new Integer(3), lista1.head());
		Assert.assertEquals(new Integer(1), lista2.head());
	}

	@Test
	public void testIsEmpty() {
		Assert.assertFalse(lista1.isEmpty());
		Assert.assertFalse(lista2.isEmpty());
		Assert.assertTrue(lista3.isEmpty());
	}

	@Test
	public void testIsFull() {
		Assert.assertTrue(lista1.isFull());
		Assert.assertFalse(lista2.isFull());
		Assert.assertFalse(lista3.isFull());
	}

	@Test
	public void testEnqueue() {
		try {
			lista3.enqueue(new Integer(5));
			Assert.assertFalse(lista3.isEmpty());
			
			lista2.enqueue(null);
			Assert.assertFalse(lista2.isFull());
		} catch (QueueOverflowException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = QueueOverflowException.class)
	public void testEnqueueComErro() throws QueueOverflowException {
		lista1.enqueue(new Integer(7));
	}

	@Test
	public void testDequeue() {
		try {
			Assert.assertEquals(new Integer(3), lista1.dequeue());
		} catch (QueueUnderflowException e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected = QueueUnderflowException.class)
	public void testDequeueComErro() throws QueueUnderflowException {
		lista3.dequeue();
	}

}
