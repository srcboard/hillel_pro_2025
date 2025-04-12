package org.example.lesson_26_grasp_solid;

public class User {
    private final String name;
    private Address address;

    public User(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void printAddress() {
        if (address != null) {
            System.out.println("Address of user " + name + ": " + address);
        } else {
            System.out.println("No address set for user " + name);
        }
    }
}
