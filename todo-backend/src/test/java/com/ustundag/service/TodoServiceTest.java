package com.ustundag.service;

import com.ustundag.entity.Todo;
import com.ustundag.repo.TodoRepository;
import com.ustundag.service.dto.TodoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TodoServiceTest {
    private static final String URI_PREFIX = "/api/v1/todo/";
    @InjectMocks
    private TodoService todoService;
    @Mock
    private TodoRepository todoRepository;
    @Mock
    private MockHttpServletRequest request;

    // Mock HTTPServlet to allow Service object to create URI links.
    @BeforeEach
    public void setup() {
        this.request = new MockHttpServletRequest();
        this.request.setScheme("http");
        this.request.setServerName("localhost");
        this.request.setServerPort(8080);
        this.request.setRequestURI(URI_PREFIX);
    }

    @Test
    void addTodo() {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setDescription("Test TodoDTO");
        todoDTO.setCompleted(false);
        todoDTO.setId(1L);

        Todo todoMock = mock(Todo.class);
        when(todoMock.getId()).thenReturn(1L);
        when(todoRepository.save(any(Todo.class)))
                .thenReturn(todoMock);

        TodoDTO todoDTO_returned = todoService.addTodo(todoDTO);
        assertEquals(todoDTO.getDescription(),
                todoDTO_returned.getDescription());
        assertEquals(todoDTO.getId(), 1L);
    }

    @Test
    void getTodo() {
        Long id = 1L;
        this.request.setRequestURI(URI_PREFIX + id);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(this.request));

        Todo todoMock = mock(Todo.class);
        when(todoRepository.getOne(any(Long.class))).thenReturn(todoMock);
        when(todoMock.getId()).thenReturn(id);

        TodoDTO todoDTO = todoService.getTodo(id);
        assertEquals(todoDTO.getId(), 1L);
    }

    @Test
    void getAllTodos() {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(this.request));

        List<Todo> todoList = new ArrayList<Todo>();
        todoList.add(Todo.builder().id(1L).description("First  todo in @Test getAllTodos()...").build());
        todoList.add(Todo.builder().id(2L).description("Second todo in @Test getAllTodos()...").build());

        when(todoRepository.findAll()).thenReturn(todoList);

        List<TodoDTO> todoDTOS = todoService.getAllTodos();
        assertEquals(todoDTOS.size(), 2);
        assertEquals(todoDTOS.get(1).getId(), 2L);
    }

    @Test
    void deleteTodo() {
        Long id = 1L;
        todoService.deleteTodo(id);
        verify(todoRepository, times(1)).deleteById(eq(id));
    }

}