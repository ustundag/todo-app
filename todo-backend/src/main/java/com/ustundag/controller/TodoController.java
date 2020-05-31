package com.ustundag.controller;

import com.ustundag.service.TodoService;
import com.ustundag.service.dto.TodoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/todo")
public class TodoController {

    //@Autowired
    //private TodoRepository todoRepository;

    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<TodoDTO> getAllTodos() {
        //return todoRepository.findAll();
        return todoService.getAllTodos();
    }

    @GetMapping("/{id}")
    public TodoDTO getTodo(@PathVariable long id) {
        //return todoRepository.findById(id).get();
        return todoService.getTodo(id);
    }

    @PostMapping
    public ResponseEntity<TodoDTO> addTodo(@RequestBody TodoDTO todoDto) {
        TodoDTO todoDTOCreated = todoService.addTodo(todoDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(todoDTOCreated.getId()).toUri();
        todoDTOCreated.setUri(uri);
        return ResponseEntity.ok(todoDTOCreated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.ok().build();
    }

}
