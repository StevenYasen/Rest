package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserDtlsService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserDtlsService userDtlsService;

    @Autowired
    public AdminController(UserDtlsService userDtlsService) {
        this.userDtlsService = userDtlsService;
    }

    @GetMapping("/users")
    public String userList(Model model) {
        List<User> allUsers = userDtlsService.allUsers();
        model.addAttribute("allUsers", allUsers);
        return "users";
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") Long userId) {
            userDtlsService.deleteUser(userId);
        return "redirect:/admin/users";
    }
    @GetMapping("/addNewUser")
    public String addNewUser(Model model) {
        User u = new User();
        model.addAttribute("user", u);
        return "user-info";
    }
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userDtlsService.saveUser(user);
        return "redirect:/admin/users";
    }
    @GetMapping("/user")
    public String showUser(@RequestParam("id") Long userId, Model model) {
        User user = userDtlsService.findUserById(userId);
        model.addAttribute("user", user);
        return "show-user";
    }
    @GetMapping("/edit")
    public String editUser(@RequestParam("id") Long userId, Model model) {
        model.addAttribute("user", userDtlsService.findUserById(userId));
        return "update-user";
    }
    @PatchMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User user) {
        userDtlsService.updateUser(user);
        return "redirect:/admin/users";
    }


}
