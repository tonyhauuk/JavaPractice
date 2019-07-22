package Cheapter_3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList implements Iterable<Integer> {

    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Integer[] theItems;

    public MyArrayList() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    private void doClear() {
        size = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void trimToSize() {
        ensureCapacity(size());
    }

    public Integer get(int index) {
        if (index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();

        return theItems[index];
    }

    public Integer set(int index, Integer newValue) {
        if (index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();

        Integer old = theItems[index];
        theItems[index] = newValue;

        return old;
    }

    public void ensureCapacity(int newCapacity) {
        if (newCapacity < size)
            return;

        Integer[] old = theItems;
        theItems =  (Integer[]) new Object[newCapacity];
        for (int i = 0; i < size; i++)
            theItems[i] = old[i];
    }

    public boolean add(int x) {
        add(size(), x);
        return true;
    }

    public void add (int index, int x) {
        if (theItems.length == size)
            ensureCapacity(size() * 2 + 1);

        for (int i = size; i > index; i--)
            theItems[i] = theItems[i - 1];
        theItems[index] = x;
        size++;
    }

    public Integer remove(int index) {
        Integer removeItem = theItems[index];
        for (int i = index; i < size() - 1; i++)
            theItems[i] = theItems[i + 1];

        size--;

        return removeItem;
    }

    public Iterator<Integer> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<Integer> {
        private int current = 0;

        public boolean hasNext() {
            return current < size();
        }

        public Integer next() {
            if (!hasNext())
                throw new NoSuchElementException();

            return theItems[current++];
        }

        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }

    public static void main(String[] args) {
        MyArrayList arrayList = new MyArrayList();
        arrayList.clear();
    }
}
