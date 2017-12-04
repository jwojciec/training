package com.example.training.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.example.training.model.User;

public class SimpleListRepositoryTest {
    private static final SimpleListRepository REPOSITORY = new SimpleListRepository();
    private static final User TEST_USER = new User("4", "Jon", "Snow");
    private static final User NEW_TEST_USER = new User("5", "Jim", "Ziggy");
    private static final int NEW_TEST_USER_ID = 4;

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

    @Test
    public void When_AddingUser_Expect_CorrectResult() {
        REPOSITORY.addUser(NEW_TEST_USER);
        assertThat(REPOSITORY.getAllUsers().size(), is(5));
        // rollback new user
        REPOSITORY.deleteUser(NEW_TEST_USER_ID);
    }

    @Test
    public void When_DeletingUser_Expect_CorrectResult() {
        REPOSITORY.deleteUser(3);
        assertThat(REPOSITORY.getAllUsers().size(), is(3));
        REPOSITORY.addUser(TEST_USER);
    }
}
