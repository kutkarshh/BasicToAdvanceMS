package com.my.user.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.user.service.entities.User;
import com.my.user.service.services.impl.UserServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    // Create User
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {

        User createdUser = userServiceImpl.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    // Create Multiple Users
    @PostMapping("/multi")
    public ResponseEntity<List<User>> createMultipleUsers(@RequestBody List<User> users) {
        List<User> createdUsers = userServiceImpl.createMultipleUsers(users);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUsers);
    }

    // Update User

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = userServiceImpl.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete User
    // Get User
    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
        User user = userServiceImpl.getUser(userId);
        return ResponseEntity.ok(user);
    }

    // Get All Users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userServiceImpl.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
