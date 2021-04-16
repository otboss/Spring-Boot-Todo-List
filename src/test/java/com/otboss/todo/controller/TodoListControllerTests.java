package com.otboss.todo.controller;

import com.google.gson.Gson;
import com.otboss.todo.model.TodoListItem;
import com.otboss.todo.model.Token;
import com.otboss.todo.model.User;
import com.otboss.todo.repository.UserRepository;
import com.otboss.todo.utility.JWTUtility;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoListControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private UserRepository userRepository;

    private User testUser = new User("example123@example.com", "password");

    @Test
    @Order(3)
    public void createTodoListItem() {
        this.userRepository.save(this.testUser);
        Token token = new Token(this.testUser.getEmail());
        TodoListItem listItem = new TodoListItem("", (long) 1);
        Gson gson = new Gson();
        String listItemStringified = gson.toJson(listItem);
        System.out.println(listItemStringified);
        MockHttpServletResponse response = new MockHttpServletResponse();
        try {
            response = this.mockMvc.perform(
                    post("/api/v1/list").header("Authorization", "Bearer " + this.jwtUtility.generateToken(token))
                            .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                            .content(listItemStringified))
                    .andReturn().getResponse();
        } catch (Exception e) {
        }
        assertThat(response.getStatus()).isEqualTo(201);
    }

}