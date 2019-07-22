package Cheapter_5;

public class QuadraticProblingHashTable<String> {
    private static final int DEFAULT_TABLE_SIZE = 100;
    private HashEntry<String>[] array;
    private int currentSize;

    public QuadraticProblingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public QuadraticProblingHashTable(int size) {
        allocateArray(size);
        makeEmpty();
    }

    public void makeEmpty() {
        currentSize = 0;
        for (int i = 0; i < array.length; i++)
            array[i] = null;
    }

    private void allocateArray(int size) {
        array = new HashEntry[nextPrime(size)];
    }

    private static class HashEntry<String> {
        public String element;
        public boolean isActive;

        public HashEntry(String e) {
            this(e, true);
        }

        public HashEntry(String e, boolean i) {
            element = e;
            isActive = i;
        }
    }

    public boolean contains(String x) {
        int currentPos = findPos(x);

        return isActive(currentPos);
    }

    private int findPos(String x) {
        int offset = 1;
        int currentPos = myhash(x);

        while (array[currentPos] != null && !array[currentPos].element.equals(x)) {
            currentPos += offset;
            offset += 2;
            if (currentPos >= array.length)
                currentPos -= array.length;
        }

        return currentPos;
    }

    private boolean isActive(int position) {
        boolean ret = array[position] != null && array[position].isActive;

        return ret;
    }

    public void insert(String x) {
        int currentPos = findPos(x);
        if (isActive(currentPos))
            return;

        array[currentPos] = new HashEntry<>(x, true);

        if (currentSize > array.length / 2)
            rehash();
    }

    public void remove(String x) {
        int currentPos = findPos(x);
        if (isActive(currentPos))
            array[currentPos].isActive = false;
    }

    private void rehash() {
        HashEntry<String>[] oldArray = array;

        allocateArray(nextPrime(2 * oldArray.length));
        currentSize = 0;

        for (int i = 0; i < oldArray.length; i++)
            if (oldArray[i] != null && oldArray[i].isActive)
                insert(oldArray[i].element);
    }

    private int myhash(String x) {
        int hashVal = x.hashCode();

        hashVal %= array.length;
        if (hashVal < 0)
            hashVal += array.length;

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
