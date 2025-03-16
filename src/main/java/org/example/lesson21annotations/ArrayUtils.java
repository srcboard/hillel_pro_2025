package org.example.lesson21annotations;

public class ArrayUtils {

    @MethodInfo(name = "mergeSort", returnType = "void", description = "Merge sort")
    @Author(name = "Author")
    public static void mergeSort(int[] array) {
        org.example.lesson19algorithms.ArrayUtils.mergeSort(array);
    }

    @MethodInfo(name = "binarySearch", returnType = "int", description = "Binary search")
    @Author(name = "Author2")
    public static int binarySearch(int[] array, int target) {
        return org.example.lesson19algorithms.ArrayUtils.binarySearch(array, target);
    }

}
