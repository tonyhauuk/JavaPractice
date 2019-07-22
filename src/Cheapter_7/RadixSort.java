package Cheapter_7;

import java.util.ArrayList;
import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        sort();
    }

    public static void sort() {
        String[] arr = {"064", "008", "216", "729"};
        int len = arr[0].length();
        radixSort(arr, len);
    }

    public static void radixSort(String[] arr, int len) {
        final int BUCKETS = 256;
        ArrayList<String>[] buckets = new ArrayList[BUCKETS];

        for (int i = 0; i < BUCKETS; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (int pos = len - 1; pos >= 0; pos--) {
            for (String s : arr)
                buckets[s.charAt(pos)].add(s);

            int idx = 0;
            for (ArrayList<String> thisBuckets : buckets) {
                for (String s : thisBuckets)
                    arr[idx++] = s;

                thisBuckets.clear();
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void countingRadixSort(String[] arr, int len) {
        final int BUCKETS = 256;
        int N = arr.length;
        String[] buffer = new String[N];
        String[] in = arr;
        String[] out = buffer;

        for (int pos = len - 1; pos >= 0; pos--) {
            int[] count = new int[BUCKETS + 1];

            for (int i = 0; i < N; i++)
                count[in[i].charAt(pos) + 1]++;

            for (int j = 1; j <= BUCKETS; j++)
                count[j] += count[j - 1];

            for (int i = 0; i < N; i++)
                out[count[in[i].charAt(pos)]++] = in[i];

            String[] temp = in;
            in = out;
            out = temp;
        }

        if (len % 2 == 0)
            for (int i = 0; i < arr.length; i++)
                out[i] = in[i];
    }
}
