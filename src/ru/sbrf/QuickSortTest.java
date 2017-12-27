package ru.sbrf;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QuickSortTest {


    public static void main(String[] args) {


        sortAndPrint2(new Integer[]{2, 1});

        sortAndPrint2(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        sortAndPrint2(new Integer[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1});
        sortAndPrint2(new Integer[]{2, 6, 6, 9, 5, 7, 4, 0, 9, 0});

        //IntStream.range(0, 100000).forEach(i -> sortAndPrint(generateRandom(0, 10000)));

//        final List<Duration> mySortResult = benchmarkSort(10, QuickSort::sort);
//        final List<Duration> nativeSortResult = benchmarkSort(10, Arrays::<Integer>sort);
//
//        printResult(mySortResult);
//        printResult(nativeSortResult);
    }

    private static void printResult(List<Duration> mySortResult) {
        final LongSummaryStatistics stat = mySortResult.stream()
                .mapToLong(Duration::toNanos)
                .summaryStatistics();
        System.out.println(stat.toString());
    }

    private static List<Duration> benchmarkSort(int amount, Consumer<Integer[]> consumer) {
        List<Duration> result = new ArrayList<>();
        for(int j = 0; j < 10; ++j)  {
            Integer[][] genArrays = new Integer[100][100000];
            IntStream.range(0, 100).forEach(i -> genArrays[i] = generateRandom(0, 100000));
            System.out.println("========= Data is ready for sort ===================");
            long begin = System.nanoTime();
            IntStream.range(0, 100).forEach(i -> consumer.accept(genArrays[i]));
            final Duration end = Duration.ofNanos(System.nanoTime() - begin);
            result.add(end);
            System.out.println("========= Data is sorted [" + end + "]");
        }
        return result;
    }

    private static Integer[] generateRandom(int from, int to) {
        List<Integer> result = new ArrayList<>();
        for(int i = from; i < to; i++) {
            result.add(from + ThreadLocalRandom.current().nextInt(to - from));
        }
        return result.toArray(new Integer[result.size()]);
    }

    private static void sortAndPrint(Integer[] arr) {
        System.out.println("Before =>> " + Arrays.toString(arr));
        QuickSort.sort(arr);
        System.out.println("After =>> " + Arrays.toString(arr));
    }

    private static void sortAndPrint2(Integer[] arr) {
        System.out.println("Before =>> " + Arrays.toString(arr));
        QuickSort2.sort(arr);
        System.out.println("After =>> " + Arrays.toString(arr));
    }
}
