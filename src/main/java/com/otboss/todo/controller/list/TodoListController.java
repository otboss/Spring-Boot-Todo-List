package com.otboss.todo.controller.list;

import com.otboss.todo.model.TodoListItem;
import com.otboss.todo.repository.TodoListRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/list")
public class TodoListController {

    @Autowired
    private TodoListRepository todoListRepository;

    @GetMapping()
    public TodoListItem[] getList() {
        // return this.todoListRepository.getListItems(userId, filter, offset, limit);
        return new TodoListItem[] {};
    }

    @PutMapping()
    public void updateList(TodoListItem item) {
        // return new String[] {""};
    }

}