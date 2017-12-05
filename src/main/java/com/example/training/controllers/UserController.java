package com.example.training.controllers;

import java.util.List;

import com.example.training.repository.H2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.training.model.User;

@RestController
@RequestMapping("/users")
public class UserController {
    private final H2Repository repository;

    @Autowired
    public UserController(H2Repository repo) {
        this.repository = repo;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return repository.getAllUsers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable String id) {
        return repository.getUserById(id);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable String id) {
        repository.delete(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addUser(@RequestBody User user) {
        repository.add(user);
    }

}
