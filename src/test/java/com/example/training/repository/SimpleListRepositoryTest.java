package com.example.training.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.example.training.model.User;

public class SimpleListRepositoryTest {
    private static final SimpleListRepository REPOSITORY = new SimpleListRepository();
    private static final User TEST_USER = new User("4", "Jon", "Snow");

    @Test
    public void When_GettingAllUsers_Expect_CorrectResult() {
        assertThat(REPOSITORY.getAllUsers().size(), is(4));
    }

    @Test
    public void When_GettingUserById_Expect_CorrectResult() {
        assertThat(REPOSITORY.getUser(3).getId(), is(TEST_USER.getId()));
        assertThat(REPOSITORY.getUser(3).getName(), is(TEST_USER.getName()));
        assertThat(REPOSITORY.getUser(3).getSurname(), is(TEST_USER.getSurname()));
    }

    //ToDo implement unit tests for SimpleListRepository
}
