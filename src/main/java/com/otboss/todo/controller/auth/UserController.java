package com.otboss.todo.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;
import io.jsonwebtoken.Jwts;

import com.otboss.todo.model.User;
import com.otboss.todo.repository.UserRepository;
import com.otboss.todo.utility.Env;

@RestController
@RequestMapping("api/v1/auth")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String login(User credentials) {
        // TODO: Retrieve user credentials from the database and authenticate user
        return "";
    }

    @PostMapping("/register")
    public String register(@RequestBody User credentials) {
        // TODO: Save user to database return 201 status code
        credentials.setPassword(BCrypt.withDefaults().hashToString(12, credentials.getPassword().toCharArray()));
        return "";
    }
}