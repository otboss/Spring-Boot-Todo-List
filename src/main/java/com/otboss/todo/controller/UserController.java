package com.otboss.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import at.favre.lib.crypto.bcrypt.BCrypt;

import java.util.List;

import com.otboss.todo.dto.UserDto;
import com.otboss.todo.dto.mapper.UserMapper;
import com.otboss.todo.model.Token;
import com.otboss.todo.model.User;
import com.otboss.todo.service.UserService;
import com.otboss.todo.utility.JWTUtility;

@RestController
@RequestMapping("api/v1/auth")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JWTUtility jwtUtility;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String login(@RequestBody UserDto credentials) {
        List<User> fetchedCreds;
        try {
            fetchedCreds = this.userService.findByEmail(credentials.getEmail());
            fetchedCreds.get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "invalid username or password", e);
        }
        BCrypt.Result passwordVerificationResult = BCrypt.verifyer().verify(credentials.getPassword().toCharArray(),
                fetchedCreds.get(0).getPassword().toCharArray());
        if (!passwordVerificationResult.verified) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "invalid username or password");
        }
        return this.jwtUtility.generateToken(new Token(fetchedCreds.get(0).getEmail()));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody UserDto credentials) {
        credentials.setPassword(BCrypt.withDefaults().hashToString(12, credentials.getPassword().toCharArray()));
        this.userService.save(UserMapper.toUser(credentials));
    }
}