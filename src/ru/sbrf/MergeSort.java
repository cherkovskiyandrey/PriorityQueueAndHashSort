package ru.sbrf;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {

        int[] array = new int[]{5, 8, 4, 9, 3, 12, 58, 48, 78, 3, 2, 0};
        System.out.println(Arrays.toString(array));
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    //time - O(n*lg(n)) |memory - O(n)
    public static void sort(int[] array) {
        int[] tmpArray = new int[array.length];
        int[] from = array;
        int[] to = tmpArray;

        for (int i = 0; Math.pow(2, i) < from.length; i++) {
            int chunkSize = (int) Math.pow(2, i);
            int chunks = ((from.length / chunkSize + (from.length % chunkSize > 0 ? 1 : 0)) / 2) * 2;

            int j = 0;
            for (; j < chunks; j += 2) {
                int leftBegin = j * chunkSize;
                int leftSize = chunkSize;

                int rightBegin = leftBegin + leftSize;
                int rightSize = (rightBegin + chunkSize) > from.length ? from.length - rightBegin : chunkSize;

                merge(leftBegin, leftSize, rightBegin, rightSize, leftBegin, from, to);
            }
            int lastBegin = j * chunkSize;
            if (lastBegin < from.length) {
                System.arraycopy(from, lastBegin, to, lastBegin, from.length - lastBegin);
            }

            int[] swapTmp = from;
            from = to;
            to = swapTmp;
        }
        if (from != array) {
            System.arraycopy(from, 0, array, 0, to.length);
        }
    }

    private static void merge(int leftBegin, int leftSize, int rightBegin, int rightSize, int toBegin, int[] from, int[] to) {
        int i = 0;
        int j = 0;
        for (; i < leftSize && j < rightSize; ) {
            if (from[leftBegin + i] < from[rightBegin + j]) {
                to[toBegin++] = from[leftBegin + i];
                i++;
            } else {
                to[toBegin++] = from[rightBegin + j];
                j++;
            }
        }
        for (; i < leftSize; i++) {
            to[toBegin++] = from[leftBegin + i];
        }
        for (; j < rightSize; j++) {
            to[toBegin++] = from[rightBegin + j];
        }
    }
}























