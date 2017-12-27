package ru.sbrf;

public class QuickSort2 {

    public static <T extends Comparable<? super T>> void sort(T[] array) {
        sortHelper(array, 0, array.length - 1);
    }

    private static <T extends Comparable<? super T>> void sortHelper(final T[] array, final int from, final int to) {
        if (from >= to) {
            return;
        }
        setMedian(array, from, to);
        final int median = partition(array, from, to);
        sortHelper(array, from, median - 1);
        sortHelper(array, median + 1, to);
    }

    private static <T extends Comparable<? super T>> int partition(final T[] array, int from, int to) {
        final int median = from;
        int j = to + 1;
        while (true) {
            while (isLess(array, ++from, median)) if (from == to) break;
            while (isLess(array, median, --j)) if (j == median) break;
            if (from >= j) break;
            swap(array, from, j);
        }
        swap(array, median, j);
        return j;
    }

    @SuppressWarnings("unchecked")
    private static boolean isLess(Comparable[] array, int left, int right) {
        return array[left].compareTo(array[right]) < 0;
    }

    private static void setMedian(Object[] array, int from, int to) {
        swap(array, from, from + (to - from) / 2);
    }

    private static void swap(Object[] array, int posA, int posB) {
        final Object a = array[posA];
        array[posA] = array[posB];
        array[posB] = a;
    }

    //----------- Для int ----------------------

    public static void sort(int[] array) {
        sortHelper(array, 0, array.length - 1);
    }

    private static void sortHelper(final int[] array, final int from, final int to) {
        if (from >= to) {
            return;
        }
        setMedian(array, from, to);
        final int median = partition(array, from, to);
        sortHelper(array, from, median - 1);
        sortHelper(array, median + 1, to);
    }

    private static int partition(final int[] array, int from, int to) {
        final int median = from;
        int j = to + 1;
        while (true) {
            while (isLess(array, ++from, median)) if (from == to) break;
            while (isLess(array, median, --j)) if (j == median) break;
            if (from >= j) break;
            swap(array, from, j);
        }
        swap(array, median, j);
        return j;
    }

    @SuppressWarnings("unchecked")
    private static boolean isLess(int[] array, int left, int right) {
        return array[left] < array[right];
    }

    private static void setMedian(int[] array, int from, int to) {
        swap(array, from, from + (to - from) / 2);
    }

    private static void swap(int[] array, int posA, int posB) {
        final int a = array[posA];
        array[posA] = array[posB];
        array[posB] = a;
//        if(posA == posB) return;
//        array[posA] = array[posA] + array[posB];
//        array[posB] = array[posA] - array[posB];
//        array[posA] = array[posA] - array[posB];
    }
}
