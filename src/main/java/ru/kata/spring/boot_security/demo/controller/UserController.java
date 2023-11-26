package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserDtlsService;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserDtlsService userDtlsService;

    @Autowired
    public void setUserService(UserDtlsService userDtlsService) {
        this.userDtlsService = userDtlsService;
    }

    @GetMapping
    public String getUserInfo(Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user",currentUser);
        return "user";
    }
}
