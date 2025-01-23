package org.example.lesson9_3;

import java.util.Collections;
import java.util.List;

public class MinMaxProcessor extends Processor<List<Integer>, MinMaxResult> {

    public MinMaxProcessor(String id, String type, List<Integer> data) {
        super(id, type, data);
    }

    @Override
    public MinMaxResult process() {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("The list cannot be empty or null");
        }
        int min = Collections.min(data);
        int max = Collections.max(data);
        return new MinMaxResult(min, max);
    }

    public static void main(String[] args) {
        List<Integer> numbers = List.of(87, 5, 45, 19, 76, 21);
        MinMaxProcessor processor = new MinMaxProcessor("minMaxProcessor", "minMaxProcessorType", numbers);

        System.out.println("List of numbers: " + numbers);
        System.out.println("Result: " + processor.process());
    }
}
