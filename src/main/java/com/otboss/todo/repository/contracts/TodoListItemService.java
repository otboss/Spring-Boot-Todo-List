package com.otboss.todo.repository.contracts;

import com.otboss.todo.model.TodoListItem;

public interface TodoListItemService {
    public void store(TodoListItem todoListItem);

    public TodoListItem getListItem(int id);

    public TodoListItem[] getListItems(long userId, String filter, long offset, long limit);

    public void delete(int id);
}
