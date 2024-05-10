package ru.kata.spring.boot_security.services;

import ru.kata.spring.boot_security.entities.Role;

import java.util.List;

public interface RoleServiceInt {

    void save(Role role);

    List<Role> findAll();

}
