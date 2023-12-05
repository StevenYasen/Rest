package ru.kata.spring.boot_security.demo.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {
    private final UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.allUsers(), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getCurrentUser(Principal principal) {
        return new ResponseEntity<>(userService.findUserByEmail(((User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getEmail()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long userId) {
        return new ResponseEntity<>(userService.findUserById(userId), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.updateUser(user.getId(), user);
        return new ResponseEntity<>(userService.findUserById(user.getId()), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Long> updateUser(@RequestBody Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(userService.findUserByEmail(user.getEmail()), HttpStatus.OK);
    }
}
