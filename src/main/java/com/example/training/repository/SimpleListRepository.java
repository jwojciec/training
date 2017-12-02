package com.example.training.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.training.model.User;

@Component
public class SimpleListRepository {
    private static List<User> USER_LIST = new ArrayList<User>() {{
        add(new User("1", "Tyrion", "Lannister"));
        add(new User("2", "Daenerys", "Targaryen"));
        add(new User("3", "Arya", "Stark"));
        add(new User("4", "Jon", "Snow"));
    }};

    public List<User> getAllUsers() {
        return USER_LIST;
    }

    public User getUser(int id) {
        return USER_LIST.get(id);
    }

    public void deleteUser(int id) { USER_LIST.remove(id);}

    public void addUser(User user) { USER_LIST.add(user);}

}
