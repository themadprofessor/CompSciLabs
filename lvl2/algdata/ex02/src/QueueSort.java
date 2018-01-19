import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class QueueSort<E extends Comparable<E>> {

    protected ArrayQueue<ArrayQueue<E>> Q;
    public static final int CAPACITY = 10;  // default queue capacity
    private int n;
    private boolean trace;

    public QueueSort(){this(CAPACITY);}

    public QueueSort(int capacity){
        n = capacity;
        Q = new ArrayQueue<ArrayQueue<E>>(n);
    }

    private ArrayQueue<E> merge(ArrayQueue<E> q1,ArrayQueue<E> q2) throws ArrayQueueException {
        ArrayQueue<E> newQueue = new ArrayQueue<>(q1.size() + q2.size());

        E el1;
        E el2 = q2.dequeue();

        while (!q1.isEmpty()) {
            el1 = q1.dequeue();
            while (!q2.isEmpty()) {
                if (el2.compareTo(el1) < 0) {
                    newQueue.enqueue(el2);
                    el2 = q2.dequeue();
                } else {
                    break;
                }
            }
            newQueue.enqueue(el1);
        }

        newQueue.enqueue(el2);
        while (!q2.isEmpty()) {
            newQueue.enqueue(q2.dequeue());
        }

        return newQueue;
    }
    //
    // IMPLEMENT ME
    // Take two sorted queues and merge them to produce a third
    // sorted queue
    //

    public void sort(){
        while (Q.size() > 1) {
            System.out.println(Q);
            Q.enqueue(merge(Q.dequeue(), Q.dequeue()));
        }
    }
    //
    // IMPLEMENT ME
    // given a queue Q of queues
    // (1) if Q is of size 1 deliver the first queue in Q
    // (2) if Q is of size 2 or more 
    //     - get the first and second queues off Q
    //     - merge these two queues to create a third queue
    //     - add the third queue to the queue
    //     - go back to (1)
    //

    public void add(E element){
        ArrayQueue<E> queue = new ArrayQueue<>(1);
        queue.enqueue(element);
        Q.enqueue(queue);
    }
    //
    // IMPLEMENT ME
    // create an ArrayQueue<E> that contains the element
    // enqueue it onto Q
    //

    public String toString(){return Q.toString();}

    public void trace(){trace = !trace;}

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File(args[0]));
        ArrayList<String> data = new ArrayList<String>();
        while (sc.hasNext()) data.add(sc.next());
        int n = data.size();
        QueueSort<String> QS = new QueueSort<String>(n);
        for (String s : data) QS.add(s);
        if (args.length > 1) QS.trace();
        QS.sort();
        System.out.println(QS);
    }

}
