import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*----------------------------------------------------------------
 *  Author:        Nitin Gupta
 *  Written:       08/18/2018
 *  Last updated:  08/18/2018
 *  
 *  A Permutation program to print random elements from a collection 
 *
 *----------------------------------------------------------------*/
public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        if (k > 0) {
            RandomizedQueue<String> q = new RandomizedQueue<>();

            while (!StdIn.isEmpty()) {
                q.enqueue(StdIn.readString());
            }

            for (int a = 0; a < k; a++) {
                StdOut.println(q.dequeue());
            }
        }
    }
}
