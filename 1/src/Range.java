import java.util.Iterator;

/**
 * Simulates python's range function
 *
 * ps. One liner fixed
 * ps. Still figuring out how to use SimpleLinkedList... Will update code soon
 */
public class Range implements Iterable<Integer> {
	// you probably need some variables here
	int min, max, increment;

	public Range(int min, int max, int increment) {
		// change this
		this.min = min;
		this.max = max;
		this.increment = increment;
	}

	public Range(int min, int max) {
		// change this

		// use the other constructor and give it the default increment 1
		this(min, max, 1);
	}

	private class RangeIterator implements Iterator<Integer> {
		int start = min;
		int stop = max;
		int incr = increment;
		int index = 0;

		// throw exceptions to avoid crashing
		{
			if (start == stop)
				System.out.println("Error! The min and max cannot be the same value.");
			if (incr == 0)
				throw new IllegalArgumentException("Error! The increment value cannot be 0!");
		}

		@Override
		public boolean hasNext() {
			boolean hasNext = false;
			// positive increment
			if (start < stop && (start + incr * index < stop))
				hasNext = true;
				// negative increment
			else if ((start > stop) && (start + incr * index > stop))
				hasNext = true;
			return hasNext;
		}

		@Override
		public Integer next() {
			// local variable to hold the next value
			Integer next = null;
			// if has next, then calculate next value based on member variables
			if (hasNext()) {
				next = start + incr * index;
				index++;
			}
			// return the next value
			return next;
		}
	}

	public java.util.Iterator<Integer> iterator() {
		// change this
		// also understand what this actually does and the easiest way to do this
		// should be a one liner

		// one liner updated
		return new RangeIterator();
	}

	/*
	* Test the Range class
	*/
	public static void main(String[] args){
		System.out.println("One:");
		for(Integer j : new Range(1,8,1)) {
			System.out.print(j);
		}
		// 1234567

		System.out.println("\nTwo:");
		for(Integer j : new Range(1,8,2)) {
			System.out.print(j);
		}
		// 1357

		System.out.println("\nThree:");
		for(Integer j : new Range(1,8)) {
			System.out.print(j);
		}
		// 1234567

		System.out.println("\nFour:");
		for(Integer j : new Range(8,0,-1)) {
			System.out.print(j);
		}
		// 87654321

		System.out.println("\nFive:");
		for(Integer j : new Range(8,8,1)) {
			System.out.print(j);
		}
		// prints error msg

		System.out.println("\nSix:");
		for(Integer j : new Range(8,10,0)) {
			System.out.print(j);
		}
		// prints error msg
	}
}
