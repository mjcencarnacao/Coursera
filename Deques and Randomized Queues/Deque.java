import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	/*
	 * Due to the restriction of not being able to use a List <Item> (JAVA), a
	 * private class Node as been created. The main objective of which is to
	 * interconnect the different items (generic). We can add / remove in front and
	 * back so we need two Nodes (Head and Tail).
	 */

	private Node head;
	private Node tail;
	private int n;

	private class Node {
		private Item item;
		private Node back;
		private Node next;
	}

	public Deque() {
	}

	public boolean isEmpty() {
		if (n == 0)
			return true;
		return false;
	}

	public int size() {
		return n;
	}

	/*
	 * If the Item given as an argument is not null, it will proceed to the creation
	 * of a new Node (Old First), which, before inserting the new item on the Deque,
	 * will be equal to the Head. After this equality we create a new Node head and
	 * we make the necessary connections (connections in the Node back and next of
	 * the class Node ()), however it is necessary to pay attention to an
	 * exceptional case: when the The head and tail assume the same item.
	 */

	public void addFirst(Item item) {
		if (item == null)
			throw new NullPointerException("Item can not be null.");

		Node oldFirst = head;
		head = new Node();
		head.item = item;
		head.next = oldFirst;
		head.back = null;
		if (isEmpty())
			tail = head;
		else
			oldFirst.back = head;
		n++;

	}

	// Same logic only changes the Nodes.

	public void addLast(Item item) {
		if (item == null)
			throw new NullPointerException("Item can not be null.");
		Node oldLast = tail;
		tail = new Node();
		tail.item = item;
		tail.back = oldLast;
		if (head == null)
			head = tail;
		else
			tail.back.next = tail;
		n++;
	}

	/*
	 * removeFirst() allows removal of the item on the Deque Head, however, it is
	 * necessary to return it. It is checked if the list after saving the item will
	 * stay empty, if yes both the Head and the Tail are null, otherwise the Head
	 * advances a Node.
	 */

	public Item removeFirst() {
		if (isEmpty())
			throw new NoSuchElementException("Empty Deque.");
		Item returning = head.item;
		n--;
		if (isEmpty()) {
			head = null;
			tail = null;
		} else {
			head = head.next;
		}
		return returning;
	}

	// Same logic only changes the Nodes and item to return.

	public Item removeLast() {
		if (isEmpty())
			throw new NoSuchElementException("Empty Deque.");
		Item returning = tail.item;
		n--;
		if (isEmpty()) {
			head = null;
			tail = null;
		} else {
			tail = tail.back;
		}
		return returning;
	}

	@Override
	public Iterator<Item> iterator() {
		return new AuxIterator();
	}

	private class AuxIterator implements Iterator<Item> {
		private Node copy;

		// Creation of an iterator from Head to Tail.

		public AuxIterator() {
			copy = head;
		}

		@Override
		public boolean hasNext() {
			if (copy != null)
				return true;
			return false;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			Item item = copy.item;
			copy = copy.next;
			return item;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public static void main(String[] args) {
		Deque<Integer> test = new Deque<Integer>();
		System.out.println(test.size());
		test.addLast(1);
		System.out.println(test.size());
		test.addFirst(2);
		System.out.println(test.size());
		test.removeLast();
		System.out.println(test.isEmpty());
		test.removeFirst();
	}
}
