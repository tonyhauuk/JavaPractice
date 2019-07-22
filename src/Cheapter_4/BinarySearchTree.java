package Cheapter_4;


import java.nio.BufferUnderflowException;
import java.util.Comparator;

public class BinarySearchTree<Integer extends Comparable<? super Integer>> {
    private BinaryNode<Integer> root;

    private static class BinaryNode<Integer> {
        Integer element;
        BinaryNode<Integer> left, right;

        BinaryNode(Integer theElement) {
            this(theElement, null, null);
        }

        BinaryNode(Integer theElement, BinaryNode<Integer> lt, BinaryNode<Integer> rt) {
            element = theElement;
            left = lt;
            right = rt;
        }
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(Integer x) {
        return contains(x, root);
    }

    public Integer findMax() {
        if (isEmpty())
            throw new BufferUnderflowException();

        return findMax(root).element;
    }

    public Integer findMin() {
        if (isEmpty())
            throw new BufferUnderflowException();

        return findMin(root).element;
    }

    public void insert(Integer x) {
        root = insert(x, root);
    }

    public void remove(Integer x) {
        root = remove(x, root);
    }

    public void printTree() {
        if (isEmpty())
            System.out.println("Empty tree");
        else
            printTree(root);

    }

    private void printTree(BinaryNode<Integer> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }

    private int height(BinaryNode<Integer> t) {
        if (t == null)
            return -1;
        else
            return 1 + Math.max(height(t.left), height(t.right));
    }

    private boolean contains(Integer x, BinaryNode<Integer> t) {
        if (t == null)
            return false;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            return contains(x, t.left);
        else if (compareResult > 0)
            return contains(x, t.right);
        else
            return true;
    }

    private BinaryNode<Integer> findMin(BinaryNode<Integer> t) {
        if (t == null)
            return null;
        else if (t.left == null)
            return t;

        return findMin(t.left);
    }

    private BinaryNode<Integer> findMax(BinaryNode<Integer> t) {
        while (t != null)
            while (t.right != null)
                t = t.right;

        return t;
    }

    private BinaryNode<Integer> insert(Integer x, BinaryNode<Integer> t) {
        if (t == null)
            return new BinaryNode<>(x, null, null);

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = insert(x, t.left);
        else if (compareResult > 0)
            t.right = insert(x, t.right);
        else
            ;

        return t;
    }

    private BinaryNode<Integer> remove(Integer x, BinaryNode<Integer> t) {
        if (t == null)
            return t;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = remove(x, t.left);
        else if (compareResult > 0)
            t.right = remove(x, t.right);
        else if (t.left != null && t.right != null) {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else
            t = (t.left != null) ? t.left : t.right;

        return t;
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(6);
        tree.insert(2);
        tree.insert(8);

        System.out.println(tree.findMin());
    }
}