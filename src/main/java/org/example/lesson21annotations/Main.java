package org.example.lesson21annotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        Class<ArrayUtils> ArrayUtilsClass = ArrayUtils.class;

        Random rand = new Random();

        try{
            Method methodMergeSort = ArrayUtilsClass.getDeclaredMethod("mergeSort", int[].class);
            System.out.println("Method in ArrayUtils class: " + methodMergeSort.getName());
            printAnnotationInfo(methodMergeSort);

            int[] array = IntStream.generate(() -> rand.nextInt(100)).limit(10).toArray();
            System.out.println("Initial array: " + Arrays.toString(array));
            methodMergeSort.invoke(null, array);
            System.out.println("Sorted array: " + Arrays.toString(array));

            System.out.println("-------------------------");

            Method methodBinarySearch = ArrayUtilsClass.getDeclaredMethod(
                    "binarySearch", int[].class, int.class);
            System.out.println("Method in ArrayUtils class: " + methodBinarySearch.getName());
            printAnnotationInfo(methodBinarySearch);

            int target = array[rand.nextInt(array.length)];
            int index = (int) methodBinarySearch.invoke(null, array, target);
            System.out.println("Sorted array: " + Arrays.toString(array));
            System.out.println("Found number " + target + " at position " + index);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }

    private static void printAnnotationInfo(Method method){

        if(method.isAnnotationPresent(MethodInfo.class) && method.isAnnotationPresent(Author.class)){

            MethodInfo annotationMethodInfo = method.getAnnotation(MethodInfo.class);
            System.out.printf("Annotation: MethodInfo{name='%s', returnType='%s', description='%s'}%n",
                    annotationMethodInfo.name(),
                    annotationMethodInfo.returnType(),
                    annotationMethodInfo.description());

            Author annotationAuthor = method.getAnnotation(Author.class);
            System.out.printf("Annotation: Author{name='%s'}%n",
                    annotationAuthor.name());

        }

    }
}
