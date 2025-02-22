package org.example.lesson16functional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

class StringListProcessorTest {

    @Test
    void testCountUppercase() {
        Function<String, Integer> countUppercaseFunction = StringListProcessor::countUppercase;
        Assertions.assertEquals(
                2,
                countUppercaseFunction.apply("Hello World"),
                "There should be 2 uppercase letters in the string");
    }
}
