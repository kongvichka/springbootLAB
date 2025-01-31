package com.example.userservice.controller;

import com.example.userservice.exception.ResourceNotFoundException;
import com.example.userservice.model.User;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Create User
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestParam String name, @RequestParam String email) {
        return userService.createUser(name, email);
    }

    // Get All Users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get User by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ResourceNotFoundException("User not found with ID " + id);
        }
    }

    // Update User
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestParam String name, @RequestParam String email) {
        User updatedUser = userService.updateUser(id, name, email);
        if (updatedUser == null) {
            throw new ResourceNotFoundException("User not found with ID " + id);
        }
        return updatedUser;
    }

    // Delete User
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUser(id);
        if (!isDeleted) {
            throw new ResourceNotFoundException("User not found with ID " + id);
        }
    }

    // Custom Exception Handler for ResourceNotFoundException
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ex.getMessage();
    }
}
