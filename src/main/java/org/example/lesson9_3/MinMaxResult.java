package org.example.lesson9_3;

public class MinMaxResult {
    private final int min;
    private final int max;

    public MinMaxResult(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public String toString() {
        return "MinMaxResult{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }
}
