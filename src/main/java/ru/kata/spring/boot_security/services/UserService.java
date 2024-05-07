package ru.kata.spring.boot_security.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.configs.BCryptCoder;
import ru.kata.spring.boot_security.entities.User;
import ru.kata.spring.boot_security.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInt {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setPassword(BCryptCoder.passwordEncoder().encode((user.getPassword())));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("User not found with id: " + id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}