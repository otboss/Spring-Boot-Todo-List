package com.otboss.todo.utility;

import com.google.gson.Gson;

import com.otboss.todo.model.Token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtility {

    @Autowired
    Env env;

    public String generateToken(Token token) {
        Gson gson = new Gson();
        String tokenStringified = gson.toJson(token);
        return Jwts.builder().setPayload(tokenStringified).signWith(SignatureAlgorithm.HS512, this.env.jwtSecret)
                .compact();
    }

    public Token parseToken(String jwtToken) throws ExpiredJwtException, MalformedJwtException {
        Claims jwtPayload = Jwts.parser().setSigningKey(this.env.jwtSecret).parseClaimsJws(jwtToken).getBody();
        Gson gson = new Gson();
        Token token = gson.fromJson(jwtPayload.toString(), Token.class);
        if (System.currentTimeMillis() >= token.getExpiresAt()) {
            throw new ExpiredJwtException(null, null, "JWT token expired");
        }
        return token;
    }

}
