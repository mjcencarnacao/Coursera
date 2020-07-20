import edu.princeton.cs.algs4.StdIn;

public class Permutation {

	public static void main(String[] args) {
		RandomizedQueue<String> PermutationRandomizedQueue = new RandomizedQueue<>();
		int argument = Integer.parseInt(args[0]);
		while (!StdIn.isEmpty())
			PermutationRandomizedQueue.enqueue(StdIn.readString());
		for (int x = 0; x < argument; x++)
			System.out.println(PermutationRandomizedQueue.dequeue());
	}
}
