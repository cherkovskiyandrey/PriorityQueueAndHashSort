package ru.sbrf;

import java.util.Arrays;

import static java.lang.String.format;

public class SimplePriorityQueue<T extends Comparable<? super T>> {
    private final Object[] array;
    private int curLength;

    public SimplePriorityQueue(int length) {
        this.array = new Object[length];
    }

    public void push(T object) {
        if(object == null) {
            throw new NullPointerException();
        }
        if(curLength == array.length) {
            throw new IndexOutOfBoundsException(format("capacity has been reached: %d", curLength));
        }
        array[curLength] = object;
        swim(curLength);
        curLength++;
    }



    public T pop() {
        if(curLength == 0) {
            return null;
        }
        swap(0, --curLength);
        sink(0);
        return getElementBy(curLength);
    }

    private void swim(int pos) {
        while(pos != 0) {
            int parentPos = getParentPos(pos);
            if (!isLessObject(parentPos, pos)) { // obj[parentPos] < obj[pos]
                break;
            }
            swap(parentPos, pos);
            pos = parentPos;
        }
    }

    private boolean isLessObject(int parentPos, int pos) {
        return getElementBy(parentPos).compareTo(getElementBy(pos)) < 0;
    }

    @SuppressWarnings("unchecked")
    private T getElementBy(int pos) {
        return (T)array[pos];
    }

    private void sink(int pos) {
        while(pos < curLength/2) {
            int left = getLeft(pos);
            int right = getRight(pos);
            int maxChild = right == -1 ? left : (isLessObject(left, right) ? right : left);
            if (!isLessObject(pos, maxChild)) { // obj[pos] < obj[maxChild]
                break;
            }
            swap(pos, maxChild);
            pos = maxChild;
        }
    }

    private int getRight(int pos) {
        final int r = pos*2 + 2;
        return r < curLength ? r : -1;
    }

    private int getParentPos(int pos) {
        return pos % 2 == 1 ? pos / 2 : pos / 2 - 1;
    }

    private int getLeft(int pos) {
        return pos*2 + 1;
    }

    private void swap(int posA, int posB) {
        final Object a = array[posA];
        array[posA] = array[posB];
        array[posB] = a;
    }

    @Override
    public String toString() {
        return "SimplePriorityQueue{" +
                "array=" + Arrays.toString(array) +
                ", curLength=" + curLength +
                '}';
    }
}
