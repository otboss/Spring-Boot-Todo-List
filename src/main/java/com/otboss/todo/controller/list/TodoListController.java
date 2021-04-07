package com.otboss.todo.controller.list;

import com.otboss.todo.model.TodoListItem;
import com.otboss.todo.repository.TodoListRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/list")
public class TodoListController {

    @Autowired
    private TodoListRepository todoListRepository;

    @PostMapping()
    public void createListItem(@RequestBody TodoListItem listItem) {
        // TODO: Save new list item for user
    }

    @GetMapping()
    public TodoListItem[] readList(@RequestParam String filter, @RequestParam String offset,
            @RequestParam String limit) {
        // TODO: get list items from database and return to to user
        // return this.todoListRepository.getListItems(userId, filter, offset, limit);
        return new TodoListItem[] {};
    }

    @PutMapping()
    public void updateListItem(long id, TodoListItem item) {
        // return new String[] {""};
    }

    @DeleteMapping()
    public void deleteListItem(@RequestBody long id) {

    }

}