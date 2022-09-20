package edu.iastate.cs228.hw3;

import java.util.AbstractSequentialList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author Mishari Alharbi
 * Implementation of the list interface based on linked
 * nodes that store multiple items per node. Rules for adding and
 * removing elements ensure that each node (except possibly the last
 * one) is at least half full.
 */
public class MultiList<E extends Comparable<? super E>> extends AbstractSequentialList<E> {
/**
 * Default number of elements that may be stored in each node.
 * 
 */
private static final int DEFAULT_NODESIZE = 4;

/**
 * Number of elements that can be stored in each node.
 */
private final int nodeSize;

/**
 * Dummy node for head. It should be private but set to public here only for
 * grading purpose. In practice, you should always make the head of a linked
 * list a private instance variable.
 */
public Node head;

/**
 * Dummy node for tail.
 */
private Node tail;

/**
 * Number of elements in the list.
 */
private int size;

/**
 * Constructs an empty list with the default node size.
 */
public MultiList() {
	this(DEFAULT_NODESIZE);
}

/**
 * Constructs an empty list with the given node size.
 * 
 * @param nodeSize number of elements that may be stored in each node, must be
 *                 an even number
 */
public MultiList(int nodeSize) {
	if (nodeSize <= 0 || nodeSize % 2 != 0)
		throw new IllegalArgumentException();

	// dummy nodes
	head = new Node();
	tail = new Node();
	head.next = tail;
	tail.previous = head;
	this.nodeSize = nodeSize;
}

/**
 * Constructor for grading only. Fully implemented.
 * 
 * @param head
 * @param tail
 * @param nodeSize
 * @param size
 */
public MultiList(Node head, Node tail, int nodeSize, int size) {
	this.head = head;
	this.tail = tail;
	this.nodeSize = nodeSize;
	this.size = size;
}

@Override
public int size() {

	return size;
}

@Override
public boolean add(E item) {
	// item = null is not allowed.
	if (item == null) {
		throw new NullPointerException();
	}
	// makes new node if there is none or if the node before is full.
	if (tail.previous == head || tail.previous.data[nodeSize - 1] != null) {
		Node temp = new Node();
		temp.addItem(item);
		link(tail.previous, temp);
		// add at the last node with the last available offset.
	} else {
		tail.previous.addItem(item);
	}
	size++;
	return true;
}

@Override
public void add(int pos, E item) {
	// item = null is not allowed.
	if (item == null) {
		throw new NullPointerException();
	}
	Node n = findForAdd(pos).node; // finds the specific node to add to it.
	int offset = findForAdd(pos).offset; // finds the specific offset of n where the item is going to be at.
	
	// makes new node if there is none or if the node before is full.
	if (tail.previous == head || (n == tail && n.previous.count == nodeSize)) {
		Node temp = new Node();
		temp.addItem(item);
		link(tail.previous, temp);
		
		// adds the item at the given offset on the previous node.
	} else if (offset == 0 && n.previous != head && n.previous.count < nodeSize) {
		n.previous.addItem(offset, item);
		
		// adds the item at the given offset on the current node.
	} else if (n.count < nodeSize) {
		n.addItem(offset, item);
		
		// split the node and takes the last two elements into a new node.
	} else {
		int mid = nodeSize / 2;
		Node temp = new Node();
		link(n, temp);
		while (n.count > mid) {
			temp.addItem(n.data[mid]);
			n.removeItem(mid);
		}
		
			// adds the item to n if offset is less or equal to mid
		if (offset <= mid) {
			n.addItem(offset, item);
			
			// adds the item to n.next if offset is larger than mid
		} else {
			for (int j = n.next.count - 1; j > (offset - mid - 1); j--) {
				n.next.data[j + 1] = n.next.data[j];
			}
			n.next.addItem(offset - mid, item);
		}
	}
	size++;
}

@Override
public E remove(int poss) {
	// item = null is not allowed.
	if (poss > size) {
		throw new IndexOutOfBoundsException();
	}
	
	Node n = findForRemove(poss).node; // finds the specific node to remove from.
	int offset = findForRemove(poss).offset; // finds the specific offset of n where the item is at.
	E item = n.data[offset];
	int mid = nodeSize/ 2;
		// delete the element at offset if n is the only node and has only 1 element.
	if (n.next == tail && n.count == 1) {
		n.removeItem(offset);
		unlink(n);
		
		// delete the element at offset if n is the only node and has more than mid.
	} else if (n.next == tail || n.count > mid) {
		n.removeItem(offset);

		// delete the element at offset and moves the first element in n.next to n.
	} else if (n.next.count > mid) {
		n.removeItem(offset);
		n.addItem(n.count, n.next.data[0]);
		n.next.removeItem(0);
		
		// delete the element at offset and moves all the element in n.next to n and delete n.next.
	} else if (n.next.count <= mid) {
		n.removeItem(offset);
		int i = 0;
		while (i < n.next.count) {
			n.addItem(n.next.data[i]);
			n.next.removeItem(i);
		}
		unlink(n.next);
	}
	size--;
	return item;
}

/**
 *
 * Inserts newNode into the list after current without updating size.
 * Precondition: current != null, newNode != null
 * @param current is the current node where we are linking to
 * @param newNoden the new node we are linking with current.
 */
private void link(Node current, Node newNode) {
	newNode.previous = current;
	newNode.next = current.next;
	current.next.previous = newNode;
	current.next = newNode;
}

/**
 *
 * Removes current from the list without updating size.
 * @param current The node which we are going to delete.
 */
private void unlink(Node current) {
	current.previous.next = current.next;
	current.next.previous = current.previous;
}

/**
 * Sort all elements in the Multi list in NON-DECREASING order. You may do the
 * following. Traverse the list and copy its elements into an array, deleting
 * every visited node along the way. Then, sort the array by calling the
 * insertionSort() method. (Note that sorting efficiency is not a concern for
 * this project.) Finally, copy all elements from the array back to the Multi
 * list, creating new nodes for storage. After sorting, all nodes but (possibly)
 * the last one must be full of elements.
 * 
 * Comparator<E> must have been implemented for calling insertionSort().
 */
public void sort() {
	class dataComparator implements Comparator<E> {
	@Override
	public int compare(E o1, E o2) {
		return o1.compareTo(o2);
	}
	}
	E[] sorted = copyFrom();
	insertionSort(sorted, new dataComparator());
	copyTo(sorted);

}

/**
 * Sort all elements in the Multi list in NON-INCREASING order. Call the
 * bubbleSort() method. After sorting, all but (possibly) the last nodes must be
 * filled with elements.
 * 
 * Comparable<? super E> must be implemented for calling bubbleSort().
 */
public void sortReverse() {
	E[] sorted = copyFrom();
	bubbleSort(sorted);
	copyTo(sorted);
}

/**
 * A private method that copies the MultiList data to an array.
 * 
 * @return A new array with the copied data
 */
private E[] copyFrom() {
	E[] temp = (E[]) new Comparable[size];
	Node current = head.next;
	int j = 0; // offset
	for (int i = 0; i < size; i++) {
		if (current.data[j] != null) {
			temp[i] = current.data[j];
		}
		j++;
		if (j >= current.count) {
			current = current.next;
			j = 0;
		}
	}
	return temp;
}

/**
 * A private method that copies a data from an array to the MultiList data.
 * @param arr The array which we are copying from.
 */
private void copyTo(E[] arr) {
	Node current = head.next;
	while (size > 0) {
		if (current.data[0] != null) {
			remove(0);
		}
	}
	for (int i = 0; i < arr.length; i++) {
		add(arr[i]);
	}
}

@Override
public Iterator<E> iterator() {
	return new MultiListIterator();
}

@Override
public ListIterator<E> listIterator() {
	return new MultiListIterator();
}

@Override
public ListIterator<E> listIterator(int index) {
	return new MultiListIterator(index);
}

/**
 * Returns a string representation of this list showing the internal structure
 * of the nodes.
 */
public String toStringInternal() {
	return toStringInternal(null);
}

/**
 * Returns a string representation of this list showing the internal structure
 * of the nodes and the position of the iterator.
 *
 * @param iter an iterator for this list
 */
public String toStringInternal(ListIterator<E> iter) {
	int count = 0;
	int position = -1;
	if (iter != null) {
		position = iter.nextIndex();
	}

	StringBuilder sb = new StringBuilder();
	sb.append('[');
	Node current = head.next;
	while (current != tail) {
		sb.append('(');
		E data = current.data[0];
		if (data == null) {
			sb.append("-");
		} else {
			if (position == count) {
				sb.append("| ");
				position = -1;
			}
			sb.append(data.toString());
			++count;
		}

		for (int i = 1; i < nodeSize; ++i) {
			sb.append(", ");
			data = current.data[i];
			if (data == null) {
				sb.append("-");
			} else {
				if (position == count) {
					sb.append("| ");
					position = -1;
				}
				sb.append(data.toString());
				++count;

				// iterator at end
				if (position == size && count == size) {
					sb.append(" |");
					position = -1;
				}
			}
		}
		sb.append(')');
		current = current.next;
		if (current != tail)
			sb.append(", ");
	}
	sb.append("]");
	return sb.toString();
}

/**
 * Node type for this list. Each node holds a maximum of nodeSize elements in an
 * array. Empty slots are null.
 */
private class Node {
/**
 * Array of actual data elements.
 */
// Unchecked warning unavoidable.
public E[] data = (E[]) new Comparable[nodeSize];

/**
 * Link to next node.
 */
public Node next;

/**
 * Link to previous node;
 */
public Node previous;

/**
 * Index of the next available offset in this node, also equal to the number of
 * elements in this node.
 */
public int count;

/**
 * Adds an item to this node at the first available offset. Precondition: count
 * < nodeSize
 * 
 * @param item element to be added
 */
void addItem(E item) {
	if (count >= nodeSize) {
		return;
	}
	data[count++] = item;
}

/**
 * Adds an item to this node at the indicated offset, shifting elements to the
 * right as necessary.
 * 
 * Precondition: count < nodeSize
 * 
 * @param offset array index at which to put the new element
 * @param item   element to be added
 */
void addItem(int offset, E item) {
	if (count >= nodeSize) {
		return;
	}
	for (int i = count - 1; i >= offset; --i) {
		data[i + 1] = data[i];
	}
	++count;
	data[offset] = item;
}

/**
 * Deletes an element from this node at the indicated offset, shifting elements
 * left as necessary. Precondition: 0 <= offset < count
 * 
 * @param offset
 */
void removeItem(int offset) {
	E item = data[offset];
	for (int i = offset + 1; i < nodeSize; ++i) {
		data[i - 1] = data[i];
	}
	data[count - 1] = null;
	--count;
}
}

private class MultiListIterator implements ListIterator<E> {
// directions for remove() and set()
private static final int BEHIND = -1;
private static final int AHEAD = 1;
private static final int NONE = 0;

/**
 * private variable to store the current node the cursor is at.
 */
private Node cursorNode;
/**
 * private variable to store the current index the cursor is at.
 */
private int cursorIndex;
/**
 * private variable to store the current offset the cursor is at.
 */
private int cursorOffset;
/**
 * private variable to store the direction for remove and set.
 */
private int direction;

/**
 * Default constructor
 */
public MultiListIterator() {
	this(0);
}

/**
 * Constructor finds node at a given position.
 * 
 * @param pos
 */
public MultiListIterator(int pos) {
	if (pos < 0 || pos > size)
		throw new IndexOutOfBoundsException("" + pos);
	cursorNode = findForRemove(pos).node;
	cursorOffset = findForRemove(pos).offset;
	cursorIndex = pos;
	direction = NONE;
}

@Override
public boolean hasNext() {
	return cursorIndex < size;
}

@Override
public E next() {
	if (!hasNext())
		throw new NoSuchElementException();
	if (cursorNode.count <= cursorOffset && cursorNode.next != tail) {
		cursorNode = cursorNode.next;
		cursorOffset = 0;
	}
	E next = cursorNode.data[cursorOffset++];
	cursorIndex++;
	direction = BEHIND;
	return next;
}

@Override
public void remove() {
	// Can't remove if next or previous weren't called before this.
	if (direction == NONE) {
		throw new IllegalStateException();
	}

	else {
		// Delete at front of cursor.
		if (direction == AHEAD) {
			MultiList.this.remove(cursorIndex);
		} else {
			// Delete behind the cursor.
			cursorIndex--;
			MultiList.this.remove(cursorIndex);
			cursorOffset = findForRemove(cursorIndex).offset;
			cursorNode = findForRemove(cursorIndex).node;
		}
	}
	direction = NONE;
}

@Override
public boolean hasPrevious() {

	return cursorIndex > 0;
}

@Override
public E previous() {
	if (!hasPrevious())
		throw new NoSuchElementException();
	if (0 >= cursorOffset && cursorNode.previous != head) {
		cursorNode = cursorNode.previous;
		cursorOffset = cursorNode.count;
	}
	E previous = cursorNode.data[--cursorOffset];
	cursorIndex--;
	direction = AHEAD;
	return previous;
}

@Override
public int nextIndex() {
	return cursorIndex;
}

@Override
public int previousIndex() {
	return cursorIndex - 1;
}

@Override
public void set(E e) {
	if (direction == NONE) {
		throw new IllegalStateException();
	}
	if (direction == AHEAD) {
		cursorNode.data[cursorOffset] = e;
	} else {
		cursorNode.data[cursorOffset - 1] = e;
	}
}

@Override
public void add(E e) {
	
	//adds using the MultiList add with index as cursor index.
	MultiList.this.add(cursorIndex, e);
	cursorIndex++;
	cursorOffset++;
	direction = NONE;

}
}

/**
 * Sort an array arr[] using the insertion sort algorithm in the NON-DECREASING
 * order.
 * 
 * @param arr  array storing elements from the list
 * @param comp comparator used in sorting
 */
private void insertionSort(E[] arr, Comparator<? super E> comp) {

	for (int i = 1; i < arr.length; i++) {
		E temp = arr[i];
		int j = i - 1;
		while (j > -1 && arr[j].compareTo(temp) > 0) {
			arr[j + 1] = arr[j];
			--j;
			arr[j + 1] = temp;
		}
	}
}

/**
 * Sort arr[] using the bubble sort algorithm in the NON-INCREASING order. For a
 * description of bubble sort please refer to Section 6.1 in the project
 * description. You must use the compareTo() method from an implementation of
 * the Comparable interface by the class E or ? super E.
 * 
 * @param arr array holding elements from the list
 */
private void bubbleSort(E[] arr) {

	for (int i = 0; i < arr.length - 1; i++) {
		for (int j = 0; j < arr.length - 1 - i; j++) {
			if (arr[j].compareTo(arr[j + 1]) < 0) {

				E temp = arr[j];
				arr[j] = arr[j + 1];
				arr[j + 1] = temp;
			}
		}
	}
}

/**
 * Private class to store a certain node offset and data.
 * 
 * @author Mishari Alharbi
 *
 */
private class NodeInfo {
public Node node;
public int offset;

public NodeInfo(Node node, int offset) {
	this.node = node;
	this.offset = offset;
}
}

/**
 * returns the node and offset for the given logical index with counting the
 * null spaces.
 * 
 * @param pos - the logical index
 * @return A NodeInfo object containing the node and offset of the logical
 *         index.
 */
private NodeInfo findForAdd(int pos) {

	Node current = head.next;
	int index = 0;
	int offset = 0;
	while (index < pos) {
		index++;
		offset++;
		if (offset >= nodeSize) {
			current = current.next;
			offset = 0;
		}
	}
	return new NodeInfo(current, offset);

}

/**
 * returns the node and offset for the given logical index without counting the
 * null spaces.
 * 
 * @param pos - the logical index
 * @return A NodeInfo object containing the node and offset of the logical
 *         index.
 */
private NodeInfo findForRemove(int pos) {

	Node current = head.next;
	int index = 0;
	int offset = 0;
	while (index < pos) {
		index++;
		offset++;
		if (offset >= current.count) {
			current = current.next;
			offset = 0;
		}
	}
	return new NodeInfo(current, offset);

}
}