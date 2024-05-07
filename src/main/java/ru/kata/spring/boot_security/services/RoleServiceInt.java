package ru.kata.spring.boot_security.services;

import ru.kata.spring.boot_security.entities.Role;

import java.util.List;

public interface RoleServiceInt {

    public void save(Role role);

    public List<Role> findAll();

}
