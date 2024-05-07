package ru.kata.spring.boot_security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.configs.BCryptCoder;
import ru.kata.spring.boot_security.entities.Role;
import ru.kata.spring.boot_security.entities.User;
import ru.kata.spring.boot_security.services.RoleService;
import ru.kata.spring.boot_security.services.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class TestDataInit {

    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public TestDataInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;

    }

    @PostConstruct
    public void init() {
        final Role ROLE_USER = new Role("ROLE_USER");
        final Role ROLE_ADMIN = new Role("ROLE_ADMIN");
        roleService.save(new Role("ROLE_GUEST"));

        roleService.save(ROLE_USER);
        roleService.save(ROLE_ADMIN);


        userService.saveUser(new User("Жмышенко", "Валерий", 54, "admin@mail.com", "admin",
                new HashSet<>(Set.of(ROLE_ADMIN))));

        userService.saveUser(new User("Аюбджони", "Рабджазода", 13, "user@mail.com", "user",
                new HashSet<>(Set.of(ROLE_USER))));
    }
}