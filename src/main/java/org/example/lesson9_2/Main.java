package org.example.lesson9_2;

public class Main {
    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>();
        appleBox.addFruit(new Apple());
        appleBox.addFruit(new Apple());

        Box<Orange> orangeBox = new Box<>();
        orangeBox.addFruits(new Orange[]{new Orange(), new Orange(), new Orange()});

        System.out.println("Apple Box Weight: " + appleBox.getWeight());
        System.out.println("Orange Box Weight: " + orangeBox.getWeight());

        System.out.println("Boxes have the same weight: " + appleBox.compare(orangeBox));

        Box<Apple> anotherAppleBox = new Box<>();
        anotherAppleBox.addFruit(new Apple());

        appleBox.merge(anotherAppleBox);

        System.out.println("Apple Box Weight after merge: " + appleBox.getWeight());
        System.out.println("Another Apple Box Weight after merge: " + anotherAppleBox.getWeight());
    }
}
