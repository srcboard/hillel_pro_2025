package org.example.lesson_26_grasp_solid;

public class Address {
    private final String street;

    public Address(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return street;
    }
}
