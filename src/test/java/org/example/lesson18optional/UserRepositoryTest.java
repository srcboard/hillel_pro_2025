package org.example.lesson18optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserRepositoryTest {

    @Test
    void testEmptyUserRepository() {

        UserRepository userRepository = new UserRepository();

        Assertions.assertEquals(Optional.empty(), userRepository.findUserById(1));
        Assertions.assertEquals(Optional.empty(),
                userRepository.findUserByEmail("jane@example.com"));
        Assertions.assertEquals(Optional.empty(), userRepository.findAllUsers());

    }

    @Test
    void testNonEmptyUserRepository() {

        List<User> userList = new DataRepository().getListOfUsers();
        Map<Integer, User> userMap = userList.stream()
                .collect(Collectors.toMap(User::id, user -> user));
        UserRepository userRepository = new UserRepository(userList);

        Assertions.assertEquals(Optional.of(userMap.get(1)), userRepository.findUserById(1));
        Assertions.assertEquals(Optional.of(userMap.get(2)),
                userRepository.findUserByEmail("jane@example.com"));
        Assertions.assertEquals(userList.size(),
                userRepository.findAllUsers().orElse(List.of()).size());

    }

}
