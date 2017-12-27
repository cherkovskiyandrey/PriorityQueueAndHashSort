package ru.sbrf;

import java.util.Random;

public class SimplePriorityQueueTest {

    public static void main(String[] args) {

        final SimplePriorityQueue<Integer> priorityQueue = new SimplePriorityQueue<Integer>(10);

        Random r = new Random();
        for(int i = 0; i < 10; ++i) {
            priorityQueue.push(r.nextInt(100));
        }
        System.out.println(priorityQueue);
        //priorityQueue.push(100);
        System.out.println("--------------------------");

        for (Integer k = priorityQueue.pop(); k != null; k = priorityQueue.pop()) {
            System.out.println(k);
            System.out.println(priorityQueue);
            System.out.println("=====");
        }
    }
}
