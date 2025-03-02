package org.example.lesson18optional;

import java.util.ArrayList;
import java.util.List;

public class DataRepository {

    List<User> getListOfUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "John Doe", "john@example.com"));
        users.add(new User(2, "Jane Smith", "jane@example.com"));
        return users;
    }

}
