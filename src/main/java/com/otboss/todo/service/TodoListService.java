package com.otboss.todo.service;

import com.otboss.todo.dto.TodoListItemDto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListService extends JpaRepository<TodoListItemDto, Long> {

}
