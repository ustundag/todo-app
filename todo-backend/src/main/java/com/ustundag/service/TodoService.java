package com.ustundag.service;

import com.ustundag.entity.Todo;
import com.ustundag.repo.TodoRepository;
import com.ustundag.service.dto.TodoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService implements ITodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public List<TodoDTO> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        List<TodoDTO> todoDTOs = new ArrayList<>();
        todos.forEach(item -> {
            TodoDTO todoDTO = new TodoDTO(item.getId(),
                    item.getDescription(),
                    item.getDeadline(),
                    item.isCompleted()
            );
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(todoDTO.getId()).toUri();
            todoDTO.setUri(uri);
            todoDTOs.add(todoDTO);
        });
        return todoDTOs;
    }

    @Override
    public TodoDTO getTodo(Long id) {
        Todo todo = todoRepository.getOne(id);
        TodoDTO todoDTO = new TodoDTO(todo.getId(),
                todo.getDescription(),
                todo.getDeadline(),
                todo.isCompleted());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build(todoDTO);
        todoDTO.setUri(uri);
        return todoDTO;
    }

    @Override
    public TodoDTO addTodo(TodoDTO todoDTO) {
        Assert.notNull(todoDTO.getDescription(), "Description can not be null!");
        Todo todo = new Todo();
        todo.setDescription(todoDTO.getDescription());
        todo.setDeadline(todoDTO.getDeadline());
        todo.setCompleted(todoDTO.isCompleted());
        Todo todo_saved = todoRepository.save(todo);
        todoDTO.setId(todo_saved.getId());
        return todoDTO;
    }

    @Override
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

}
