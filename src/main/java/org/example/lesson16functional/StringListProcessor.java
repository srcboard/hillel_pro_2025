package org.example.lesson16functional;

import org.jetbrains.annotations.NotNull;

public class StringListProcessor {
    public static int countUppercase(@NotNull String s) {
        return s.chars().map(c -> Character.isUpperCase(c) ? 1 : 0).sum();
    }
}
