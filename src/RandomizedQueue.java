import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

/*----------------------------------------------------------------
 *  Author:        Nitin Gupta
 *  Written:       08/17/2018
 *  Last updated:  08/17/2018
 *  
 *  A Randomized Queue implementation which supports addition and removal in a bag 
 *  User can use iterator to access Queue elements in random order  
 *
 *----------------------------------------------------------------*/
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int size;

    public RandomizedQueue() {
        // construct an empty randomized queue
        items = (Item[]) new Object[1];
    }

    /**
     * Returns if queue is empty
     */
    public boolean isEmpty() {
        // is the randomized queue empty?
        return size == 0;
    }

    /**
     * Returns number of elements in queue
     */
    public int size() {
        // return the number of items on the randomized queue
        return size;
    }

    /**
     * Add given element in queue Throws IllegalArgumentException if element is null
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Iteam can not be null");
        }

        // add the item
        if (size == items.length) {
            resize(size * 2);
        }
        items[size++] = item;
    }

    /**
     * Remove a any element from queue Throws NoSuchElementException if queue is
     * empty
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty queue");
        }

        // remove and return a random item
        int pos = StdRandom.uniform(0, size);
        Item temp = items[pos];
        items[pos] = items[size - 1];
        items[--size] = null;
        if (size > 0 && size == items.length / 4)
            resize(items.length / 2);
        return temp;
    }

    /**
     * Returns random element from queue Throws NoSuchElementException if queue is
     * empty
     */
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty queue");
        }

        // return a random item (but do not remove it)
        return items[StdRandom.uniform(0, size)];
    }

    /**
     * Iterator over elements in random order
     */
    public Iterator<Item> iterator() {
        return new RandomizedQueueItr();
    }

    /**
     * Resize array in amortized time
     */
    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int count = 0; count < size; count++) {
            temp[count] = items[count];
        }
        items = temp;
    }

    public static void main(String[] args) {
        // unit testing (optional)
        RandomizedQueue<String> test = new RandomizedQueue<>();
        test.enqueue("A");
        test.enqueue("B");
        test.enqueue("C");
        for (String item : test) {
            System.out.println(item);
        }

        test.dequeue();
        for (String item : test) {
            System.out.println(item);
        }

        for (String item : test) {
            System.out.println(item);
        }

        for (String item : test) {
            System.out.println(item);
        }
    }

    private class RandomizedQueueItr implements Iterator<Item> {
        private int count;
        private int[] randoms;

        public RandomizedQueueItr() {
            randoms = StdRandom.permutation(size);
        }

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public Item next() {
            if (count == size) {
                throw new NoSuchElementException("No more element");
            }
            return items[randoms[count++]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove not supported");
        }
    }
}