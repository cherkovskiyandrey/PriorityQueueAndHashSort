package ru.sbrf;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Дом on 13.01.2018.
 */
public class Solution {


    public static void main(String[] args) {
        System.out.println(solution(new int[]{60, 80, 40}, new int[]{2, 3, 5}, 5, 2, 200));

        System.out.println(solution(new int[]{40, 40, 100, 80, 20}, new int[]{3, 3, 2, 2, 3}, 3, 5, 200));
    }

    public static int solution(int[] A, int[] B, int M, int X, int Y) {


        int result = 0;
        Set<Integer> floors = new HashSet<>(X);
        long currentWeight = 0;
        int lastB = 0;
        for (int i = 0; i < A.length; i++) {
            currentWeight += A[i];
            boolean lastCycle = i == (A.length - 1);
            long nextWeight = (lastCycle ? currentWeight : currentWeight + A[i + 1]);

            if ((!lastCycle && (nextWeight > Y || (i - lastB + 1) == X)) ||
                    (lastCycle && (currentWeight <= Y && ((i - lastB) <= X)))) {
                currentWeight = 0;
                floors.clear();
                for (int j = lastB; j <= i; j++) {
                    if (floors.add(B[j])) {
                        result++;
                    }
                }
                lastB = i + 1;
                result++;
            }
        }

        return result;
    }

}
