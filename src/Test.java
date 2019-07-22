import Cheapter_7.HeapSort;
import Cheapter_7.MergeSort;
import Cheapter_7.QuickSort;
import Cheapter_7.ShellSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import static java.lang.Thread.yield;

public class Test {
    public static void main(String[] args) {
        int[] a = {8987, -740, 4, 587, 637, 6, -51, -7, 9, 47, 7, 98, 5, 65, 77, 667, 97, 46};
        int[] b = {-4, 987, 1, 987, 3, 87, 7, 0, 9, 95, 1, 51, 5987, 98, 95, 46, 87, 98, 7};
        int[] c = {-587, 0, 8, 9, 8, 7, 1, 22, 66, 44, 55, 77, 99, 88, -999};
        int[] arr = {87987, 4, 587, 637, 4879871, 6, -51, 7, 9, 5, 47, 98465, 87, 768, 78, 695, 8, 981, 98, 7, 6, 879, 252, 18, 79, 54, 8, 98, 6354, 1, 1, 5, 476, 87, 65, 7, 98, 5, 65, 77, 667, 97, 97, 66, 8, 7, 46};
        pickup();
        Vector<Integer> v = new Vector<>();
        v.add(1);
        v.remove(v.size());
//        oom();
//        cpoom();
        long start = System.currentTimeMillis();
//        maxSub(a);

//        insertionSort(a);
//        selectionSort(b);
//        bubbleSort(arr);

//        // Quick Sort
//        QuickSort.quickSort(arr);
//
//        // Shell Sort
//        ShellSort.shellSort(c);
//
//        // Merge Sort
//        MergeSort.mergeSort(b);
//
//        // Heap Sort
//        HeapSort.heapSort(a);

        long end = System.currentTimeMillis() - start;
        System.out.println("Time used: " + end);
    }

    public static void cpoom() {

        String str1 = new StringBuffer("123").append("456").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuffer("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }

    public static void oom() {
        List<Test> list = new ArrayList<Test>();
        while (true)
            list.add(new Test());
    }

    private static void maxSub(int[] a) {
        int maxSum = 0, thisSum = 0;

        for (int j = 0; j < a.length; j++) {
            thisSum += a[j];

            if (thisSum > maxSum)
                maxSum = thisSum;
            else if (thisSum < 0)
                thisSum = 0;
        }

        System.out.println(maxSum);
    }

    private static void insertionSort(int[] a) {
        int j;
        for (int i = 1; i < a.length; i++) {
            int temp = a[i];
            for (j = i; j > 0 && temp < a[j - 1]; j--)
                a[j] = a[j - 1];

            a[j] = temp;
        }
        System.out.print("Insertion Sort: ");
        System.out.println(Arrays.toString(a));
    }

    private static void selectionSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            if (i != min) {
                int temp = a[i];
                a[i] = a[min];
                a[min] = temp;
            }
        }

        System.out.print("Selection Sort: ");
        System.out.println(Arrays.toString(a));
    }

    private static void bubbleSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            boolean is = true;
            for (int j = 0; j < a.length - i; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    is = false;
                }
            }
            if (is)
                break;
        }

        System.out.print("Bubble Sort:    ");
        System.out.println(Arrays.toString(a));
    }

    private static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
//        anotherQuickSort(array, 0, array.length - 1);
        System.out.println("Quick sort: " + Arrays.toString(array));
    }

    public static void quickSort(int[] array, int left, int right) {
        int i = left;
        int j = right - 1;
        int pivot = median3(array, left, right);

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
        swap(array, i, right - 1);
        quickSort(array, left, i - 1);
        quickSort(array, i + 1, right);
    }

    public static int median3(int[] array, int left, int right) {
        int center = (left + right) / 2;
        if (array[center] < array[left])
            swap(array, left, center);

        if (array[right] < array[left])
            swap(array, left, right);

        if (array[right] < array[center])
            swap(array, left, right);

        swap(array, center, right - 1);

        return array[right - 1];
    }

    private static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    private static void anotherQuickSort(int[] array, int left, int right) {
        if (left >= right)
            return;

        int pivot = partition(array, left, right);
        anotherQuickSort(array, left, pivot - 1);
        anotherQuickSort(array, pivot + 1, right);
    }

    private static int partition(int[] array, int left, int right) {
        int key = array[left];
        while (left < right) {
            while (array[right] >= key && right > left)
                right--;

            array[left] = array[right];

            while (array[left] <= key && right > left)
                left++;

            array[right] = array[left];
        }
        array[right] = key;

        return right;
    }

    public static void shellSort(int[] a) {
        int x;
        for (int i = a.length; i > 0; i /= 2) {
            for (int j = i; j < a.length; j++) {
                int temp = a[j];
                for (x = j; x >= i && temp < a[x - i]; x -= i)
                    a[x] = a[x - i];

                a[x] = temp;
            }
        }
    }

    public static void pickup() {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;

        System.out.println(c == d);
        System.out.println(e == f);
        System.out.println(c == (a + b));
        System.out.println(c.equals(a + b));
        System.out.println(g == (a + b));
        System.out.println(g.equals(a + b));
    }
}
