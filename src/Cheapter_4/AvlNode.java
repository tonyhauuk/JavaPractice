package Cheapter_4;

import java.lang.String;

public class AvlNode<String extends Comparable<? super String>> {
    private static final int ALLOWED_IMBALANCE = 1;

    private static class AvlTreeNode<String> {
        String element;
        AvlTreeNode<String> child;

        AvlTreeNode<String> left, right;
        int height;

        AvlTreeNode(String theElement) {
            this(theElement, null, null);
        }

        AvlTreeNode(String theElement, AvlTreeNode<String> lt, AvlTreeNode<String> rt) {
            element = theElement;
            left = lt;
            right = rt;
            height = 0;
        }

        public void makeEmpty() {
            child = null;
        }
    }

    private int height(AvlTreeNode<String> t) {
        return t == null ? -1 : t.height;
    }

    private AvlTreeNode<String> insert(String x, AvlTreeNode<String> t) {
        if (t == null)
            return new AvlTreeNode<>(x, null, null);

        int compare = x.compareTo(t.element);

        if (compare < 0)
            t.left = insert(x, t.left);
        else if (compare > 0)
            t.right = insert(x, t.right);
        else
            ;
        return balance(t);
    }

    private AvlTreeNode<String> balance(AvlTreeNode<String> t) {
        if (t == null)
            return t;

        if (height(t.left) - height(t.right) > ALLOWED_IMBALANCE)
            if (height(t.left.left) >= height(t.left.right))
                t = rotateWithLeftChild(t);
            else
                t = doubleWithLeftChild(t);
        else
            if (height(t.right) - height(t.left) > ALLOWED_IMBALANCE)
                if (height(t.right.right) >= height(t.right.left))
                    t = rotateWithLeftChild(t);
                else
                    t = doubleWithLeftChild(t);

        t.height = Math.max(height(t.left), height(t.right)) + 1;

        return t;
    }

    private AvlTreeNode<String> rotateWithLeftChild(AvlTreeNode<String> k2) {
        AvlTreeNode<String> k1 = k2.child;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;

        return k1;
    }

    private AvlTreeNode<String> doubleWithLeftChild(AvlTreeNode<String> k3) {
        k3.left = rotateWithLeftChild(k3.left);

        return rotateWithLeftChild(k3);
    }

    private AvlTreeNode<String> remove(String x, AvlTreeNode<String> t) {
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

        return balance(t);
    }

    private AvlTreeNode<String> findMin(AvlTreeNode<String> t) {
        if (t == null)
            return null;
        else if (t.left == null)
            return t;

        return findMin(t.left);
    }
}
