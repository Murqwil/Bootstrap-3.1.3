package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public AdminController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping
    public String user(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        User currentUser = userRepository.findByEmail("admin");
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
        userRepository.delete(user);
        return "redirect:/admin";
    }

    @PostMapping("/add-user")
    public String addUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }
}
