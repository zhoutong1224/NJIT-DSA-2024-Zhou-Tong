package oy.tol.tra;

import java.util.Arrays;

public class QueueImplementation<E> implements QueueInterface<E> {
    private Object[] itemArray;
    private int size = 0;
    private int head = 0;
    private int tail = 0;
    private int capacity;
    private static final int DEFAULT_STACK_SIZE = 10;

    public QueueImplementation() throws QueueAllocationException {
        this(DEFAULT_STACK_SIZE);
    }

    public QueueImplementation(int capacity) throws QueueAllocationException {
        if (capacity <= 0) {
            throw new QueueAllocationException("Cannot allocate room for the internal array");
        }
        try {
            itemArray = new Object[capacity];
            this.capacity = capacity;
        } catch (OutOfMemoryError e) {
            throw new QueueAllocationException("Failed to allocate more room for the queue");
        }
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public void enqueue(E element) throws QueueAllocationException, NullPointerException {
        if (element == null) {
            throw new NullPointerException("The element to enqueue cannot be null");
        }
        if (size == capacity) {
            ensureCapacity();
        }

        itemArray[tail] = element;
        tail = (tail + 1) % capacity;
        size++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E dequeue() throws QueueIsEmptyException {
        if (isEmpty()) {
            throw new QueueIsEmptyException("The queue cannot be empty");
        }
        E dequeuedElement = (E) itemArray[head];
        itemArray[head] = null; // Help with garbage collection
        head = (head + 1) % capacity;
        size--;
        return dequeuedElement;
    }

    @Override
    public E element() throws QueueIsEmptyException {
        if (isEmpty()) {
            throw new QueueIsEmptyException("The queue cannot be empty");
        }
        return (E) itemArray[head];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(itemArray, null); // Help with garbage collection
        head = tail = size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            int index = (head + i) % capacity;
            builder.append(itemArray[index]);
            if (i < size - 1) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    private void ensureCapacity() throws QueueAllocationException {
        int newCapacity = capacity * 2;
        Object[] newArray = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = itemArray[(head + i) % capacity];
        }
        itemArray = newArray;
        head = 0;
        tail = size;
        capacity = newCapacity;
    }
}