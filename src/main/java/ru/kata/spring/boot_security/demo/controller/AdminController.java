package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

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

    @GetMapping
    public String userList(Principal principal, Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        model.addAttribute("allRoles", roleService.getAllRoles());
        model.addAttribute("newUser", new User());
        model.addAttribute("curUser", userService.findUserByUsername(principal.getName()));
        return "users";
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return "redirect:/admin";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
//        model.addAttribute("allRoles", roleService.getAllRoles());
//        model.addAttribute("allUsers", userService.allUsers());
        return "redirect:/admin";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("newUser") User user, Model model) {
        userService.saveUser(user);
//        model.addAttribute("allUsers", userService.allUsers());
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String showUser(@RequestParam("id") Long userId, Model model) {
        model.addAttribute("user", userService.findUserById(userId));
//        model.addAttribute("allUsers", userService.allUsers());
        return "redirect:/admin";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam("id") Long userId, Model model) {
        model.addAttribute("theUser", userService.findUserById(userId));
//        model.addAttribute("allRoles", roleService.getAllRoles());
//        model.addAttribute("allUsers", userService.allUsers());
        return "redirect:/admin";
    }

    @PatchMapping("/updateUser/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @PathVariable("id") Long id) {
        userService.updateUser(id, user);
//        model.addAttribute("allUsers", userService.allUsers());
        return "redirect:/admin";
    }
}
