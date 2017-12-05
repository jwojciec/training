package com.example.training.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.io.FileReader;
import java.io.IOException;

import com.example.training.repository.H2Repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.example.training.model.User;
import com.google.common.collect.ImmutableList;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {
    private static final String ALL_USERS_PATH = "src/test/resources/user/allUsers.json";
    private static final String SINGLE_USER_PATH = "src/test/resources/user/singleUser.json";
    private static final User TEST_USER1 = new User("2a3993ee-d74e-42ac-af90-9390642db803", "John", "Doe");
    private static final User TEST_USER2 = new User("8644d523-eaf2-412b-87b9-82ceba72ec6b", "Foo", "Bar");
    private static final User TEST_USER_TO_ADD = new User("8644d523-eaf2-xxxx-87b9-82ceba72ec6b", "Joe", "Ziggy");
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private MockMvc mockMvc;

    @Mock
    private H2Repository h2Repository;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(new UserController(h2Repository)).build();

        when(h2Repository.getAllUsers()).thenReturn(ImmutableList.of(TEST_USER1, TEST_USER2));
        when(h2Repository.getUserById("1")).thenReturn(TEST_USER1);
    }

    @Test
    public void When_GettingAllUsers_Expect_CorrectResponse() throws Exception {
        this.mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().json(readJsonFile(ALL_USERS_PATH)));
    }

    @Test
    public void When_GettingUserById_Expect_CorrectResponse() throws Exception {
        this.mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(readJsonFile(SINGLE_USER_PATH)));
    }

    @Test
    public void When_addingUser_Expect_CorrectResponse() throws Exception {
        this.mockMvc.perform(post("/users/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(OBJECT_MAPPER.writeValueAsString(TEST_USER_TO_ADD)))
                .andExpect(status().isOk());
    }

    @Test
    public void When_deletingUser_Expect_CorrectResponse() throws Exception {
        this.mockMvc.perform(delete("/users/delete/1"))
                .andExpect(status().isOk())
                .andReturn();
    }

    private static String readJsonFile(String path) throws IOException, ParseException {
        return new JSONParser().parse(new FileReader(path)).toString();
    }
}
