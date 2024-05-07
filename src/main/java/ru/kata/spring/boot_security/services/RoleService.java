package ru.kata.spring.boot_security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.entities.Role;
import ru.kata.spring.boot_security.repositories.RoleRepository;

import java.util.List;

@Service
public class RoleService implements RoleServiceInt {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    @Transactional
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

}
