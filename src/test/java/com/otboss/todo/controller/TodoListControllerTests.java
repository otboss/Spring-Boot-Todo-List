package com.otboss.todo.controller;

import com.google.gson.Gson;
import com.otboss.todo.model.TodoListItem;
import com.otboss.todo.model.Token;
import com.otboss.todo.repository.TodoListRepository;
import com.otboss.todo.repository.UserRepository;
import com.otboss.todo.utility.JWTUtility;
import com.otboss.todo.utility.PageTodoListItem;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.io.UnsupportedEncodingException;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static com.otboss.todo.utility.TestConstants.testUser;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class TodoListControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TodoListRepository todoListRepository;

    @Test
    @Order(1)
    public void createTodoListItem() {
        this.userRepository.save(testUser);
        Token token = new Token(testUser.getEmail());
        TodoListItem listItem = new TodoListItem("initial entry", (long) 1);
        Gson gson = new Gson();
        String listItemStringified = gson.toJson(listItem);
        MockHttpServletResponse response = new MockHttpServletResponse();
        try {
            response = this.mockMvc.perform(
                    post("/api/v1/list").header("Authorization", "Bearer " + this.jwtUtility.generateToken(token))
                            .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                            .content(listItemStringified))
                    .andReturn().getResponse();
        } catch (Exception e) {
            fail("exception thrown");
        }
        assertThat(response.getStatus()).isEqualTo(201);
    }

    @Test
    @Order(2)
    public void getTodoListItem() {
        Token token = new Token(testUser.getEmail());
        MockHttpServletResponse response = new MockHttpServletResponse();
        try {
            response = this.mockMvc.perform(get("/api/v1/list?filter=&offset=0&limit=40")
                    .header("Authorization", "Bearer " + this.jwtUtility.generateToken(token))
                    .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        } catch (Exception e) {
            fail("exception thrown");
        }
        String responseText = "";
        try {
            responseText = response.getContentAsString();
        } catch (UnsupportedEncodingException e) {
            fail("exception thrown");
        }
        Gson gson = new Gson();
        PageTodoListItem parsedResponse = gson.fromJson(responseText, PageTodoListItem.class);
        assertThat(parsedResponse.getContent().size()).isEqualTo(1);
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    @Order(3)
    public void updateTodoListItem() {
        Token token = new Token(testUser.getEmail());
        final String updatedEntryText = "updated entry";
        TodoListItem listItem = new TodoListItem(updatedEntryText, (long) 1);
        listItem.setChecked(true);
        listItem.setId((long) 1);
        Gson gson = new Gson();
        String listItemStringified = gson.toJson(listItem);
        MockHttpServletResponse response = new MockHttpServletResponse();
        try {
            response = this.mockMvc.perform(put("/api/v1/list")
                    .header("Authorization", "Bearer " + this.jwtUtility.generateToken(token))
                    .accept(MediaType.ALL_VALUE).contentType(MediaType.APPLICATION_JSON).content(listItemStringified))
                    .andReturn().getResponse();
        } catch (Exception e) {
            fail("exception thrown");
        }
        TodoListItem retrievedListItem = (this.todoListRepository.findById(listItem.getId())).get();
        assertThat(retrievedListItem.getEntry()).isEqualTo(updatedEntryText);
        assertThat(retrievedListItem.getChecked()).isEqualTo(true);
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    @Order(4)
    public void deleteTodoListItem() {
        Token token = new Token(testUser.getEmail());
        TodoListItem listItem = new TodoListItem("", (long) 1);
        listItem.setId((long) 1);
        Gson gson = new Gson();
        String listItemStringified = gson.toJson(listItem);
        MockHttpServletResponse response = new MockHttpServletResponse();
        try {
            response = this.mockMvc.perform(delete("/api/v1/list")
                    .header("Authorization", "Bearer " + this.jwtUtility.generateToken(token))
                    .accept(MediaType.ALL_VALUE).contentType(MediaType.APPLICATION_JSON).content(listItemStringified))
                    .andReturn().getResponse();
        } catch (Exception e) {
            fail("exception thrown");
        }
        try {
            Optional<TodoListItem> retrievedListItem = this.todoListRepository.findById(listItem.getId());
            retrievedListItem.get();
            fail("todo list item was retrieved without");
        } catch (NoSuchElementException e) {

        }
        assertThat(response.getStatus()).isEqualTo(200);
    }

}