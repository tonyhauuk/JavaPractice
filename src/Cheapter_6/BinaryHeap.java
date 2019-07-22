package Cheapter_6;

import java.nio.BufferUnderflowException;

public class BinaryHeap<String extends Comparable<? super String>> {
    private static final int DEFAULT_CAPACITY = 10;

    private int currentSize; // Number of elements in heap
    private String[] array; // The heap array

    public BinaryHeap() {

        this(DEFAULT_CAPACITY);
    }

    public BinaryHeap(int capacity) {
        currentSize = 0;
        array = (String[]) new Comparable[capacity + 1];
    }

    public BinaryHeap(String[] items) {
        currentSize = items.length;
        array = (String[]) new Comparable[(currentSize + 2) * 11 / 10];

        int i = 1;
        for (String item : items)
            array[i++] = item;

        buildHeap();
    }

    private void buildHeap() {
        for (int i = currentSize / 2; i > 0; i--)
            percolateDown(i);
    }

    public void insert(String x) {
        if (currentSize == array.length - 1)
            enLargeArray(array.length * 2);

        int hole = ++currentSize;
        for (array[0] = x; x.compareTo(array[hole / 2]) < 0; hole /= 2)
            array[hole] = array[hole / 2];

        array[hole] = x;
    }

    private void enLargeArray(int newSize) {
        String[] old = array;
        array = (String[]) new Comparable[newSize];
        for (int i = 0; i < old.length; i++)
            array[i] = old[i];
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void makeEmpty() {
        currentSize = 0;
    }

    public String deleteMin() {
        if (isEmpty())
            throw new BufferUnderflowException();

        String minItem = findMin();
        array[1] = array[currentSize--];
        percolateDown(1);

        return minItem;
    }

    private void percolateDown(int hole) {
        int child;
        String tmp = array[hole];

        for (; hole * 2 <= currentSize; hole = child) {
            child = hole * 2;
            if (child != currentSize && array[child + 1].compareTo(array[child]) < 0)
                child++;

            if (array[child].compareTo(tmp) < 0)
                array[hole] = array[child];
            else
                break;
        }

        array[hole] = tmp;
    }

    public String findMin() {
        if (isEmpty())
            throw new BufferUnderflowException();

        return array[1];
    }
}
