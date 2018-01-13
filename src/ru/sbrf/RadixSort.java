package ru.sbrf;

import java.util.Arrays;

public class RadixSort {

    public static void main(String[] args) {
        int[] test = new int[]{1, 3, 6, 4, 1, 2};

        System.out.println("After =>> " + Arrays.toString(test));

        System.out.println(solution(test));

        System.out.println("After =>> " + Arrays.toString(test));

    }

    public static int solution(int[] A) {
        // write your code in Java SE 8
        A = removeNegatives(A);
        sort(A);
        int min = A.length > 0 ? A[0] : 0;
        for (int i = 1; i < A.length; i++) {
            int supposition = min + 1;
            if(supposition > 0 && supposition < A[i]) {
                break;
            }
            min = A[i];
        }
        return min + 1;
    }

    private static int[] removeNegatives(int[] test) {
        int[] positivesOnly = new int[test.length];
        int j = 0;
        for (int i = 0; i < test.length; i++) {
            if (test[i] > 0) {
                positivesOnly[j++] = test[i];
            }
        }
        int[] result = new int[j];
        copy(positivesOnly, result, j);
        return result;
    }

    public static void sort(int[] array) {
        int maxVal = max(array);

        for (int i = 0; (int) Math.pow(10, i) <= maxVal; i++) {
            bucketsToList(listToBuckets(array, i), array);
        }
    }

    //time - O(n) |memory - O(1)
    private static void bucketsToList(int[][] buckets, int[] array) {
        for (int i = 0, j = 0; i < buckets.length; i++) {
            for (int k = 0; k < buckets[i].length; k++) {
                array[j++] = buckets[i][k];
            }
        }
    }

    //time - O(n) |memory - O(n*m) (!)
    private static int[][] listToBuckets(int[] tmpArray, int radix) {
        int[][] result = new int[10][tmpArray.length];
        int[] arraysSizes = new int[10];
        for (int i = 0; i < tmpArray.length; i++) {
            int index = (tmpArray[i] / (int) Math.pow(10, radix)) % 10;
            result[index][arraysSizes[index]++] = tmpArray[i];
        }
        return shrinkArrays(result, arraysSizes);
    }

    //time - O(n) |memory - O(n)
    private static int[][] shrinkArrays(int[][] tmpResult, int[] arraysSizes) {
        int[][] result = new int[10][];
        for (int i = 0; i < arraysSizes.length; i++) {
            int[] cell = new int[arraysSizes[i]];
            copy(tmpResult[i], cell, cell.length);
            result[i] = cell;
        }

        return result;
    }

    //time - O(n) |memory - O(1)
    private static void copy(int[] from, int[] to, int length) {
        for (int i = 0; i < length; i++) {
            to[i] = from[i];
        }
    }

    //time - O(n) |memory - O(1)
    private static int max(int[] array) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
//    //Returns the smallest positive integer (greater than 0) that does not occur in A
//    public static void main(String[] args) {
//        int[] test = new int[]{1, 3, 6, 4, 1, 2};
//
//        System.out.println("After =>> " + Arrays.toString(test));
//
//        test = removeNegativesAnd0(test);
//
//        System.out.println("After =>> " + Arrays.toString(test));
//
//        sort(test);
//
//        System.out.println("After =>> " + Arrays.toString(test));
//
//        if (test.length == 0) {
//            System.out.println(1);
//            return;
//        }
//
//        int min = test.length > 0 ? test[0] : 1;
//        for (int i = 1; i < test.length; i++) {
//            int supposition = min + 1;
//            if(supposition > 0 && supposition < test[i]) {
//                break;
//            }
//            min = test[i];
//        }
//        if(min < 0) {
//            System.out.println(1);
//        } else {
//            System.out.println(min + 1);
//        }
//    }
//
//    private static int[] removeNegativesAnd0(int[] test) {
//        int[] positivesOnly = new int[test.length];
//        int j = 0;
//        for (int i = 0; i < test.length; i++) {
//            if (test[i] > 0) {
//                positivesOnly[j++] = test[i];
//            }
//        }
//        int[] result = new int[j];
//        copy(positivesOnly, result, j);
//        return result;
//    }
//
//    public static void sort(int[] array) {
//        int maxVal = max(array);
//
//        for (int i = 0; (int) Math.pow(10, i) <= maxVal; i++) {
//            bucketsToList(listToBuckets(array, i), array);
//        }
//    }
//
//    //time - O(n) |memory - O(1)
//    private static void bucketsToList(int[][] buckets, int[] array) {
//        for (int i = 0, j = 0; i < buckets.length; i++) {
//            for (int k = 0; k < buckets[i].length; k++) {
//                array[j++] = buckets[i][k];
//            }
//        }
//    }
//
//    //time - O(n) |memory - O(n*m) (!)
//    private static int[][] listToBuckets(int[] tmpArray, int radix) {
//        int[][] result = new int[10][tmpArray.length];
//        int[] arraysSizes = new int[10];
//        for (int i = 0; i < tmpArray.length; i++) {
//            int index = (tmpArray[i] / (int) Math.pow(10, radix)) % 10;
//            result[index][arraysSizes[index]++] = tmpArray[i];
//        }
//        return shrinkArrays(result, arraysSizes);
//    }
//
//    //time - O(n) |memory - O(n)
//    private static int[][] shrinkArrays(int[][] tmpResult, int[] arraysSizes) {
//        int[][] result = new int[10][];
//        for (int i = 0; i < arraysSizes.length; i++) {
//            int[] cell = new int[arraysSizes[i]];
//            copy(tmpResult[i], cell, cell.length);
//            result[i] = cell;
//        }
//
//        return result;
//    }
//
//    //time - O(n) |memory - O(1)
//    private static void copy(int[] from, int[] to, int length) {
//        for (int i = 0; i < length; i++) {
//            to[i] = from[i];
//        }
//    }
//
//    //time - O(n) |memory - O(1)
//    private static int max(int[] array) {
//        int max = Integer.MIN_VALUE;
//        for (int i = 0; i < array.length; i++) {
//            if (array[i] > max) {
//                max = array[i];
//            }
//        }
//        return max;
//    }

}













