package Cheapter_4;

public class TreeNode {
    Object element;
    TreeNode firstChild;
    TreeNode nextSibling;

    public void listAll() {
        listAll(0);
    }

    private void listAll(int depth) {
        printName(depth);
    }

    private static void printName(int n) {
        System.out.println("The depth: " + n);

    }

    public void test() {
        System.out.println("Test;");
    }
}
