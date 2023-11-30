package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService {
    User findUserById(Long userId);
    User findUserByUsername(String username);
    List<User> allUsers();
    void saveUser(User user);
    void updateUser(Long id,User user);
    void deleteUser(Long userId);
}
