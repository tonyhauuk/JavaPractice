package Cheapter_5;

public class HashDemo {
    public static void main(String[] args) {

        test();
    }

    public static void test() {
        String key = "HashTest";
        int tablesSize = 10007;
        int hashInt = hash3(key, tablesSize);
        System.out.println(hashInt);
    }

    public static int hash1(String key, int tableSize) {
        int hashVal = 0;
        for (int i = 0; i < key.length(); i++)
            hashVal += key.charAt(i);

        return hashVal % tableSize;
    }

    public static int hash2(String key, int tableSize) {
        return (key.charAt(0) + 27 * key.charAt(1) + 729 * key.charAt(2)) % tableSize;
    }

    public static int hash3(String key, int tableSize) {
        int hashVal = 0;

        for (int i = 0; i < key.length(); i++)
            hashVal = 37 * hashVal + key.charAt(i);

        hashVal %= tableSize;
        if (hashVal < 0)
            hashVal += tableSize;

        return hashVal;
    }
}
