package Cheapter_5;

import java.util.Random;

public class CuckooHashTable<String> {
    private static final double MAX_LOAD = 0.4;
    private static final int ALLOWED_REHASHES = 1;
    private static final int DEFAULT_TABLE_SIZE = 101;

    private final HashFamily<? super String> hashFunctions;
    private final int numHashFunctions;
    private String[] array;
    private int currentSize;

    private int rehashes = 0;
    private Random r = new Random();

    public CuckooHashTable(HashFamily<? super String> hf) {
        this(hf, DEFAULT_TABLE_SIZE);
    }

    public CuckooHashTable(HashFamily<? super String> hf, int size) {
        allocateArray(nextPrime(size));
        doClear();
        hashFunctions = hf;
        numHashFunctions = hf.getNumberOfFunctions();
    }

    private void doClear() {
        currentSize = 0;
        for (int i = 0; i < array.length; i++)
            array[i] = null;
    }

    private void allocateArray(int arraySize) {
        array = (String[]) new Object[arraySize];
    }

    public void makeEmpty() {
        doClear();
    }

    private int myhash(String x, int which) {
        int hashVal = hashFunctions.hash(x, which);

        hashVal %= array.length;
        if (hashVal < 0)
            hashVal += array.length;

        return hashVal;
    }

    private int findPos(String x) {
        for (int i = 0; i < numHashFunctions; i++) {
            int pos = myhash(x, i);
            if (array[pos] != null && array[pos].equals(x))
                return pos;
        }

        return -1;
    }

    public boolean contains(String x) {
        return findPos(x) != -1;
    }

    public boolean remove(String x) {
        int pos = findPos(x);

        if (pos != -1) {
            array[pos] = null;
            currentSize--;
        }

        return pos != -1;
    }

    public boolean insert(String x) {
        if (contains(x))
            return false;

        if (currentSize >= array.length * MAX_LOAD)
            expand();

        return insertHelper1(x);
    }

    private boolean insertHelper1(String x) {
        final int COUNT_LIMIT = 100;

        while (true) {
            int lastPos = -1;
            int pos;

            for (int count = 0; count < COUNT_LIMIT; count++) {
                for (int i = 0; i < numHashFunctions; i++) {
                    pos = myhash(x, i);

                    if (array[pos] == null) {
                        array[pos] = x;
                        currentSize++;

                        return true;
                    }
                }

                int i = 0;
                do {
                    pos = myhash(x, r.nextInt(numHashFunctions));
                } while (pos == lastPos && i++ < 5);

                String temp = array[lastPos = pos];
                array[pos] = x;
                x = temp;
            }

            if (++rehashes > ALLOWED_REHASHES) {
                expand();
                rehashes = 0;
            } else {
                rehash();
            }
        }
    }

    private void expand() {
        rehash((int) (array.length / MAX_LOAD));
    }

    private void rehash() {
        hashFunctions.generateNewFunctions();
        rehash(array.length);
    }

    private void rehash(int newLength) {
        String[] old = array;
        allocateArray(nextPrime(newLength));
        currentSize = 0;

        for (String str : old)
            if (str != null)
                insert(str);
    }

    protected static int nextPrime(int n) {
        if (n % 2 == 0)
            n++;

        for (; !isPrime(n); n += 2)
            ;

        return n;
    }


    private static boolean isPrime(int n) {
        if (n == 2 || n == 3)
            return true;

        if (n == 1 || n % 2 == 0)
            return false;

        for (int i = 3; i * i <= n; i += 2)
            if (n % i == 0)
                return false;

        return true;
    }

    public void printArray() {
        System.out.println("Array size is: " + array.length);
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null)
                System.out.println("current pos: " + i + " current value: " + array[i]);
        }
    }
}
