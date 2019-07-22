package Cheapter_1;


public class BinarySearch {
    public static void main(String[] args) {
//        System.out.println("Welcome to Java!");
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        int key = 19;
        int ret = binarySearch(a, key);
//        System.out.println("Result: " + ret);

//        int x = 770, y = 256;
//        gcd(x, y);

    }

    public static int gcd(int x, int y) {
        while (x != 0) {
            int rem = x % y;
            x = y;
            y = rem;
        }
        return x;
    }

    public static int binarySearch(int[] a, int key) {
        int low = 0, high = a.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            System.out.println("Middle is: " + a[mid]);
            if (a[mid] < key)
                low = mid + 1;
            else if (a[mid] > key)
                high = mid - 1;
            else
                return mid;
        }

        return -1; // Not found
    }


}
