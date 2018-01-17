package ru.sbrf;

/**
 * Created by Дом on 13.01.2018.
 */
public class Solution2 {

    public static void main(String[] args) {
        System.out.println(solution2(new int[]{0, 7, 11, 13, 14, 15, 16, 20, 21, 22, 23}));
        System.out.println(solution2(new int[0]));
    }

    public static int solution(int[] A) {
        long sum = 0;
        for (int aA : A) {
            sum += Math.pow(2, aA);
        }
        sum *= 3;
        int result = 0;
        for (int i = 0; i < 64; i++) {
            if (((sum >>>= 1) & 1) == 1) {
                result++;
            }
        }
        return result;
    }

    //этот отсортированный массив по сути порядковые номера единиц числа K в двоичной системе
    //соответсвенно длина массива и есть кол-во единиц в числе K
    //3*K - это 2*K + K
    // получаем 2*K - просто перенос на на один порядок
    public static int solution2(int[] A) {
        int result = 0;
        boolean transfer = false;
        int lastVal = 0;
        for (int i = 0, j = 0; i < A.length; ) {

            if (A[i] == (A[j] + 1)) {
                if(lastVal < (A[i] - 1) && transfer) {
                    result++;
                    transfer = false;
                }
                if (transfer) {
                    result++;
                }
                transfer = true;
                lastVal = A[i];
                ++j;
                ++i;
            } else if (A[i] < (A[j] + 1)) {
                if(lastVal < (A[i] - 1) && transfer) {
                    result++;
                    transfer = false;
                }
                if (transfer) {
                    transfer = true;
                } else {
                    result++;
                }
                lastVal = A[i];
                ++i;
            } else {
                if(lastVal < A[j] && transfer) {
                    result++;
                    transfer = false;
                }
                if (transfer) {
                    transfer = true;
                } else {
                    result++;
                }
                lastVal = A[j] + 1;
                ++j;
            }
        }
        return A.length == 0 ? 0 : result + 1;
    }

}
