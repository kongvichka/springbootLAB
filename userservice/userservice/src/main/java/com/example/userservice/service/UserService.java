package com.example.userservice.service;

import com.example.userservice.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private List<User> users = new ArrayList<>();
    private Long userIdCounter = 1L;

    // Create User
    public User createUser(String name, String email) {
        User user = new User(userIdCounter++, name, email);
        users.add(user);
        return user;
    }

    // Get All Users
    public List<User> getAllUsers() {
        return users;
    }

    // Get User by ID
    public Optional<User> getUserById(Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    // Update User
    public User updateUser(Long id, String name, String email) {
        Optional<User> userOptional = getUserById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(name);
            user.setEmail(email);
            return user;
        }
        return null;
    }

    // Delete User
    public boolean deleteUser(Long id) {
        return users.removeIf(user -> user.getId().equals(id));
    }
}
