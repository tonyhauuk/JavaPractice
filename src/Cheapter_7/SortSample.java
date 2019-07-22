package Cheapter_7;

import java.util.Arrays;

public class SortSample {
    public static void main(String[] args) {

        doTest();
    }

    private static void doTest() {
        int[] arr = {87987, 4, 587, 637, 6, -51, -7, 9, 5, 47, 98465, 87, 768, 78, 695, 8, 981, 98, 7, 6, 879, 252, 18, 79, 54, 8, 98, 6354, -1, 1, 5, -476, 87, 65, 7, 98, 5, 65, 77, 667, 97, 97, 66, 8, 7, 46};
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
        bubbleSort(arr);
    }



    public static void insertionSort(int[] a) {
        int i;

        for (int p = 1; p < a.length; p++) {
            int tmp = a[p];
            for (i = p; i > 0 && tmp < a[i - 1]; i--) {
                a[i] = a[i - 1];
            }

            a[i] = tmp;
            System.out.println(a[i]);
        }
    }

    public static void bubbleSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            boolean flag = true;

            for (int j = 0; j < a.length - i; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    flag = false;
                }
            }
            if (flag)
                break;
        }
        System.out.println(Arrays.toString(a));
    }

    public static void selectionSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[min])
                    min = j;
            }
            if (i != min) {
                int tmp = a[i];
                a[i] = a[min];
                a[min] = tmp;
            }
        }
        System.out.println(a);
    }

    public static void shellSort(int[] a) {
        int j;
        for (int gap = a.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < a.length; i++) {
                int temp = a[i];
                for (j = i; j >= gap && temp < a[j - gap]; j -= gap)
                    a[j] = a[j - gap];

                a[j] = temp;
            }
        }
    }

    public static void shellKnuthSort(int[] a) {
        int h = 1;
        int length = a.length;

        while (h <= length / 3)
            h = h * 3 + 1;

        while (h > 0) {
            for (int i = h; i < length; i++) {
                int temp = a[i];
                int j = i;

                if (a[j] < a[j - h]) {
                    while (j - h >= 0 && temp < a[j - h]) {
                        a[j] = a[j - h];
                        j -= h;
                    }
                    a[j] = temp;
                }
            }
            h = (h - 1) / 3;
        }
        System.out.println("Steps: " + h + " : " + Arrays.toString(a));
    }


}
