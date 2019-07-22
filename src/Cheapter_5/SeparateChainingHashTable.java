package Cheapter_5;

import java.util.LinkedList;
import java.util.List;

public class SeparateChainingHashTable<String> {
    private static final int DEFAULT_TABLE_SIZE = 100;
    private List<String>[] theLists;
    private int currentSize;


    public SeparateChainingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public SeparateChainingHashTable(int size) {
        theLists = new LinkedList[nextPrime(size)];
        for (int i = 0; i < theLists.length; i++)
            theLists[i] = new LinkedList<>();
    }

    public void makeEmpty() {
        for (int i = 0; i < theLists.length; i++)
            theLists[i].clear();
        currentSize = 0;
    }

    public void insert(String x) {
        List<String> whichList = theLists[myHash(x)];
        if (!whichList.contains(x)) {
            whichList.add(x);

            if (++currentSize > theLists.length)
                rehash();
        }
    }

    public boolean contains(String x) {
        List<String> whichList = theLists[myHash(x)];

        return whichList.contains(x);
    }

    public void remove(String x) {
        List<String> whichList = theLists[myHash(x)];
        if (!whichList.contains(x)) {
            whichList.remove(x);
            currentSize--;
        }
    }

    private void rehash() {
        List<String>[] oldLists = theLists;
        theLists = new List[nextPrime(2 * theLists.length)];
        for (int j = 0; j < theLists.length; j++)
            theLists[j] = new LinkedList<>();

        currentSize = 0;
        for (int i = 0; i < oldLists.length; i++)
            for (String item : oldLists[i])
                insert(item);
    }

    private int myHash(String x) {
        int hashVal = x.hashCode();

        hashVal %= theLists.length;
        if (hashVal < 0)
            hashVal += theLists.length;

        return hashVal;
    }

    private static int nextPrime(int n) {
        while (!isPrime(n))
            n++;

        return n;
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++)
            if (n % i == 0 && n != 2)
                return false;

        return true;
    }


}
