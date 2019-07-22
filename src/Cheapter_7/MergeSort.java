package Cheapter_7;

import java.util.Arrays;

public class MergeSort {


    public static void mergeSort(int[] a) {
        int[] temp = new int[a.length];
        mergeSort(a, temp, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    public static void mergeSort(int[] a, int[] temp, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;

            mergeSort(a, temp, left, center);
            mergeSort(a, temp, center + 1, right);
            merge(a, temp, left, center + 1, right);
        }
    }

    public static void merge(int[] a, int[] temp, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos -1;
        int tempPos = leftPos;
        int num = rightEnd - leftPos + 1;

        // Main loop
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (a[leftPos] <= a[rightPos])
                temp[tempPos++] = a[leftPos++];
            else
                temp[tempPos++] = a[rightPos++];
        }

        // Copy rest of first half
        while (leftPos <= leftEnd)
            temp[tempPos++] = a[leftPos++];

        // Copy rest of right half
        while (rightPos <= rightEnd)
            temp[tempPos++] = a[rightPos++];

        // Copy temp array back
        for (int i = 0; i < num; i++, rightEnd--)
            a[rightEnd] = temp[rightEnd];
    }
}
