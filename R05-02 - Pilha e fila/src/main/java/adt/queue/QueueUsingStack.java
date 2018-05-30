package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		try {
			unstack(stack2, stack1);
			stack1.push(element);
		} catch (Exception e) {
			throw new QueueOverflowException();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		try {
			unstack(stack1, stack2);
			return stack2.pop();
		} catch (Exception e) {
			throw new QueueUnderflowException();
		}
	}

	@Override
	public T head() {
		try {
			unstack(stack1, stack2);
			return stack2.top();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean isEmpty() {
		return stack1.isEmpty() && stack2.isEmpty();
	}

	@Override
	public boolean isFull() {
		return stack1.isFull() || stack2.isFull();
	}

	private void unstack(Stack<T> stack1, Stack<T> stack2) 
			throws StackUnderflowException, StackOverflowException {
		while (!stack1.isEmpty()) {
			T topRemoved = stack1.pop();
			stack2.push(topRemoved);
		}
	}

}
