package Cheapter_1;

import java.util.LinkedList;

public class Generic {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(5);

        int val = list.element();
        System.out.println("Contents are: " + val);
    }
}
