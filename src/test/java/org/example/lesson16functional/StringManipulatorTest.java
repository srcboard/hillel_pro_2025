package org.example.lesson16functional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringManipulatorTest {

    @Test
    void testToUpperCase() {
        StringManipulator toUpperCaseManipulator = s -> s.toUpperCase();
        Assertions.assertEquals(
                "HELLO WORLD",
                toUpperCaseManipulator.manipulate("hello world"),
                "String should be converted to uppercase");
    }
}
