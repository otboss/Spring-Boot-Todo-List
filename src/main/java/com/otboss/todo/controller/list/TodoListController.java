package com.otboss.todo.controller.list;

import java.util.Optional;

import com.otboss.todo.model.TodoListItem;
import com.otboss.todo.repository.TodoListRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/list")
public class TodoListController {

    @Autowired
    private TodoListRepository todoListRepository;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createListItem(@RequestBody TodoListItem listItem) {
        // TODO: Implement user authentication
        // TODO: Save new list item for user
    }

    @GetMapping()
    public TodoListItem[] readList(@RequestParam String filter, @RequestParam String offset,
            @RequestParam String limit) {
        // TODO: Implement user authentication here
        // TODO: get list items from database and return to to user
        // return this.todoListRepository.getListItems(userId, filter, offset, limit);
        return new TodoListItem[] {};
    }

    @PutMapping()
    public void updateListItem(@RequestBody long id, @RequestBody TodoListItem item) throws NullPointerException {
        // TODO: Implement user authentication here
        TodoListItem listItem = (this.todoListRepository.findById(id)).get();
        listItem.setEntry(listItem.getEntry());
        listItem.setChecked(listItem.getChecked());
        this.todoListRepository.save(listItem);
    }

    @DeleteMapping()
    public void deleteListItem(@RequestBody long id) {
        // TODO: Implement user authentication here
    }

}