import java.util.*;
import java.io.*;

public class ArrayQueue<E> {

    public static final int CAPACITY = 10;  // default queue capacity
    private E[] Q;                          // E array used to implement the queue  
    private int n;                          // actual capacity of queue
    private int front;                      // index for the top of queue
    private int rear;                       // rear of the queue
    private int size;                       // size of the queue


    public ArrayQueue(){this(CAPACITY);}

    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity){
        n = capacity;
        Q = (E[]) new Object [n];
        front = rear = size = 0;
    }
    //
    // NOTE: java does not allow creation of array with parametrised type!
    //

    public int size(){return size;}
    //
    // IMPLEMENT ME
    //

    public boolean isEmpty(){return size == 0;}
    //
    // IMPLEMENT ME
    //

    public E front() throws ArrayQueueException {
        if (isEmpty()) {
            throw new ArrayQueueException("queue is empty");
        }
        return Q[rear];
    }
    //
    // IMPLEMENT ME
    //

    public void enqueue(E element) throws ArrayQueueException {
        if (size == n) {
            throw new ArrayQueueException("queue is full");
        }

        size++;
        Q[rear] = element;
        rear = (rear + 1) % n;
    }
    //
    // IMPLEMENT ME
    //


    public E dequeue() throws ArrayQueueException {
        if (isEmpty()) {
            throw new ArrayQueueException("queue is empty");
        }
        E element = Q[front];
        front = (front + 1) % n;
        size--;
        return element;
    }
    //
    // IMPLEMENT ME
    //

    public String toString(){
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        for (int i = 0; i < size; i++) {
            joiner.add(Q[(front + i) % n].toString());
        }
        return joiner.toString();
    }
    //
    // IMPLEMENT ME
    //
    //
    // NOTE: if the queue contains 1,2,3 then return "[1,2,3]"
    //       if the queue contains 1 then return "[1]"
    //       if the queue is empty return "[]"
    //
}
	
