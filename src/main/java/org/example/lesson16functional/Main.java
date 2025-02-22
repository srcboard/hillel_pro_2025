package org.example.lesson16functional;

import java.util.function.Function;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        MathOperation addition = new MathOperation() {
            @Override
            public int operate(int a, int b) {
                return a + b;
            }
        };
        System.out.println("Result of adding 10 and 15: " + addition.operate(10, 15));

        StringManipulator toUpperCaseManipulator = s -> s.toUpperCase();
        String originalString = "hello world";
        System.out.printf("String \"%s\" in uppercase: %s%n",
                originalString,
                toUpperCaseManipulator.manipulate(originalString));

        Function<String, Integer> countUppercaseFunction = StringListProcessor::countUppercase;
        String sampleString = "Hello World";
        System.out.printf("Number of uppercase letter in \"%s\": %d%n",
                sampleString,
                countUppercaseFunction.apply(sampleString));

        Supplier<Integer> randomNumberSupplier = () ->
                RandomNumberGenerator.generateRandomNumber(1, 100);
        System.out.println("Random number (1 - 100): " + randomNumberSupplier.get());
    }
}
