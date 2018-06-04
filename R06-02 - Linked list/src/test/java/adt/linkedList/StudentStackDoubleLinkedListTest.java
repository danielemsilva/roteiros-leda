package adt.linkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import adt.stack.*;

public class StudentStackDoubleLinkedListTest {

	private StackDoubleLinkedListImpl<Integer> lista1;
	private StackDoubleLinkedListImpl<Integer> lista2;
	private StackDoubleLinkedListImpl<Integer> lista3;

	@Before
	public void setUp() throws Exception {

		getImplementations();

		// Lista com 3 elementos.
		lista1.push(3);
		lista1.push(2);
		lista1.push(1);

		// Lista com 1 elemento.
		lista2.push(1);
	}

	private void getImplementations() {
		lista1 = new StackDoubleLinkedListImpl<>(3);
		lista2 = new StackDoubleLinkedListImpl<>(2);
		lista3 = new StackDoubleLinkedListImpl<>(5);
	}

	@Test
	public void testTop() {
		Assert.assertEquals(new Integer(1), lista1.top());
		Assert.assertEquals(new Integer(1), lista2.top());
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
	public void testPush() {
		try {
			lista3.push(new Integer(5));
			Assert.assertFalse(lista3.isEmpty());
			
			lista2.push(null);
			Assert.assertFalse(lista2.isFull());
		} catch (StackOverflowException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = StackOverflowException.class)
	public void testPushComErro() throws StackOverflowException {
		lista1.push(new Integer(5));
	}

	@Test
	public void testPop() {
		try {
			Assert.assertEquals(new Integer(1), lista1.pop());
		} catch (StackUnderflowException e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected = StackUnderflowException.class)
	public void testPopComErro() throws StackUnderflowException {
		lista3.pop();
	}

}
