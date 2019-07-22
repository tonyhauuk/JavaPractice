package Cheapter_10;

import com.sun.org.apache.regexp.internal.RE;

public class RedBlackTree<String extends Comparable<? super String>> {
    private RedBlackNode<String> header;
    private RedBlackNode<String> nullNode;

    private static final int BLACK = 1;
    private static final int RED = 0;

    private RedBlackNode<String> current;
    private RedBlackNode<String> parent;
    private RedBlackNode<String> grand;
    private RedBlackNode<String> great;


    public RedBlackTree() {
        nullNode = new RedBlackNode<>(null);
        nullNode.left = nullNode.right = nullNode;
        header = new RedBlackNode<>(null);
        header.left = header.right = nullNode;
    }

    private static class RedBlackNode<String> {
        String element; // The data int the node
        RedBlackNode<String> left;  // Left child
        RedBlackNode<String> right; // Right child
        int color;

        RedBlackNode(String theElement) {
            this(theElement, null, null);
        }

        RedBlackNode(String theElement, RedBlackNode<String> lt, RedBlackNode<String> rt) {
            element = theElement;
            left = lt;
            right = rt;
            color = RedBlackTree.BLACK;
        }
    }

    private RedBlackNode<String> rotate(String item, RedBlackNode<String> parent) {
        if (compare(item, parent) < 0) {
            parent.left = compare(item, parent.left) < 0 ? rotateWithLeftChild(parent.left) : rotateWithRightChild(parent.left);
            return parent.left;
        } else {
            parent.right = compare(item, parent.right) < 0 ? rotateWithLeftChild(parent.right) : rotateWithRightChild(parent.right);
            return parent.right;
        }
    }

    private final int compare(String item, RedBlackNode<String> t) {
        if (t == header)
            return 1;
        else
            return item.compareTo(t.element);
    }

    /**
     * Rotate binary tree node with left child.
     */
    private RedBlackNode<String> rotateWithLeftChild(RedBlackNode<String> k2) {
        RedBlackNode<String> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        return k1;
    }

    /**
     * Rotate binary tree node with right child.
     */
    private RedBlackNode<String> rotateWithRightChild(RedBlackNode<String> k1) {
        RedBlackNode<String> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        return k2;
    }

    private void handelProrient(String item) {
        // Do the color filp
        current.color = RED;
        current.left.color = BLACK;
        current.right.color = BLACK;

        if (parent.color == RED) {
            grand.color = RED;
            if ((compare(item, grand) < 0) != (compare(item, parent) < 0))
                parent = rotate(item, great);

            current = rotate(item, great);
            current.color = BLACK;
        }
        header.right.color = BLACK;
    }

    public void insert(String item) {
        current = parent = grand = header;
        nullNode.element = item;
        while (compare(item, current) != 0) {
            great = grand;
            grand = parent;
            parent = current;

            current = compare(item, current) < 0 ? current.left : current.right;
            // Check if two red children; fix if so
            if (current.left.color == RED && current.right.color == RED)
                handelProrient(item);
        }

        if (current != nullNode)
            return;

        current = new RedBlackNode<>(item, nullNode, nullNode);
        // Attach to parent
        if (compare(item, parent) < 0)
            parent.left = current;
        else
            parent.right = current;

        handelProrient(item);
    }


    public void printTree() {
        if (isEmpty())
            System.out.println("Empty Tree");
        else
            printTree(header.right);
    }

    public void printTree(RedBlackNode<String> t) {
        if (t != nullNode) {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }

    private boolean isEmpty() {
        return header.right == nullNode;
    }
}
