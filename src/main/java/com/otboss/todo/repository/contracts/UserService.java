package com.otboss.todo.repository.contracts;

import com.otboss.todo.model.User;

public interface UserService {
    public void store(User user);

    public User getUser(int id);

    public void delete(int id);
}
