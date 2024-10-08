package com.my.user.service.services;

import java.util.List;

import com.my.user.service.entities.User;

public interface UserService {

    // User Operations will be added here

    // Create User
    User createUser(User user);

    // Create Multiple Users
    List<User> createMultipleUsers(List<User> users);

    // Update User
    User updateUser(User user);

    // Delete User
    void deleteUser(String userId);

    // Get User
    User getUser(String userId);

    // Get All Users
    List<User> getAllUsers();
}
