package com.personal.todo.service;

import com.personal.todo.entity.Todo;
import com.personal.todo.respository.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService{
    private final TodoRepo todoRepo;

    @Autowired
    public TodoServiceImpl(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }

    @Override
    public List<Todo> doGetAllTodos() {
        return todoRepo.findAll();
    }

    @Override
    public Todo doGetTodo(Long id) {
        Optional<Todo> todoOptional = todoRepo.findById(id);
        if (todoOptional.isPresent()) {
            return todoOptional.get();
        } else {
            throw new IllegalStateException(id + " not found");
        }
    }

    @Override
    public Todo doCreateTodo(Todo todo) {
        return todoRepo.save(todo);
    }

    @Override
    public Todo doUpdateTodo(Long id, Todo todo) {
        Optional<Todo> todoOptional = todoRepo.findById(id);
        if (todoOptional.isPresent()) {
            Todo previousTodo = todoOptional.get();

            previousTodo.setLabel(todo.getLabel());
            previousTodo.setDescription(todo.getDescription());
            previousTodo.setDueDate(todo.getDueDate());
            return todoRepo.save(previousTodo);
        } else {
            throw new IllegalStateException(id + " not found");
        }
    }

    @Override
    public void doDeleteTodo(Long id) {
        Optional<Todo> todoOptional = todoRepo.findById(id);
        if (todoOptional.isPresent()) {
            todoRepo.deleteById(id);
        } else {
            throw new IllegalStateException(id + " not found");
        }
    }
}
