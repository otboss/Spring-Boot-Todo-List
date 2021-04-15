package com.otboss.todo.constants;

import com.otboss.todo.model.Token;
import com.otboss.todo.model.User;
import com.otboss.todo.utility.JWTUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestConstants {

    @Autowired
    JWTUtility jwtUtility;

    public User testUser = new User("example@example.com", "password");
    public String jwtToken = this.jwtUtility.generateToken(new Token(this.testUser.getEmail()));

}
