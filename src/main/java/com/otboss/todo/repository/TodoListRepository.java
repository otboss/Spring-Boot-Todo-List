package com.otboss.todo.repository;

import com.otboss.todo.model.TodoListItem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepository extends JpaRepository<TodoListItem, Long> {
    // public List<TodoListItem> findByUserId(Long userId);

    public Page<TodoListItem> findByUserId(Long userId, Pageable pageRequest);
}
