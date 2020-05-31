package com.ustundag.service;

import com.ustundag.service.dto.TodoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TodoServiceIntegrationTest {
    @Autowired
    private TodoService todoService;

    @Test
    void addTodo() {
        TodoDTO todoDTO = TodoDTO.builder().description("Test TodoDTO").isCompleted(false).build();
        TodoDTO todoDTO_returned = todoService.addTodo(todoDTO);
        assertTrue(todoDTO_returned.getId() > 0L);
    }

    @Test
    void addTodoNotNullException() {
        TodoDTO todoDTO = new TodoDTO();
        assertThrows(IllegalArgumentException.class, () -> {
            todoService.addTodo(todoDTO);
        });
    }

}
