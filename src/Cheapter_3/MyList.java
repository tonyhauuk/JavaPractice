package Cheapter_3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MyList {
    public static void main(String[] args) {
        list();
    }

    public static void list() {
        LinkedList<Integer> linkLst = new LinkedList<>();
        ArrayList<Integer> arrLst = new ArrayList<>();

        int n = 400000;
        // Both O(N)
        makeList1(linkLst, n);
        makeList1(arrLst, n);

        // LinkedList O(N), ArrayList O(n2)
        makeList2(linkLst, n);
        makeList2(arrLst, n);

        int sum1 = sum(linkLst, n);
        int sum2 = sum(arrLst, n);
        System.out.println("Sum 1: " + sum1 + "\t Sum 2: " + sum2);

        removeEven(linkLst, n);
        removeEven(arrLst, n);
    }

    private static void makeList1(List<Integer> lst, int n) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++)
            lst.add(i);
        long end = System.currentTimeMillis();
        System.out.println("makeList1 Used time: " + (end - start));
    }

    private static void makeList2(List<Integer> lst, int n) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++)
            lst.add(0, i);
        long end = System.currentTimeMillis();
        System.out.println("makeList2 Used time: " + (end - start));
    }

    private static int sum(List<Integer> lst, int n) {
        for (int i = 0; i < n; i++)
            lst.add(i);

        long start = System.currentTimeMillis();
        int total = 0;
        for(int i = 0; i < n; i++)
            total += lst.get(i);
        long end = System.currentTimeMillis();
        System.out.println("Sum Used time: " + (end - start));

        return total;
    }

    private static void removeEven(List<Integer> lst, int n) {
        for (int i = 0; i < n; i++)
            lst.add(i);

        Iterator<Integer> itr = lst.iterator();
        long start = System.currentTimeMillis();
        while (itr.hasNext()) {
            if (itr.next() % 2 == 0)
                itr.remove();
        }
        long end = System.currentTimeMillis();
        System.out.println("removeEven Used time: " + (end - start));
    }
}

