package com.otboss.todo.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.otboss.todo.model.User;
import com.otboss.todo.repository.UserRepository;

@RestController
@RequestMapping("api/v1/auth")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String login(User credentials) {
        // TODO: Retrieve user credentials from the database
        return "";
    }

    @PostMapping("/register")
    public String register(User credentials) {
        return "";
    }
}