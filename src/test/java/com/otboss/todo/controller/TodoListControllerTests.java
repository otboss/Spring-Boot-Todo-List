package com.otboss.todo.controller;

import com.otboss.todo.constants.TestConstants;
import com.otboss.todo.controller.list.TodoListController;
import com.otboss.todo.model.TodoListItem;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TodoListControllerTests {

    @Autowired
    private TodoListController controller;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TestConstants testConstants;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void createTodoListItem() {
        String url = String.format("http://localhost:%d/api/v1/list", this.port);
        TodoListItem listItem = new TodoListItem("", (long) 1);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(this.testConstants.jwtToken);
        HttpEntity<TodoListItem> entity = new HttpEntity<>(listItem, headers);
        ResponseEntity<String> response = this.restTemplate.postForEntity(url, entity, String.class, "");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

}