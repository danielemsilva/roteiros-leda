package adt.stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentStackRecursiveDoubleLinkedListTest {

	private StackRecursiveDoubleLinkedListImpl<Integer> stack1;
	private StackRecursiveDoubleLinkedListImpl<Integer> stack2;
	private StackRecursiveDoubleLinkedListImpl<Integer> stack3;

	@Before
	public void setUp() throws Exception {

		getImplementations();

		stack1.push(3);
		stack1.push(2);
		stack1.push(1);

		stack2.push(3);
	}

	private void getImplementations() {
		stack1 = new StackRecursiveDoubleLinkedListImpl<>(3);
		stack2 = new StackRecursiveDoubleLinkedListImpl<>(2);
		stack3 = new StackRecursiveDoubleLinkedListImpl<>(4);
	}
	
	@Test
	public void testIsEmpty() {
		Assert.assertFalse(stack1.isEmpty());
		Assert.assertFalse(stack2.isEmpty());
		Assert.assertTrue(stack3.isEmpty());
	}
	
	@Test
	public void testIsFull() {
		Assert.assertTrue(stack1.isFull());
		Assert.assertFalse(stack2.isFull());
		Assert.assertFalse(stack3.isFull());
	}
	
	@Test
	public void testPush() throws StackOverflowException {
		stack2.push(2);
		Assert.assertTrue(stack2.isFull());
	}

	@Test(expected = StackOverflowException.class)
	public void testPushError() throws StackOverflowException {
		stack1.push(4);
	}
	
	@Test
	public void testPop() throws StackUnderflowException {
		stack2.pop();
		Assert.assertTrue(stack2.isEmpty());
	}

	@Test(expected = StackUnderflowException.class)
	public void testPopError() throws StackUnderflowException {
		stack3.pop();
	}
	
	@Test
	public void testTop() {
		Assert.assertEquals(new Integer(1), stack1.top());
	}

}
