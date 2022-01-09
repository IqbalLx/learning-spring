package com.personal.todo.controller;

import com.personal.todo.entity.Todo;
import com.personal.todo.entity.TodoDTO;
import com.personal.todo.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
    @Autowired
    private ModelMapper modelMapper;

    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<TodoDTO> getAllTodos() {
        return todoService.doGetAllTodos().stream().map(todo -> modelMapper.map(todo, TodoDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDTO> getTodo(@PathVariable(name = "id") Long id) {
        Todo todo = todoService.doGetTodo(id);
        TodoDTO todoResponse = modelMapper.map(todo, TodoDTO.class);

        return ResponseEntity.ok().body(todoResponse);
    }

    @PostMapping
    public ResponseEntity<TodoDTO> createTodo(@RequestBody TodoDTO todoDto) {
        Todo todoRequest = modelMapper.map(todoDto, Todo.class);

        Todo todo = todoService.doCreateTodo(todoRequest);

        TodoDTO todoResponse = modelMapper.map(todo, TodoDTO.class);

        return ResponseEntity.ok().body(todoResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoDTO> updateTodo(@PathVariable(name = "id") Long id, @RequestBody TodoDTO todoDto) {
        Todo todoRequest = modelMapper.map(todoDto, Todo.class);

        Todo todo = todoService.doUpdateTodo(id, todoRequest);

        TodoDTO todoResponse = modelMapper.map(todo, TodoDTO.class);

        return ResponseEntity.ok().body(todoResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable(name = "id") Long id) {
        todoService.doDeleteTodo(id);

        return ResponseEntity.ok().body("Post deleted!");
    }

}
