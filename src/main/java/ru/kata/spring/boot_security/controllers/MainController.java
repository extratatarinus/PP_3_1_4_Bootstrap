package ru.kata.spring.boot_security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.entities.User;
import ru.kata.spring.boot_security.services.RoleService;
import ru.kata.spring.boot_security.services.UserService;

import java.security.Principal;
import java.util.Optional;

@Controller
public class MainController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public MainController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String showAllUsers(Model model, Principal principal) {
        Optional<User> optionalUser = userService.findByEmail(principal.getName());
        User authUser = optionalUser.orElse(null);
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("allRoles", roleService.findAll());
        model.addAttribute("newUser", new User());
        model.addAttribute("authUser", authUser);
        return "index";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user, Principal principal) {
        Optional<User> authUserOptional = userService.findByEmail(principal.getName());
        userService.saveUser(user);
        return authUserOptional.map(authUser -> {
            if (authUser.getId().equals(user.getId()) && !user.getRolesNames().contains("ADMIN")) {
                return "redirect:/logout";
            } else {
                return "redirect:/";
            }
        }).orElse("redirect:/");
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam Long id, Principal principal) {
        Optional<User> authUserOptional = userService.findByEmail(principal.getName());
        userService.deleteUser(id);
        return authUserOptional.map(authUser -> {
            if (authUser.getId().equals(id)) {
                userService.deleteUser(id);
                return "redirect:/logout";
            } else {
                return "redirect:/";
            }
        }).orElse("redirect:/");
    }
}
