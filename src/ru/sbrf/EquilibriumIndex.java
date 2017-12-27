package ru.sbrf;

import java.util.Arrays;

public class EquilibriumIndex {


    public static void main(String[] args) {

        int[][] a = new int[][]{
                {-1, 3, -4, 5, 1, -6, 2, 1},
                {},
                {Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, -200000}
        };

        for (int[] array : a) {
            int[] equilibriumIdxs = equilibriumIndex(array);
            System.out.println(Arrays.toString(equilibriumIdxs));
        }
    }


    //0 ≤ P < N
    public static int[] equilibriumIndex(int[] array) {
        if (array.length > 100_000) {
            throw new IllegalArgumentException("length: " + array.length);
        }

        int[] tmpResult = new int[array.length];
        long right = sum(array);
        long left = 0;
        int idx = 0;
        for (int i = 0; i < array.length; i++) {
            right -= array[i];
            left += i - 1 >= 0 ? array[i - 1] : 0;
            if (left == right) {
                tmpResult[idx++] = i;
            }
        }
        int[] result = new int[idx];
        copy(tmpResult, result, idx);

        return result;
    }

    private static void copy(int[] from, int[] to, int size) {
        for (int i = 0; i < size; i++) {
            to[i] = from[i];
        }
    }

    private static long sum(int[] array) {
        long result = 0;
        for (int element : array) {
            result += element;
        }
        return result;
    }
}
