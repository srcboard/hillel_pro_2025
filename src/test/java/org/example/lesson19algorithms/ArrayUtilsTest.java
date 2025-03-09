package org.example.lesson19algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class ArrayUtilsTest {

    @Test
    void testMergeSort() {

        Random rand = new Random();

        int[] array = IntStream.generate(() -> rand.nextInt(100)).limit(10).toArray();
        int[] expected = array.clone();

        Arrays.sort(expected);
        ArrayUtils.mergeSort(array);

        Assertions.assertArrayEquals(expected, array);

    }

    @Test
    void testBinarySearch() {

        Random rand = new Random();

        int[] array = IntStream.generate(() -> rand.nextInt(100)).limit(10).toArray();
        Arrays.sort(array);

        int target = array[rand.nextInt(array.length)];

        int expected = Arrays.binarySearch(array, target);
        int index = ArrayUtils.binarySearch(array, target);

        Assertions.assertEquals(expected, index);

    }

}
