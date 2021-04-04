package com.otboss.todo.repository.contracts;

public interface GenericService<T> {
    public void store(T t);

    public T retrieve(int id);

    public T[] search(String name);

    public T delete(int id);
}
