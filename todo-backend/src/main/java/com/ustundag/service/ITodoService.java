package com.ustundag.service;

import com.ustundag.service.dto.TodoDTO;

import java.util.List;

public interface ITodoService {
    TodoDTO addTodo(TodoDTO todoDTO);

    TodoDTO getTodo(Long id);

    void deleteTodo(Long id);

    List<TodoDTO> getAllTodos();
}
