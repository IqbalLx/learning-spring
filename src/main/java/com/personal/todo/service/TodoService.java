package com.personal.todo.service;

import com.personal.todo.entity.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> doGetAllTodos();

    Todo doGetTodo(Long id);

    Todo doCreateTodo(Todo todo);

    Todo doUpdateTodo(Long id, Todo todo);

    void doDeleteTodo(Long id);

}
