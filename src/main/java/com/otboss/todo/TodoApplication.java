package com.otboss.todo;

import com.google.gson.Gson;
import com.otboss.todo.model.Token;
import com.otboss.todo.utility.Env;
import com.otboss.todo.utility.JWTUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@SpringBootApplication
public class TodoApplication {
	@Autowired
	JWTUtility jwtUtility;

	public static void main(String[] args) {
		// String jws =
		// Jwts.builder().setSubject("Joe").signWith(SignatureAlgorithm.HS256,
		// "key").compact();
		SpringApplication.run(TodoApplication.class, args);
	}

	@Bean
	public void demo() {
		// Token token = new Token("otsurfer6@gmail.com", 1619774692322L);
		// String tokenString = this.jwtUtility.generateToken(token);
		// System.out.println("TOKEN IS: " + tokenString);
		// Gson gson = new Gson();

		// System.out.println("TOOKKK: " +
		// gson.toJson(this.jwtUtility.parseToken(tokenString)));
	}

}
