package com.personal.todo.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TodoDTO {
    private Long id;
    private String label;
    private String description;
    private LocalDate dueDate;
}
