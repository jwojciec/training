package com.example.training.repository;

import com.example.training.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class H2Repository {
    @Autowired
    private static IH2Repository repository;

    public H2Repository(IH2Repository repo) {
        this.repository = repo;
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<User>();
        repository.findAll().forEach(usersList::add);
        return usersList;
    }

    public User getUserById(String id) {
        return repository.findOne(id);
    }

    public void add(User user) {
        repository.save(user);
    }

    public void delete(String id) {
        repository.delete(id);
    }
}
