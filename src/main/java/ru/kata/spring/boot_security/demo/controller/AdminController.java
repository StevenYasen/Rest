package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "users";
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") Long userId) {
        userService.deleteUser(userId);
        return "redirect:/admin/";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "user-info";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin/";
    }

    @GetMapping("/user")
    public String showUser(@RequestParam("id") Long userId, Model model) {
        model.addAttribute("user", userService.findUserById(userId));
        return "show-user";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam("id") Long userId, Model model) {
        model.addAttribute("theUser", userService.findUserById(userId));
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "update-user";
    }

    @PatchMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin/";
    }
}
