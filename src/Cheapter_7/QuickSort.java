package Cheapter_7;

import java.util.Arrays;

public class QuickSort {
    private static final int CUTOFF = 10;

    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
        System.out.println("Quick sort: " + Arrays.toString(array));
    }

    private static void quickSort(int[] array, int left, int right) {
        if (left + CUTOFF <= right) {
            int pivot = median3(array, left, right);

            // Begin partitioning
            int i = left, j = right - 1;
            for (; ; ) {
                while (array[++i] < pivot) {
                }
                while (array[--j] > pivot) {
                }

                if (i < j)
                    swap(array, i, j);
                else
                    break;
            }

            swap(array, i, right - 1);     // Restore pivot
            quickSort(array, left, i - 1); // Sort small elements
            quickSort(array, i + 1, right); // Sort large elements
        } else {
            insertionSort(array);
        }
    }


    private static int median3(int[] array, int left, int right) {
        int center = (left + right) / 2;

        if (array[center] < array[left])
            swap(array, left, center);
        if (array[right] < array[left])
            swap(array, left, right);
        if (array[right] < array[center])
            swap(array, center, right);

        swap(array, center, right - 1);

        return array[right - 1];
    }

    private static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    private static int[] insertionSort(int[] array) {
        int j;
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            for (j = i; j > 0 && temp < array[j - 1]; j--)
                array[j] = array[j - 1];
            array[j] = temp;
        }
        return array;
    }
}
