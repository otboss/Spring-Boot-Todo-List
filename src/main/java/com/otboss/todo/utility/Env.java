package com.otboss.todo.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Env {

    @Value("jwt.secret")
    public String jwtSecret;

}
