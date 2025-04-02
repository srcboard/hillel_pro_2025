package org.example.lesson25gof;

class Car implements Transport {
    @Override
    public void move() {
        System.out.println("Car is moving on the road.");
    }
}
