package org.example.lesson18optional;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        List<User> listOfUsers = new DataRepository().getListOfUsers();
        UserRepository userRepository = new UserRepository(listOfUsers);

        Optional<User> userById = userRepository.findUserById(1);
        userById.ifPresentOrElse(
                user -> System.out.println("Found user by ID: " + user),
                () -> System.out.println("User not found by ID")
        );

        Optional<User> userByEmail = userRepository.findUserByEmail("jane@example.com");
        userByEmail.ifPresentOrElse(
                user -> System.out.println("Found user by Email: " + user),
                () -> System.out.println("User not found by Email")
        );

        Optional<List<User>> allUsers = userRepository.findAllUsers();
        allUsers.ifPresentOrElse(
                users -> System.out.println("Total users: " + users.size()),
                () -> System.out.println("No users found")
        );
    }
}
