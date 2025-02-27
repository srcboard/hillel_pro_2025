package org.example.lesson16functional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

class RandomNumberGeneratorTest {

    @Test
    void testGenerateRandomNumber() {
        int min = 1;
        int max = 100;
        Supplier<Integer> randomNumberSupplier = () ->
                RandomNumberGenerator.generateRandomNumber(min, max);
        int result = randomNumberSupplier.get();
        Assertions.assertTrue(
                result >= min && result <= max,
                "Generated random number should be within the range (1 - 100)");
    }
}
