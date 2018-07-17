package adt.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 3 > 2),
 * essa heap deixa os elementos maiores no topo. Essa comparação não é feita
 * diretamente com os elementos armazenados, mas sim usando um comparator. Dessa
 * forma, dependendo do comparator, a heap pode funcionar como uma max-heap ou
 * min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é
	 * mudar apenas o comparator e mandar reordenar a heap usando esse comparator.
	 * Assim os metodos da heap não precisam saber se vai funcionar como max-heap
	 * ou min-heap.
	 */
	protected Comparator<T> comparator;

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma min-heap.
	 */
	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento indexado
	 * pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento indexado
	 * pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		ArrayList<T> resp = new ArrayList<T>();
		for (T elem : this.heap) {
			if (elem != null) {
				resp.add(elem);
			}
		}
		return (T[]) resp.toArray(new Comparable[0]);
	}

	// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode ser
	 * a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
	 * (comparados usando o comparator) elementos na parte de cima da heap.
	 */
	private void heapify(int position) {
		int left = left(position);
		int right = right(position);
		int parent = parent(position);
		int actual = position;

		if (parent <= index && comparator.compare(heap[actual], heap[parent]) > 0) {
			actual = parent;
		}
		if (left <= index && comparator.compare(heap[left], heap[actual]) > 0) {
			actual = left;
		}
		if (right <= index && comparator.compare(heap[right], heap[actual]) > 0) {
			actual = right;
		}
		if (actual != position) {
			Util.swap(heap, position, actual);
			heapify(actual);
		}
	}

	@Override
	public void insert(T element) {
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		} else if (isEmpty()) {
			index++;
			heap[index] = element;
		} else {
			index++;
			heap[index] = element;
			heapify(index);
		}
	}

	@Override
	public void buildHeap(T[] array) {
		if (array != null) {
			index = -1;
			for (T elem : array)
				insert(elem);
		}
	}

	@Override
	public T extractRootElement() {
		if (isEmpty()) {
			return null;
		}
		T extract = rootElement();
		Util.swap(heap, 0, index);
		heap[index] = null;
		index--;
		heapify(0);
		return extract;
	}

	@Override
	public T rootElement() {
		if (this.isEmpty()) {
			return null;
		}
		return heap[0];
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] heapsort(T[] array) {
		T[] sorted = array;

		if (array != null && array.length != 0) {
			for (T element : array) {
				insert(element);
			}
			sorted = (T[]) (new Comparable[size()]);

			if (heap[0].compareTo(heap[index]) > 0) {
				for (int i = array.length - 1; i >= 0; i--) {
					sorted[i] = extractRootElement();
				}
			} else {
				for (int i = 0; i < array.length; i++) {
					sorted[i] = extractRootElement();
				}
			}
		}
		return sorted;
	}

	@Override
	public int size() {
		return index + 1;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}

}
