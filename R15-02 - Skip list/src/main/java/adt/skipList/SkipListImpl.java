package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void insert(int key, T newValue, int height) {
		if (newValue == null || height > this.maxHeight) {
			return;
		}
		
		SkipListNode<T>[] toUpdate = (SkipListNode<T>[]) new SkipListNode[maxHeight];
		SkipListNode<T> aux = search(key, toUpdate, root);
		if (aux.getKey() == key) {
			aux.setValue(newValue);
		} else {
			int level = randomLevel();			
			if (level > maxHeight) {
				for (int i = maxHeight; i < level; i++) {
					toUpdate[i] = root;
				}
				maxHeight = level;
			}
			
			SkipListNode<T> newNode = new SkipListNode<T>(key, level, newValue);
			for (int i = 0; i < level; i++) {
				newNode.forward[i] = toUpdate[i].getForward(i);
				toUpdate[i].forward[i] = newNode;
			}
		}
	}

	@Override
	public void remove(int key) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public SkipListNode<T> search(int key) {
		if (this.root.key == key) {
			return this.root;
		}
		if (this.NIL.key == key) {
			return this.NIL;
		}

		SkipListNode<T> aux = this.root;
		for (int i = this.maxHeight - 1; i >= 0; i--) {
			while (key > aux.getForward(i).getKey()) {
				aux = aux.getForward(i);
			}
		}
		
		aux = aux.getForward(0);
		if (aux != null && aux.getKey() == key) {
			return aux;
		}
		return null;
	}
	
	private SkipListNode<T> search(int key, SkipListNode<T>[] toUpdate, SkipListNode<T> aux) {
		for (int i = maxHeight - 1; i >= 0; i--) {
			while (aux.getForward(i).getKey() < key) {
				aux = aux.getForward(i);
			}
			toUpdate[i] = aux;
		}
		
		aux = aux.forward[0];
		return aux;
	}

	@Override
	public int size() {
		SkipListNode<T> aux = this.root;
		int size = 0;
		while (!aux.forward[0].equals(this.NIL)) {
			size++;
			aux = aux.getForward(0);
		}
		return size;
	}

	@Override
	public SkipListNode<T>[] toArray() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	private int randomLevel(){
		int randomLevel = 1;
		while(Math.random() <= this.PROBABILITY && randomLevel < this.maxHeight){
			randomLevel++;
		}
		return randomLevel;
	}

}
