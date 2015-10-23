/**
 * LinkedList class implements a doubly-linked list. Adapted from Weiss, Data
 * Structures and Algorithm Analysis in Java. 3rd ed.
 * http://users.cis.fiu.edu/~weiss/dsaajava3/code/SimpleLinkedList.java
 */
public class SimpleLinkedList<T> implements Iterable<T> {

	private int size;
	private Node<T> beginMarker;
	private Node<T> endMarker;

	/**
	 * This is the doubly-linked list node class.
	 */
	private class Node<NodeT> {
		public Node(NodeT d, Node<NodeT> p, Node<NodeT> n) {
			data = d;
			prev = p;
			next = n;
		}

		public NodeT data;
		public Node<NodeT> prev;
		public Node<NodeT> next;
	}

	/**
	 * Construct an empty LinkedList.
	 */
	public SimpleLinkedList() {
		doClear();
	}

	/**
	 * Change the size of this collection to zero by initializing the beginning
	 * and end marker.
	 */
	public void doClear() {
		beginMarker = new Node<>(null, null, null);
		endMarker = new Node<>(null, beginMarker, null);
		beginMarker.next = endMarker;

		size = 0;
	}

	/**
	 * @return the number of items in this collection.
	 */
	public int size() {
		return size;
	}

	/**
	 * @return boolean indicating if the linked list is empty
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Gets the Node at position idx, which must range from lower to upper.
	 * 
	 * @param idx
	 *          index to search at.
	 * @param lower
	 *          lowest valid index.
	 * @param upper
	 *          highest valid index.
	 * @return internal node corresponding to idx.
	 * @throws IndexOutOfBoundsException
	 *           if index is not between lower and upper, inclusive.
	 */
	private Node<T> getNode(int idx, int lower, int upper) {
		Node<T> p;

		if (idx < lower || idx > upper)
			throw new IndexOutOfBoundsException("getNode index: " + idx + "; size: "
					+ size());

		if (idx < size() / 2) { // Search through list from the beginning
			p = beginMarker.next;
			for (int i = 0; i < idx; i++)
				p = p.next;
		} else { // serch through the list from the end
			p = endMarker;
			for (int i = size(); i > idx; i--)
				p = p.prev;
		}

		return p;
	}

	/**
	 * Gets the Node at position idx, which must range from 0 to size( ) - 1.
	 * 
	 * @param idx
	 *          index to search at.
	 * @return internal node corresponding to idx.
	 * @throws IndexOutOfBoundsException
	 *           if index is out of range.
	 */
	private Node<T> getNode(int idx) {
		return getNode(idx, 0, size() - 1);
	}

	/**
	 * Returns the item at position idx.
	 * 
	 * @param idx
	 *          the index to search in.
	 * @throws IndexOutOfBoundsException
	 *           if index is out of range.
	 */
	public T get(int idx) {
		return getNode(idx).data;
	}

	/**
	 * Changes the item at position idx.
	 * 
	 * @param idx
	 *          the index to change.
	 * @param newVal
	 *          the new value.
	 * @return the old value.
	 * @throws IndexOutOfBoundsException
	 *           if index is out of range.
	 */
	public T set(int idx, T newVal) {
		Node<T> p = getNode(idx);
		T oldVal = p.data;

		p.data = newVal;
		return oldVal;
	}

	/**
	 * Adds an item in front of node p, shifting p and all items after it one
	 * position to the right in the list.
	 * 
	 * @param p
	 *          Node to add before.
	 * @param x
	 *          any object.
	 * @throws IndexOutOfBoundsException
	 *           if idx < 0 or idx > size()
	 */
	private void addBefore(Node<T> p, T x) {
		Node<T> newNode = new Node<>(x, p.prev, p);
		newNode.prev.next = newNode;
		p.prev = newNode;
		size++;
	}

	/**
	 * Adds an item at specified index. Remaining items shift up one index.
	 * 
	 * @param x
	 *          any object.
	 * @param idx
	 *          position to add at.
	 * @throws IndexOutOfBoundsException
	 *           if idx < 0 or idx > size()
	 */
	public void add(int idx, T x) {
		addBefore(getNode(idx, 0, size()), x);
	}

	/**
	 * Adds an item to this collection, at the end.
	 * 
	 * @param x
	 *          any object.
	 */
	public void add(T x) {
		add(size(), x);
	}

	/**
	 * Removes the object contained in Node p.
	 * 
	 * @param p
	 *          the Node containing the object.
	 * @return the item was removed from the collection.
	 */
	private T remove(Node<T> p) {
		p.next.prev = p.prev;
		p.prev.next = p.next;
		size--;

		return p.data;
	}

	/**
	 * Removes an item from this collection.
	 * 
	 * @param idx
	 *          the index of the object.
	 * @return the item was removed from the collection.
	 */
	public T remove(int idx) {
		return remove(getNode(idx));
	}

	/**
	 * Returns a String representation of this collection.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("[ ");
		for (T x : this) {
			sb.append(x + " ");
		}
		sb.append("]");

		return new String(sb);
	}

	/********* ADD YOUR SOLUTIONS HERE *****************/

	/**
	 * Find the first occurrence of a given object, and return the index of it.
	 *
	 * @param o
	 * 			the object that you want to locate
	 * @return the index of the first occurrence of the element o;
	 * or return -1 if the element is not found.
	 */
	public int indexOf(Object o) {
		// local int index to represent the index of the element that's currently being compared to;
		int index = 0;

		// use if/else statement to find o
		if (o != null) {
			// when o is not null
			// beginMarker.next because first data is always null
			for (Node<T> n = beginMarker.next; n != null; n = n.next) {
				if (o.equals(n.data)) {
					return index;
				}
				index++;
			}
		} else {
			// when o is null
			// beginMarker.next because first data is always null
			for (Node<T> n = beginMarker.next; n != null; n = n.next) {
				if (n.data == null) {
					return index;
				}
				index++;
			}
		}

		// if the if/else statement cannot find o, then it means that o is not in the list or it is null
		// return -1
		return -1;
	}

	/**
	 * Reverse the doubly linked list
	 *
	 * Add-on Question:
	 * Maybe we can use a boolean variable to manage the direction the linked list should go.
	 * If true then prev goes backward and next goes forward;
	 * If false then next goes backward and prev goes forward.
	 * Then the reverse() is used to change the value of the boolean variable.
	 */
	public void reverse() {
		Node<T> node = beginMarker;
		// swap the nodes throughout the list
		while (node != null) {
			// use temp to hold the value
			Node<T> temp = node.prev;
			node.prev = node.next;
			node.next = temp;
			node = node.prev;
		}
		// use temp to hold the value
		Node<T> temp = endMarker;
		endMarker = beginMarker;
		beginMarker = temp;
	}

	/**
	 * Remove the duplicates in an unsorted doubly linked list
	 *
	 * Add-on Question:
	 * 1) O(N^2)
	 * 2) Sorted List: we only need one node when comparing (one while loop),
	 * since the duplicates would always be right after the original element;
	 * just have one node comparing data with its next node's data (if/else in the while loop),
	 * and keep doing node.next = node.next.next to skip the duplicates.
	 */
	public void removeDuplicates() {
		// node current runs through the list
		Node<T> current = beginMarker.next;
		// node node compares every element with the current node
		Node<T> node;
		// first while loop increments current
		while (current != null) {
			node = current;
			// second while loop increments node
			while(node.next != null) {
				if (current.data.equals(node.next.data)) {
					node.next = node.next.next;
				} else {
					node = node.next;
				}
			}
			current = current.next;
		}
	}

	/**
	 * Interleaves elements from the other list into the linked list
	 * @param other
	 * 			another SimpleLinkedList list object
	 */
	public void interleave(SimpleLinkedList<T> other) {
		// create a new list to replace other so other would not be altered;
		// not optimal at all but I haven't come up with a better plan yet...
		// will change the code later, hopefully.
		SimpleLinkedList<T> temp = new SimpleLinkedList<>();
		for (int i = 0; i < other.size; i++) {
			temp.add(other.get(i));
		}
		Node<T> nodeOne = beginMarker.next;
		Node<T> nodeTwo = temp.beginMarker.next;
		Node<T> nextTwo;

		// when lst is longer than other
		while (nodeOne.next != null && nodeTwo.next != null
				&& nodeOne.next.data != null) {
			nextTwo = nodeTwo.next;
			nodeTwo.next = nodeOne.next;
			nodeOne.next = nodeTwo;
			nodeOne = nodeTwo.next;
			nodeTwo = nextTwo;
		}
		// in case the list that's operated on is null
		if (nodeOne.next == null) {
			beginMarker = other.beginMarker;
		}
		// when other is longer than lst, then append
		if (size <= other.size) {
			nodeOne.next = nodeTwo;
			while (nodeTwo.next != null) {
				nodeTwo = nodeTwo.next;
			}
			endMarker = nodeTwo;
		}
	}
	
	/**
	 * Obtains an Iterator object used to traverse the collection.
	 * 
	 * @return an iterator positioned prior to the first element.
	 */
	public java.util.Iterator<T> iterator() {
		return new LinkedListIterator();
	}

	/**
	 * This is the implementation of the LinkedListIterator. It maintains a notion
	 * of a current position and of course the implicit reference to the
	 * SimpleLinkedList.
	 */
	private class LinkedListIterator implements java.util.Iterator<T> {
		private Node<T> current = beginMarker.next;
		private boolean okToRemove = false;

		public boolean hasNext() {
			return current != endMarker;
		}

		public T next() {
			if (!hasNext())
				throw new java.util.NoSuchElementException();

			T nextItem = current.data;
			current = current.next;
			okToRemove = true;
			return nextItem;
		}

		public void remove() {
			if (!okToRemove)
				throw new IllegalStateException();

			SimpleLinkedList.this.remove(current.prev);
			// ensures that remove() cannot be called twice during a single step in
			// iteration
			okToRemove = false;
		}
	}

	/**
	 * Test the linked list.
	 */
	public static void main(String[] args) {
		SimpleLinkedList<Integer> lst = new SimpleLinkedList<>();

		for (int i = 0; i < 10; i++)
			lst.add(i);
		for (int i = 20; i < 30; i++)
			lst.add(0, i);

		lst.remove(0);
		lst.remove(lst.size() - 1);

		System.out.println(lst);

	}
}
