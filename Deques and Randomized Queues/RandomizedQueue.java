import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] itemArray;
	private int items;

	public RandomizedQueue() {
		itemArray = (Item[]) new Object[2];
	}

	public boolean isEmpty() {
		if (items == 0)
			return true;
		return false;
	}

	public int size() {
		return items;
	}

	public void enqueue(Item item) {
		if (item == null)
			throw new IllegalArgumentException("Item can not be null.");
		if (items == itemArray.length) {
			Item[] copy = (Item[]) new Object[itemArray.length * 2];
			for (int i = 0; i < items; i++)
				copy[i] = itemArray[i];
			itemArray = copy;
		}
		if (items == 0) {
			itemArray[items] = item;
			items++;
			return;
		}

		itemArray[items] = item;
		items++;
	}

	public Item dequeue() {
		if (isEmpty())
			throw new NoSuchElementException("Empty RandomizedQueue.");
		if (items == itemArray.length / 4) {
			Item[] copy = (Item[]) new Object[itemArray.length / 2];
			for (int i = 0; i < items; i++)
				copy[i] = itemArray[i];
			itemArray = copy;
		}
		int randomIndex = StdRandom.uniform(items);
		Item returning = itemArray[randomIndex];
		itemArray[randomIndex] = itemArray[--items];
		itemArray[items] = null;
		return returning;
	}

	public Item sample() {
		if (isEmpty())
			throw new NoSuchElementException("Empty RandomizedQueue.");
		int random = StdRandom.uniform(items);
		return itemArray[random];
	}

	@Override
	public Iterator<Item> iterator() {
		return new AuxIterator();
	}

	private class AuxIterator implements Iterator<Item> {

		private int iterator;
		private int[] copy;

		public AuxIterator() {
			copy = new int[items];
			for (int x = 0; x < items; x++) {
				copy[x] = x;
			}
			StdRandom.shuffle(copy);
		}

		@Override
		public boolean hasNext() {
			if (iterator < items)
				return true;
			return false;
		}

		@Override
		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return itemArray[copy[iterator++]];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public static void main(String[] args) {
		RandomizedQueue<Integer> test = new RandomizedQueue<>();
		System.out.println(test.isEmpty());
		test.enqueue(1);
		System.out.println(test.size());
		test.enqueue(2);
		System.out.println(test.size());
		test.dequeue();
		test.dequeue();
		System.out.println(test.size());
	}

}
