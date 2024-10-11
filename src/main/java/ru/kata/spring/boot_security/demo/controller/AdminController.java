package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String user(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        User currentUser = userService.getUserByEmail("admin");
        model.addAttribute("user", currentUser);
        return "admin";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@RequestParam Integer id, @ModelAttribute User user) {
        userService.updateUserById(id, user);
        return "redirect:/admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@ModelAttribute User user) {
        userService.removeUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/add-user")
    public String addUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }
}
