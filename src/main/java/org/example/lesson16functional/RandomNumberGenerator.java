package org.example.lesson16functional;

import java.util.Random;

public class RandomNumberGenerator {
    public static int generateRandomNumber(int min, int max) {
        return new Random().nextInt(min, max + 1);
    }
}
