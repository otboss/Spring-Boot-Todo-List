package com.otboss.todo.controller.list;

import java.util.List;
import java.util.Optional;

import com.otboss.todo.model.TodoListItem;
import com.otboss.todo.model.Token;
import com.otboss.todo.model.User;
import com.otboss.todo.repository.TodoListRepository;
import com.otboss.todo.repository.UserRepository;
import com.otboss.todo.utility.JWTUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@RestController
@RequestMapping("api/v1/list")
public class TodoListController {

    @Autowired
    private TodoListRepository todoListRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTUtility jwtUtility;

    private User getUserFromToken(String token) {
        Token parsedToken;
        try {
            if (!token.startsWith("Bearer")) {
                throw new MalformedJwtException("invalid token provided");
            }
            parsedToken = this.jwtUtility.parseToken(token.substring(7));
        } catch (ExpiredJwtException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "token expired", e);
        } catch (MalformedJwtException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "invalid token provided", e);
        }
        List<User> user = this.userRepository.findByEmail(parsedToken.getEmail());
        try {
            user.get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "user not found for email provided", e);
        }
        return user.get(0);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createListItem(@RequestBody TodoListItem listItem,
            @RequestHeader(name = "Authorization") String token) {
        User user = this.getUserFromToken(token);
        TodoListItem createdListItem = new TodoListItem(listItem.getEntry(), user.getId());
        this.todoListRepository.save(createdListItem);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Page<TodoListItem> readList(@RequestParam String filter, @RequestParam String offset,
            @RequestParam String limit, @RequestHeader(name = "Authorization") String token)
            throws ResponseStatusException {
        User user = this.getUserFromToken(token);
        Integer parsedOffset;
        Integer parsedLimit;
        try {
            parsedOffset = Integer.parseInt(offset);
            parsedLimit = Integer.parseInt(limit);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "invalid field provided");
        }
        if (parsedLimit > 100) {
            parsedLimit = 100;
        }
        return this.todoListRepository.findByUserId(user.getId(),
                PageRequest.of(parsedOffset, parsedLimit, Sort.by("timestamp")));
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public void updateListItem(@RequestBody long id, @RequestBody TodoListItem item,
            @RequestHeader(name = "Authorization") String token) throws ResponseStatusException {
        User user = this.getUserFromToken(token);
        Optional<TodoListItem> listItem = this.todoListRepository.findById(id);
        if (listItem == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "list item not found");
        }
        TodoListItem listItemParsed = listItem.get();
        if (listItemParsed.getUserId() != user.getId()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "unauthorized");
        }
        listItemParsed.setEntry(listItemParsed.getEntry());
        listItemParsed.setChecked(listItemParsed.getChecked());
        this.todoListRepository.save(listItemParsed);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public void deleteListItem(@RequestBody long id, @RequestHeader(name = "Authorization") String token) {
        User user = this.getUserFromToken(token);
        Optional<TodoListItem> listItem = this.todoListRepository.findById(id);
        if (listItem == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "list item not found");
        }
        TodoListItem listItemParsed = listItem.get();
        if (listItemParsed.getUserId() != user.getId()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "");
        }
        this.todoListRepository.deleteById(listItemParsed.getId());
    }

}