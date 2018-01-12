package ru.sbrf;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class MergeSortTest {
    public static void main(String[] args) {

        sortAndPrint(new int[]{2, 1});

        sortAndPrint(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        sortAndPrint(new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1});
        sortAndPrint(new int[]{2, 6, 6, 9, 5, 7, 4, 0, 9, 0});

        final List<Duration> myMergeResult = benchmarkMyMerge(10);
//        final List<Duration> mySortResult = benchmarkMySort(10);
//        final List<Duration> sortFromBookResult = benchmarkSortFromBook(10);
        final List<Duration> nativeSortResult = benchmarkSort(10);

        printResult(myMergeResult);
//        printResult(mySortResult);
//        printResult(sortFromBookResult);
        printResult(nativeSortResult);
    }

    private static List<Duration> benchmarkMyMerge(int amount) {
        List<Duration> result = new ArrayList<>();
        for (int j = 0; j < 10; ++j) {
            int[][] genArrays = new int[100][100000];
            IntStream.range(0, 100).forEach(i -> genArrays[i] = generateRandom(0, 100000));
            System.out.println("========= Data is ready for sort ===================");
            long begin = System.nanoTime();
            IntStream.range(0, 100).forEach(i -> MergeSort.sort(genArrays[i]));
            final Duration end = Duration.ofNanos(System.nanoTime() - begin);
            result.add(end);

            IntStream.range(0, 100).forEach(i -> checkSorted(genArrays[i]));

            System.out.println("========= Data is sorted [" + end + "]");
        }
        return result;
    }

    private static void sortAndPrint(int[] arr) {
        System.out.println("Before =>> " + Arrays.toString(arr));
        MergeSort.sort(arr);
        System.out.println("After =>> " + Arrays.toString(arr));
    }


    private static void printResult(List<Duration> mySortResult) {
        final LongSummaryStatistics stat = mySortResult.stream()
                .mapToLong(Duration::toNanos)
                .summaryStatistics();
        System.out.printf("Statistics: count=%s, sum=%s, min=%s, average=%s, max=%s%s",
                stat.getCount(),
                Duration.ofNanos(stat.getSum()),
                Duration.ofNanos(stat.getMin()),
                Duration.ofNanos((long) stat.getAverage()),
                Duration.ofNanos(stat.getMax()),
                System.lineSeparator()
        );
    }

    private static List<Duration> benchmarkSort(int amount) {
        List<Duration> result = new ArrayList<>();
        for (int j = 0; j < 10; ++j) {
            int[][] genArrays = new int[100][100000];
            IntStream.range(0, 100).forEach(i -> genArrays[i] = generateRandom(0, 100000));
            System.out.println("========= Data is ready for sort ===================");
            long begin = System.nanoTime();
            IntStream.range(0, 100).forEach(i -> Arrays.sort(genArrays[i]));
            final Duration end = Duration.ofNanos(System.nanoTime() - begin);
            result.add(end);

            IntStream.range(0, 100).forEach(i -> checkSorted(genArrays[i]));

            System.out.println("========= Data is sorted [" + end + "]");
        }
        return result;
    }

    public static int[] generateRandom(int from, int to) {
        List<Integer> result = new ArrayList<>();
        for (int i = from; i < to; i++) {
            result.add(from + ThreadLocalRandom.current().nextInt(to - from));
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static boolean isSorted(int[] array) {
        int min = Integer.MIN_VALUE;
        for (int i : array) {
            if (min > i) {
                return false;
            }
            min = i;
        }
        return true;
    }

    private static void checkSorted(int[] array) {
        if (!PyramidalSortTest.isSorted(array)) {
            throw new RuntimeException("Unsorted!");
        }
    }
}
