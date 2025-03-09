package org.example.lesson19algorithms;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        Random rand = new Random();

        int[] array = IntStream.generate(() -> rand.nextInt(100)).limit(10).toArray();
        System.out.println("Initial array: " + Arrays.toString(array));
        ArrayUtils.mergeSort(array);
        System.out.println("Sorted array: " + Arrays.toString(array));

        int target = array[rand.nextInt(array.length)];
        int index = ArrayUtils.binarySearch(array, target);
        System.out.println("Found number " + target + " at position " + index);

    }
}
