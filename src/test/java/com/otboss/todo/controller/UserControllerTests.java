package com.otboss.todo.controller;

import com.google.gson.Gson;
import com.otboss.todo.model.Token;
import com.otboss.todo.utility.JWTUtility;
import com.otboss.todo.utility.TestConstants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static com.otboss.todo.utility.TestConstants.testUser;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class UserControllerTests {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Given that user provides valid email and password a 201 - created response should be returned")
    @Order(1)
    public void registration() {
        Gson gson = new Gson();
        String userStringified = gson.toJson(testUser);
        MockHttpServletResponse response = new MockHttpServletResponse();
        try {
            response = this.mockMvc
                    .perform(post("/api/v1/auth/register").accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON).content(userStringified))
                    .andReturn().getResponse();
        } catch (Exception e) {
            fail("Exception thrown");
        }
        assertThat(response.getStatus()).isEqualTo(201);
    }

    @Test
    @DisplayName("Given that user provides existing email and password a 202 -  accepted response should be returned")
    @Order(2)
    public void login() {
        Gson gson = new Gson();
        String userStringified = gson.toJson(testUser);
        MockHttpServletResponse response = new MockHttpServletResponse();
        try {
            response = this.mockMvc
                    .perform(post("/api/v1/auth/login").accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON).content(userStringified))
                    .andReturn().getResponse();
        } catch (Exception e) {
            fail("Exception thrown");
        }
        assertThat(response.getStatus()).isEqualTo(202);
        String responseAsString = "";
        try {
            responseAsString = response.getContentAsString();
        } catch (Exception e) {
            fail("Exception thrown");
        }
        TestConstants.jwtToken = responseAsString;
        Token token = this.jwtUtility.parseToken(responseAsString);
        assertThat(token.getEmail()).isEqualTo(testUser.getEmail());
    }
}
