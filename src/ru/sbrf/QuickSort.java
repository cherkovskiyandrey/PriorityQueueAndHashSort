package ru.sbrf;

public class QuickSort {

    /**
     * Implementation by means of program stack.
     *
     * @param array
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void sort(T[] array) {
        sortHelper(array, 0, array.length);
    }

    private static <T extends Comparable<? super T>> void sortHelper(final T[] array, final int begin, final int size) {
        if (size < 2) {
            return;
        }
        int end = begin + size - 1;
        if (size == 2) {
            if (!isLess(array, begin, end)) {
                swap(array, begin, end);
            }
            return;
        }
        int left = begin;
        int right = end - 1;
        int median = begin + (end - begin) / 2;
        swap(array, median, end);
        median = end;
        while (left < right) {
            final boolean isLeftLessMedian = isLess(array, left, median);
            final boolean isRightGreatMedian = !isLess(array, right, median);
            if (!isLeftLessMedian && !isRightGreatMedian) {
                swap(array, left, right);
                left++;
                right--;
            } else {
                if (isLeftLessMedian) left++;
                if (isRightGreatMedian) right--;
            }
        }
        if(!isLess(array, right, median)) {
            swap(array, right, median);
            median = right;
        } else {
            swap(array, right + 1, median);
            median = right + 1;
        }

        sortHelper(array, begin, median - begin);
        sortHelper(array, median + 1, end - median);
    }

    @SuppressWarnings("unchecked")
    private static boolean isLess(Comparable[] array, int left, int right) {
        return array[left].compareTo(array[right]) < 0;
    }

    private static void swap(Object[] array, int posA, int posB) {
        final Object a = array[posA];
        array[posA] = array[posB];
        array[posB] = a;
    }

    //--------------- For int ------------------------
    public static void sort(int[] array) {
        sortHelper(array, 0, array.length);
    }

    private static void sortHelper(final int[] array, final int begin, final int size) {
        if (size < 2) {
            return;
        }
        int end = begin + size - 1;
        if (size == 2) {
            if (!isLess(array, begin, end)) {
                swap(array, begin, end);
            }
            return;
        }
        int left = begin;
        int right = end - 1;
        int median = begin + (end - begin) / 2;
        swap(array, median, end);
        median = end;
        while (left < right) {
            final boolean isLeftLessMedian = isLess(array, left, median);
            final boolean isRightGreatMedian = !isLess(array, right, median);
            if (!isLeftLessMedian && !isRightGreatMedian) {
                swap(array, left, right);
                left++;
                right--;
            } else {
                if (isLeftLessMedian) left++;
                if (isRightGreatMedian) right--;
            }
        }
        if(!isLess(array, right, median)) {
            swap(array, right, median);
            median = right;
        } else {
            swap(array, right + 1, median);
            median = right + 1;
        }

        sortHelper(array, begin, median - begin);
        sortHelper(array, median + 1, end - median);
    }

    @SuppressWarnings("unchecked")
    private static boolean isLess(int[] array, int left, int right) {
        return array[left] < array[right];
    }

    private static void swap(int[] array, int posA, int posB) {
        final int a = array[posA];
        array[posA] = array[posB];
        array[posB] = a;
    }
}
