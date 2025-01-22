package org.example.lesson9_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Box<T extends Fruit> {
    private List<T> fruits;

    public Box() {
        this.fruits = new ArrayList<>();
    }

    private List<T> getFruits() {
        return fruits;
    }

    private void setFruits(List<T> fruits) {
        this.fruits = fruits;
    }

    public void addFruit(T fruit) {
        this.fruits.add(fruit);
    }

    public void addFruits(T[] fruits) {
        Collections.addAll(this.fruits, fruits);
    }

    public float getWeight() {
        return this.getFruits().stream()
                .map(Fruit::getWeight)
                .reduce(0.0f, Float::sum);
    }

    public boolean compare(Box<? extends Fruit> otherBox) {
        return Float.compare(this.getWeight(), otherBox.getWeight()) == 0;
    }

    public void merge(Box<T> otherBox) {
        if (this == otherBox) {
            throw new IllegalArgumentException("Cannot merge a box with itself.");
        }
        otherBox.getFruits().forEach(this::addFruit);
        otherBox.setFruits(new ArrayList<>());
    }
}
