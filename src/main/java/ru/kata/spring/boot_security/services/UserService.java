package ru.kata.spring.boot_security.services;

import org.hibernate.Hibernate;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.configs.BCryptCoder;
import ru.kata.spring.boot_security.repositories.UserRepository;
import ru.kata.spring.boot_security.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

   private final UserRepository userRepository;


   public UserService(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   @Transactional
   public List<User> getAllUsers() {
      return userRepository.findAll();
   }

   @Transactional
   public void saveUser(User user) {
      user.setPassword(BCryptCoder.passwordEncoder().encode((user.getPassword())));
      userRepository.save(user);
   }

   @Transactional
   public void deleteUser(Long id) {
      userRepository.deleteById(id);
   }

   private User findUser(Long id) {
      return userRepository.findById(id).orElseThrow(() ->
              new UsernameNotFoundException("User not found with id: " + id));
   }

   public Optional<User> findByEmail(String email) {
      return userRepository.findByEmail(email);
   }


}