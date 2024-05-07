package ru.kata.spring.boot_security.services;

import ru.kata.spring.boot_security.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceInt {

    public List<User> getAllUsers();

    public void saveUser(User user);

    public void deleteUser(Long id);

    public Optional<User> findByEmail(String email);
}
