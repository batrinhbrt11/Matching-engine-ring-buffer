package vn.demo.starter.util;

public class RingBuffer<T> {
    private T[] buffer;
    private int head;
    private int tail;
    private int size;
    private boolean isFull;

    /**
     * Constructor for RingBuffer
     * size: capacity of buffer
     * head: index of head
     * tail: index of tail
     * isFull: flag to check if buffer is full
     * @param capacity
     */
    public RingBuffer(int capacity) {
        buffer = (T[]) new Object[capacity];
        size = capacity;
        head = 0;
        tail = 0;
        isFull = false;
    }

    public boolean push(T item) {
        if (isFull) {
            // If buffer is full, throw exception
            throw new RuntimeException("Buffer is full!");  
        }
        // Add item to buffer
        buffer[tail] = item;
        // Move tail to next position
        tail = (tail + 1) % size;
        // If tail is equal to head, buffer is full
        if (tail == head) {
            isFull = true;
        }
        return true;
    }

    /**
     * Pop item from buffer
     * @return item
     */
    public T pop() {
        if (isEmpty()) {
            // If buffer is empty, throw exception
            throw new RuntimeException("Buffer is empty!");
        }
        // Get item from buffer
        T item = buffer[head];
        // Move head to next position
        head = (head + 1) % size;
        // Set isFull to false
        isFull = false;
    
        return item;
    }

    /**
     * Check if buffer is empty
     * @return true if buffer is empty, false otherwise
     */
    public boolean isEmpty() {
        return (!isFull && head == tail);
    }

    /**
     * Check if buffer is full
     * @return true if buffer is full, false otherwise
     */
    public boolean isFull() {
        return isFull;
    }
}
