package org.example.lesson14_2;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Benchmark {

  List<Integer> list;

  public Benchmark(List<Integer> list) {
    this.list = list;
  }

  void add() {
    long start = System.nanoTime();

    for (int i = 0; i < 10000000; i++) {
      list.add(i);
    }

    long result = System.nanoTime() - start;

    System.out.println("Time to add: " + result / 1000000 + " ms");

  }

  void get() {
    long start = System.nanoTime();

    for (int i = 0; i < 10000000; i++) {
      list.get(i);
    }

    long result = System.nanoTime() - start;

    System.out.println("Time to get : " + result / 1000000 + " ms");

  }

  void addInMiddle(int iterations, String capacity) {
    long start = System.nanoTime();

    for (int i = 0; i < iterations; i++) {
      list.add(list.size() / 2, i);
    }

    long result = System.nanoTime() - start;

    System.out.printf("%s, iterations %d%s: Time to add in middle %d ms%n",
            list.getClass().getSimpleName(),
            iterations,
            !capacity.isEmpty() ? ", capacity " + capacity : "",
            result / 1000000);

  }

  void addFirst(int iterations, String capacity) {
    long start = System.nanoTime();

    for (int i = 0; i < iterations; i++) {
      list.addFirst(i);
    }

    long result = System.nanoTime() - start;

    System.out.printf("%s, iterations %d%s: Time to add first %d ms%n",
            list.getClass().getSimpleName(),
            iterations,
            !capacity.isEmpty() ? ", capacity " + capacity : "",
            result / 1000000);

  }

  public static void main(String[] args) {
    Benchmark benchmark = new Benchmark(new ArrayList<>());
    benchmark.add();
    benchmark.get();

    int iterations = 100000;

    new Benchmark(new ArrayList<>(iterations)).addInMiddle(iterations, String.valueOf(iterations));
    new Benchmark(new ArrayList<>()).addInMiddle(iterations, "default");
    new Benchmark(new LinkedList<>()).addInMiddle(iterations,"");

    new Benchmark(new ArrayList<>(iterations)).addFirst(iterations, String.valueOf(iterations));
    new Benchmark(new ArrayList<>()).addFirst(iterations, "default");
    new Benchmark(new LinkedList<>()).addFirst(iterations, "");


  }
}

// Array list add with capacity 116 ms
// Array list add default capacity 160 ms
// Array list get default capacity 10 ms

// Linked list add default capacity 1020 ms
// Linked list get with capacity 1020 ms
