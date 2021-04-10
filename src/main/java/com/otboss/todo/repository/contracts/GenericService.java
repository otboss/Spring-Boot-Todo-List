package com.otboss.todo.repository.contracts;

import java.util.Optional;

public interface GenericService<T> {
    public void store(T t);

    public Optional<T> retrieve(int id);

    public T[] search(String name);

    public T delete(int id);
}
