package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public AdminController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/")
    public String userList(Model model) {
        model.addAttribute("allUsers", userServiceImpl.allUsers());
        return "users";
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") Long userId) {
        userServiceImpl.deleteUser(userId);
        return "redirect:/admin/";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "user-info";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userServiceImpl.saveUser(user);
        return "redirect:/admin/";
    }

    @GetMapping("/user")
    public String showUser(@RequestParam("id") Long userId, Model model) {
        model.addAttribute("user", userServiceImpl.findUserById(userId));
        return "show-user";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam("id") Long userId, Model model) {
        model.addAttribute("user", userServiceImpl.findUserById(userId));
        return "update-user";
    }

    @PatchMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User user) {
        userServiceImpl.updateUser(user);
        return "redirect:/admin/";
    }
}
