package ru.sbrf;

import java.util.Arrays;

public class PyramidalSort {

    public static void sort(int[] array) {
        int length = array.length;
        for (int i = (length / 2) - 1; i >= 0; --i) {
            sink(array, i, length);
        }
        while (length > 0) {
            swap(array, 0, --length);
            sink(array, 0, length);
        }
    }

    private static void sink(int[] array, int index, int length) {
        while (index < length / 2) {
            int maxChildIndex = (index * 2 + 2 >= length || array[index * 2 + 1] > array[index * 2 + 2]) ? index * 2 + 1 : index * 2 + 2;
            if (array[index] >= array[maxChildIndex]) {
                break;
            }
            swap(array, index, maxChildIndex);
            index = maxChildIndex;
        }
    }


    private static void swap(int[] array, int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    public static void main(String[] args) {
        int[] array = PyramidalSortTest.generateRandom(0, 1000);
        System.out.println(Arrays.toString(array));
        sort(array);
        if(!PyramidalSortTest.isSorted(array)) {
            throw new RuntimeException("Unsorted!");
        }
        System.out.println(Arrays.toString(array));

    }
}
