package Cheapter_5;

import java.util.Random;

public class StringHashFamily implements HashFamily<String> {
    private final int[] MULTIPLIERS;
    private final Random r = new Random();

    public StringHashFamily(int d) {
        MULTIPLIERS = new int[d];
        generateNewFunctions();
    }

    @Override
    public int hash(String x, int which) {
        final int multiplier = MULTIPLIERS[which];
        int hashVal = 0;

        for (int i = 0; i < x.length(); i++)
            hashVal = multiplier * hashVal + x.charAt(i);

        return hashVal;
    }

    @Override
    public int getNumberOfFunctions() {
        return MULTIPLIERS.length;
    }

    @Override
    public void generateNewFunctions() {
        for (int i = 0; i < MULTIPLIERS.length;i++)
            MULTIPLIERS[i] = r.nextInt();
    }

    private static HashFamily<String> hashFamily = new HashFamily<String>() {
        @Override
        public int hash(String x, int which) {
            int hashVal = 0;
            switch (which) {
                case 0: {
                    for (int i = 0; i < x.length(); i++) {
                        hashVal += x.charAt(i);
                    }
                    break;
                }
                case 1:
                    for (int i = 0; i < x.length(); i++) {
                        hashVal = 37 * hashVal + x.charAt(i);
                    }
                    break;
            }
            return hashVal;
        }

        @Override
        public int getNumberOfFunctions() {
            return 2;
        }

        @Override
        public void generateNewFunctions() {

        }
    };

    public static void main(String[] args) {
        CuckooHashTable<String> hashTable = new CuckooHashTable<String>(hashFamily, 7);
        String[] str = {"abc", "ADB", "ABCD", "XXX", "zxc"};

        for (int i = 0; i < str.length; i++)
            hashTable.insert(str[i]);

        hashTable.printArray();
    }
}
