package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User updateUserById (Integer id,User userProperty);

    void removeUserById (Integer id);

    User saveUser (User user);
}
