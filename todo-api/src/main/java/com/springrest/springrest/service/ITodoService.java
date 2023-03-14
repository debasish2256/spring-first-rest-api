package com.springrest.springrest.service;

import java.util.List;

import com.springrest.springrest.model.Todo;

public interface ITodoService {
    public List<Todo> findAll();

    public Todo findById(int id);

    public void addTodo(Todo todo);

    public void deleteTodo(int id);

    public void updateTodo(int id,Todo newTodo);
}
