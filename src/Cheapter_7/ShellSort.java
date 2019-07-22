package Cheapter_7;

import java.util.Arrays;

public class ShellSort {
    public static void shellSort(int[] array) {
        int x;
        for (int i = array.length; i > 0; i /= 2) {
            for (int j = i; j < array.length; j++) {
                int temp = array[j];
                for (x = j; x >= i && temp < array[x - i]; x -= i)
                    array[x] = array[x - i];

                array[x] = temp;
            }
        }
        System.out.println("Shell Sort: " + Arrays.toString(array));
    }
}
