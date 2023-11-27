package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService {
    public User findUserById(Long userId);
    public List<User> allUsers();
    public void saveUser(User user);
    public void updateUser(User user);
    public void deleteUser(Long userId);
}
