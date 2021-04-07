package com.otboss.todo.model;

import java.util.Date;

public class Token {

    private String email;
    private long issuedAt = (new Date(System.currentTimeMillis())).getTime();
    private long expiresAt = this.issuedAt + (long) 2.88e+7;

    public Token(String email, long expiresAt) {
        this.email = email;
        this.expiresAt = expiresAt;
    }

    public String getEmail() {
        return this.email;
    }

    public long getExpiresAt() {
        return this.expiresAt;
    }

    public long getIssuedAt() {
        return this.issuedAt;
    }

}
