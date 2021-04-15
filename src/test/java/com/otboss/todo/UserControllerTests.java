package com.otboss.todo;

import com.otboss.todo.controller.auth.UserController;
import com.otboss.todo.model.Token;
import com.otboss.todo.model.User;
import com.otboss.todo.utility.JWTUtility;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerTests {

    @Autowired
    private UserController controller;

    @Autowired
    private JWTUtility jwtUtility;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private User testUser = new User("example@example.com", "password");

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    @DisplayName("Given that user provides valid email and password a 201 - created response should be returned")
    public void registration() {
        String registerUrl = String.format("http://localhost:%d/api/v1/auth/register", this.port);
        ResponseEntity<String> response = this.restTemplate.postForEntity(registerUrl, this.testUser, String.class, "");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(null);
    }

    @Test
    @DisplayName("Given that user provides existing email and password a 202 - accepted response should be returned")
    public void login() {
        String loginUrl = String.format("http://localhost:%d/api/v1/auth/login", this.port);
        User testUser = new User("example@example.com", "password");
        ResponseEntity<String> response = this.restTemplate.postForEntity(loginUrl, this.testUser, String.class, "");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        Token token = this.jwtUtility.parseToken(response.getBody());
        assertThat(token.getEmail()).isEqualTo(testUser.getEmail());
    }
}
