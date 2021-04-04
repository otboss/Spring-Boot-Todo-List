package com.otboss.todo.repository;

import com.otboss.todo.model.TodoListItem;
import com.otboss.todo.repository.contracts.TodoListItemService;

public class TodoListRepository implements TodoListItemService {

    public TodoListRepository() {

    }

    @Override
    public void store(TodoListItem t) {
        // TODO Auto-generated method stub
    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub
    }

    @Override
    public TodoListItem getListItem(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TodoListItem[] getListItems(long userId, String filter, long offset, long limit) {
        // TODO Auto-generated method stub
        return null;
    }

}
