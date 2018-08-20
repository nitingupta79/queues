import java.util.Iterator;
import java.util.NoSuchElementException;

/*----------------------------------------------------------------
 *  Author:        Nitin Gupta
 *  Written:       08/17/2018
 *  Last updated:  08/17/2018
 *  
 *  A Queue implementation which supports addition and removal on head as well as tail 
 *  User can use iterator to access Queue elements in FIFO order  
 *
 *----------------------------------------------------------------*/
public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    public Deque() {
        // construct an empty deque
    }

    /**
     * Returns if queue is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns number of elements in queue
     */
    public int size() {
        return size;
    }

    /**
     * Add given element at first position in queue Throws IllegalArgumentException
     * if element is null
     */
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item can not be null");
        }

        // add the item to the front
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = first;

        if (first != null) {
            first.prev = newNode;
        }
        first = newNode;

        size++;
        if (size == 1) {
            last = first;
        }
    }

    /**
     * Add given element at last position in queue Throws IllegalArgumentException
     * if element is null
     */
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item can not be null");
        }

        Node newNode = new Node();
        newNode.item = item;
        newNode.prev = last;
        if (last != null) {
            last.next = newNode;
        }
        last = newNode;
        size++;

        if (size == 1) {
            first = last;
        }
    }

    /**
     * Remove first element from queue Throws NoSuchElementException if queue is
     * empty
     */
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty queue");
        }

        // remove and return the item from the front
        Node tempNode = first;

        // Remove first element
        first = null;

        // if only one element, empty the queue, else store previous element into last
        if (size == 1) {
            last = null;
        } else {
            first = tempNode.next;
            first.prev = null;
        }

        size--;

        if (size == 1) {
            first.next = null;
            last = first;
        }

        return tempNode.item;
    }

    /**
     * Remove last element from queue Throws NoSuchElementException if queue is
     * empty
     */
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty queue");
        }

        // Store current last in temp
        Node tempNode = last;

        // Remove current last
        last = null;

        // if only one element, empty the queue, else store previous element into last
        if (size == 1) {
            first = null;
        } else {
            last = tempNode.prev;
            last.next = null;
        }

        size--;

        if (size == 1) {
            last.prev = null;
            first = last;
        }

        return tempNode.item;
    }

    /**
     * Iterator over elements Return elements in FIFO order
     */
    public Iterator<Item> iterator() {
        // return an iterator over items in order from front to end
        return new DequeIterator();
    }

    public static void main(String[] args) {
        // unit testing (optional)
        Deque<Integer> deque = new Deque<Integer>();
        System.out.println(deque.isEmpty());
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);
        System.out.println(deque.removeLast());
        deque.addLast(5);
    }

    /**
     * Node class to hold element and next and prev positions
     */
    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node node = first;

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public Item next() {
            if (node == null) {
                throw new NoSuchElementException("No more element");
            }
            Item temp = node.item;
            node = node.next;
            return temp;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove not supported");
        }
    }
}