package org.example.lesson18optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private final List<User> users;

    public UserRepository() {
        this(new ArrayList<>());
    }

    public UserRepository(List<User> users) {
        this.users = users;
    }

    public Optional<User> findUserById(int id) {
        return users.stream()
                .filter(user -> user.id() == id)
                .findFirst();
    }

    public Optional<User> findUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.email().equals(email))
                .findFirst();
    }

    public Optional<List<User>> findAllUsers() {
        return users.isEmpty() ? Optional.empty() : Optional.of(users);
    }
}
