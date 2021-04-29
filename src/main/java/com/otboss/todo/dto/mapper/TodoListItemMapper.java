package com.otboss.todo.dto.mapper;

import com.otboss.todo.dto.TodoListItemDto;
import com.otboss.todo.model.TodoListItem;

public class TodoListItemMapper {
    public static TodoListItemDto toTodoListItemDto(TodoListItem todoListItem) {
        TodoListItemDto listItem = new TodoListItemDto();
        listItem.setEntry(todoListItem.getEntry());
        listItem.setChecked(todoListItem.getChecked());
        listItem.setTimestamp(todoListItem.getTimestamp());
        return listItem;
    }
}
