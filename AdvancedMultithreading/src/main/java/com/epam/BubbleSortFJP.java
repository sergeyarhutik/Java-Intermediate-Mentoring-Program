package com.epam;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class BubbleSortFJP {
    public static void main(String[] args) {
        final int NUM = 25_000_000;

        int[] bigArrayOne = new int[NUM];
        int[] bigArrayTwo = new int[NUM];
        Random random = new Random();

        for(int i = 0; i < bigArrayOne.length - 1; i++) {
            bigArrayOne[i] = random.nextInt(bigArrayOne.length*10);
        }
        for(int i = 0; i < bigArrayTwo.length - 1; i++) {
            bigArrayTwo[i] = random.nextInt(bigArrayTwo.length*10);
        }

        long startTime = System.currentTimeMillis();
        QsortSimple(bigArrayOne, 0, bigArrayOne.length - 1);
        long endTime = System.currentTimeMillis();
        System.out.println("\nTotal sorting time QsortSimple: " + (endTime - startTime) +"ms");
        System.out.println("QsortSimple result = " + Arrays.toString(bigArrayOne));

        ForkJoinPool fjp = new ForkJoinPool();
        startTime = System.currentTimeMillis();
        fjp.invoke(new QSortFJP(bigArrayTwo));
        endTime = System.currentTimeMillis();
        System.out.println("\nTotal sorting time QsortFJP: " + (endTime - startTime) +"ms");
        System.out.println("QSortFJP result = " + Arrays.toString(bigArrayTwo));

    }

    public static void QsortSimple(int[] array, int begin, int end) {
        if(begin >= end)
            return;
        int pivot = array[begin];
        int i = begin+1;
        int tmp;

        for(int j = begin+1; j<= end; j++){
            if(pivot > array[j]){
                tmp = array[j];
                array[j] = array[i];
                array[i] = tmp;
                i++;
            }
        }

        array[begin] = array[i-1];
        array[i-1] = pivot;

        QsortSimple(array, begin, i-2);
        QsortSimple(array, i, end);
    }
}

class QSortFJP extends RecursiveAction {
    int[] array;
    int begin;
    int end;

    public QSortFJP(int[] array) {
        this.array = array;
        this.begin = 0;
        this.end = array.length - 1;
    }

    public QSortFJP(int[] array, int begin, int end) {
        this.array = array;
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected void compute() {
        if(begin >= end)
            return;
        int pivot = array[begin];
        int i = begin+1;
        int tmp;

        for(int j = begin+1; j<= end; j++){
            if(pivot > array[j]){
                tmp = array[j];
                array[j] = array[i];
                array[i] = tmp;
                i++;
            }
        }

        array[begin] = array[i-1];
        array[i-1] = pivot;

        QSortFJP task1 = new QSortFJP(array, begin, i-2);
        QSortFJP task2 = new QSortFJP(array, i, end);
        invokeAll(task1, task2);
    }
}