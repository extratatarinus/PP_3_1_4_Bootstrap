package ru.kata.spring.boot_security.services;

import ru.kata.spring.boot_security.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceInt {

    List<User> getAllUsers();

    void saveUser(User user);

    void deleteUser(Long id);

    Optional<User> findByEmail(String email);
}
