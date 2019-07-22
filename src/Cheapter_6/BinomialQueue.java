package Cheapter_6;

import java.nio.BufferUnderflowException;

public class BinomialQueue<Integer extends Comparable<? super Integer>> {
    private static final int DEFAULT_TREES = 1;
    private int currentSize;
    private Node<Integer>[] theTrees;

    private static class Node<Integer> {
        Integer element;
        Node<Integer> leftChild, nextSibling;

        Node(Integer item) {
            this(item, null, null);
        }

        Node(Integer item, Node<Integer> lt, Node<Integer> nt) {
            element = item;
            leftChild = lt;
            nextSibling = nt;
        }
    }

    public BinomialQueue() {
        theTrees = new Node[DEFAULT_TREES];
        makeEmpty();
    }

    public BinomialQueue(Integer item) {
        currentSize = 1;
        theTrees = new Node[1];
        theTrees[0] = new Node<Integer>(item, null, null);
    }


    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void makeEmpty() {
        currentSize = 0;
        for (int i = 0; i < theTrees.length; i++)
            theTrees[i] = null;
    }

    private int capacity() {
        return (1 << theTrees.length) - 1;
    }

    private Node<Integer> combineTrees(Node<Integer> t1, Node<Integer> t2) {
        if (t1.element.compareTo(t2.element) > 0)
            return combineTrees(t2, t1);

        t2.nextSibling = t1.leftChild;
        t1.leftChild = t2;

        return t1;
    }

    public void merge(BinomialQueue<Integer> rhs) {
        if (this == rhs)    // Avoid aliasing problems
            return;

        currentSize += rhs.currentSize;

        if (currentSize > capacity()) {
            int newNumTrees = Math.max(theTrees.length, rhs.theTrees.length) + 1;
            expandTheTrees(newNumTrees);
        }

        Node<Integer> carry = null;
        for (int i = 0, j = 1; j <= currentSize; i++, j *= 2) {
            Node<Integer> t1 = theTrees[i];
            Node<Integer> t2 = i < rhs.theTrees.length ? rhs.theTrees[i] : null;

            int whichCase = t1 == null ? 0 : 1;
            whichCase += t2 == null ? 0 : 2;
            whichCase += carry == null ? 0 : 4;

            switch (whichCase) {
                case 0: /* No trees */
                case 1: /* Only this */
                    break;
                case 2: /* Only rhs */
                    theTrees[i] = t2;
                    rhs.theTrees[i] = null;
                    break;
                case 4: /* Only carry */
                    theTrees[i] = carry;
                    carry = null;
                    break;
                case 3: /* this and rhs */
                    carry = combineTrees(t1, t2);
                    theTrees[i] = rhs.theTrees[i] = null;
                    break;
                case 5: /* this and carry */
                    carry = combineTrees(t1, carry);
                    theTrees[i] = null;
                    break;
                case 6: /* rhs and carry */
                    carry = combineTrees(t2, carry);
                    rhs.theTrees[i] = null;
                    break;
                case 7: /* All three */
                    theTrees[i] = carry;
                    carry = combineTrees(t1, t2);
                    rhs.theTrees[i] = null;
                    break;
            }
        }

        for (int k = 0; k < rhs.theTrees.length; k++)
            rhs.theTrees[k] = null;

        rhs.currentSize = 0;
    }

    private void expandTheTrees(int newNumTrees) {
        Node<Integer>[] old = theTrees;
        int oldNumTrees = theTrees.length;

        theTrees = new Node[newNumTrees];
        for (int i = 0; i < Math.min(oldNumTrees, newNumTrees); i++)
            theTrees[i] = old[i];
        for (int i = oldNumTrees; i < newNumTrees; i++)
            theTrees[i] = null;
    }

    public void insert(Integer x) {
        merge(new BinomialQueue<>(x));
    }

    public Integer findMin() {
        if (isEmpty())
            throw new BufferUnderflowException();

        return theTrees[findMinIndex()].element;
    }

    private int findMinIndex() {
        int i;
        int minIndex;

        for (i = 0; theTrees[i] == null; i++)
            ;

        for (minIndex = i; i < theTrees.length; i++)
            if (theTrees[i] != null && theTrees[i].element.compareTo(theTrees[minIndex].element) < 0)
                minIndex = i;

        return minIndex;
    }

    public Integer deleteMin() {
        if (isEmpty())
            throw new BufferUnderflowException();

        int minIndex = findMinIndex();
        Integer minItem = theTrees[minIndex].element;

        Node<Integer> deletedTree = theTrees[minIndex].leftChild;

        // Construct H''
        BinomialQueue<Integer> deletedQueue = new BinomialQueue<>();
        deletedQueue.expandTheTrees(minIndex);

        deletedQueue.currentSize = (1 << minIndex) - 1;

        for (int j = minIndex - 1; j >= 0; j--) {
            deletedQueue.theTrees[j] = deletedTree;
            deletedTree = deletedTree.nextSibling;
            deletedQueue.theTrees[j].nextSibling = null;
        }

        // Construct H'
        theTrees[minIndex] = null;
        currentSize -= deletedQueue.currentSize + 1;

        merge(deletedQueue);

        return minItem;
    }
}
