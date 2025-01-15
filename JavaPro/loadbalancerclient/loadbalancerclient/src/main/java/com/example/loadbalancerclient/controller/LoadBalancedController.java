package com.example.loadbalancerclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class LoadBalancedController {

    @Autowired
    private RestTemplate restTemplate;

    // Call UserService
    @GetMapping("/users")
    public ResponseEntity<String> getUsers() {
        String response = restTemplate.getForObject("http://userservice/users", String.class);
        return ResponseEntity.ok(response);
    }

    // Call ProductService
    @GetMapping("/products")
    public ResponseEntity<String> getProducts() {
        String response = restTemplate.getForObject("http://productservice/products", String.class);
        return ResponseEntity.ok(response);
    }
}

/*
// Enable if want to allow CRUD

package com.example.loadbalancerclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/users")
public class LoadBalancedController {

    @Autowired
    private RestTemplate restTemplate;

    private final String userServiceBaseUrl = "http://userservice/users";

    // GET: Retrieve all users
    @GetMapping
    public ResponseEntity<String> getAllUsers() {
        String response = restTemplate.getForObject(userServiceBaseUrl, String.class);
        return ResponseEntity.ok(response);
    }

    // POST: Create a new user
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody String user) {
        String response = restTemplate.postForObject(userServiceBaseUrl, user, String.class);
        return ResponseEntity.ok(response);
    }

    // PUT: Update an existing user
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") Long id, @RequestBody String user) {
        restTemplate.put(userServiceBaseUrl + "/" + id, user);
        return ResponseEntity.ok().build();
    }

    // DELETE: Delete an existing user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        restTemplate.delete(userServiceBaseUrl + "/" + id);
        return ResponseEntity.ok().build();
    }
}
*/