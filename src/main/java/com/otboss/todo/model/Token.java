package com.otboss.todo.model;

import java.util.Date;

public class Token {

    private String email;
    private Long issuedAt = (new Date(System.currentTimeMillis())).getTime();
    private Long expiresAt = this.issuedAt + (long) 2.88e+7;

    public Token(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public Long getExpiresAt() {
        return this.expiresAt;
    }

    public Long getIssuedAt() {
        return this.issuedAt;
    }

}
