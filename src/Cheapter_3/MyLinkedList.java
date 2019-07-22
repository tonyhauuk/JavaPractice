package Cheapter_3;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<String> implements Iterable<String> {
    private int theSize;
    private int modCount;
    private Node<String> beginMarker;
    private Node<String> endMarker;

    private static class Node<String> {
        public String data;
        public Node<String> prev;
        public Node<String> next;

        public Node(String d, Node<String> p, Node<String> n) {
            data = d;
            prev = p;
            next = n;
        }
    }

    public MyLinkedList() {
        doClear();
    }

    public void doClear() {
        beginMarker = new Node<String>(null, null, null);
        endMarker = new Node<>(null, beginMarker, null);
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean add(String x) {
        add(size(), x);
        return true;
    }

    public void add(int idx, String x) {
        addBefore(getNode(idx, 0, size()), x);
    }

    public String get(int idx) {
        return getNode(idx).data;
    }

    public String set(int idx, String newValue) {
        Node<String> p = getNode(idx);
        String oldValue = p.data;
        p.data = newValue;

        return oldValue;
    }

    public String remove(int idx) {
        return remove(getNode(idx));
    }

    private void addBefore(Node<String> p, String x) {
        Node<String> newNode = new Node<>(x, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }

    private String remove(Node<String> p) {
        p.prev.next = p.next;
        p.next.prev = p.prev;
        theSize--;
        modCount--;

        return p.data;
    }

    private Node<String> getNode(int idx) {
        return getNode(idx, 0, size() - 1);
    }

    private Node<String> getNode(int idx, int lower, int upper) {
        Node<String> p;

        if (idx < lower || idx > upper)
            throw new IndexOutOfBoundsException();

        if (idx < size() / 2) {
            p = beginMarker.next;
            for (int i = 0; i < idx; i++)
                p = p.next;
        } else {
            p = endMarker;
            for (int i = size(); i > idx; i--)
                p = p.prev;
        }

        return p;
    }

    public Iterator<String> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<String> {
        private Node<String> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        public boolean hasNext() {
            return current != endMarker;
        }

        public String next() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (!hasNext())
                throw new NoSuchElementException();

            String nextItem = current.data;
            current = current.next;
            okToRemove = true;

            return nextItem;
        }

        public void remove() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (!okToRemove)
                throw new IllegalStateException();

            MyLinkedList.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false;
        }
    }

    public static void main(java.lang.String[] args) {
        MyLinkedList<java.lang.String> list = new MyLinkedList<>();
        for (int i = 0; i < 5; i++)
            list.set(i, java.lang.String.valueOf(i));

        System.out.println(list);
    }
}

