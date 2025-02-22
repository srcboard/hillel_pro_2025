package org.example.lesson16functional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MathOperationTest {

    @Test
    void testAdditionOperation() {
        MathOperation addition = new MathOperation() {
            @Override
            public int operate(int a, int b) {
                return a + b;
            }
        };
        Assertions.assertEquals(
                25,
                addition.operate(10, 15),
                "Addition operation should return 25");
    }
}
