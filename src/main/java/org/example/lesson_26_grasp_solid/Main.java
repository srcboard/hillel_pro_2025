package org.example.lesson_26_grasp_solid;

public class Main {
    public static void main(String[] args) {
        User user = new User("Example");
        Address address = new Address("Example St");

        user.setAddress(address);
        user.printAddress();
    }
}
