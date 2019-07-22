package Cheapter_7;

import java.util.Arrays;

public class HeapSort {

    public static void heapSort(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--)
            percDown(array, i, array.length);

        for (int i = array.length - 1; i > 0; i--) {
            swap(array, 0, i);  // Delete Max
            percDown(array, 0, i);
        }

        System.out.println("Heap Sort: " + Arrays.toString(array));
    }

    private static void percDown(int[] array, int i, int n) {
        int child;
        int temp;

        for (temp = array[i]; leftChild(i) < n; i = child) {
            child = leftChild(i);

            if (child != n - 1 && array[child] < array[child + 1])
                child++;

            if (temp < array[child])
                array[i] = array[child];
            else
                break;
        }
        array[i] = temp;
    }

    private static int leftChild(int i) {

        return 2 * i + 1;
    }

    private static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
