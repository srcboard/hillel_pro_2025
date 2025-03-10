package org.example.lesson19algorithms;

public class ArrayUtils {

    public static void mergeSort(int[] array) {

        if (array.length < 2) {
            return;
        }

        int middleIndex = array.length / 2;

        int[] left = new int[middleIndex];
        for (int i = 0; i < middleIndex; i++){
            left[i] = array[i];
        }

        int[] right = new int[array.length - middleIndex];
        for (int j = middleIndex; j < array.length; j++){
            right[j - middleIndex] = array[j];
        }

        mergeSort(left);
        mergeSort(right);
        merge(array, left, right);

    }

    private static void merge(int[] array, int[] left, int[] right) {

        int leftIndex = 0;
        int rightIndex = 0;
        int arrayIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] <= right[rightIndex]) {
                array[arrayIndex] = left[leftIndex];
                leftIndex++;
            } else {
                array[arrayIndex] = right[rightIndex];
                rightIndex++;
            }
            arrayIndex++;
        }

        for (int i = leftIndex; i < left.length; i++) {
            array[arrayIndex] = left[leftIndex];
            arrayIndex++;
            leftIndex++;
        }

        for (int j = rightIndex; j < right.length; j++) {
            array[arrayIndex] = right[rightIndex];
            arrayIndex++;
            rightIndex++;
        }

    }

    public static int binarySearch(int[] array, int target) {

        int leftIndex = 0;
        int rightIndex = array.length - 1;

        while (leftIndex <= rightIndex) {
            int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;
            if (array[middleIndex] == target) {
                return middleIndex;
            } else if (array[middleIndex] < target) {
                leftIndex = middleIndex + 1;
            } else {
                rightIndex = middleIndex - 1;
            }
        }

        return -1;

    }

}
