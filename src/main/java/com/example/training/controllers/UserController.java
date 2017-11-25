package com.example.training.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.training.model.User;
import com.example.training.repository.SimpleListRepository;

@RestController
@RequestMapping("/users")
public class UserController {
    private final SimpleListRepository repository;

    @Autowired
    public UserController(SimpleListRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return repository.getAllUsers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable int id) {
        return repository.getUser(id);
    }

    //ToDo implement all other methods:
    public void deleteUser(@PathVariable int id) {
    }

    public void addUser(@RequestBody User user){
    }

}
