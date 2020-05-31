package com.ustundag.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ustundag.service.TodoService;
import com.ustundag.service.dto.TodoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
//@WebMvcTest(controllers = TodoController.class)
@AutoConfigureMockMvc
@SpringBootTest
class TodoControllerTest {
    private static final String CONTENT_TYPE = "application/json";
    private static final String URI_PREFIX   = "/api/v1/todo/";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private TodoService todoService;
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

    //TODO todoService.addTodo(todoDto) returns null
    @Test
    void whenPostValidInput_thenReturns200OK() throws Exception {
        String description = "Test whenPostValidInput_thenReturns200OK";
        Long id = 1L;

        // given

        // when
        TodoDTO todoDTO = TodoDTO.builder().id(id).description(description).build();
        when(todoService.addTodo(any(TodoDTO.class))).thenReturn(todoDTO);

        // then
        ResultActions actions =  mockMvc.perform(post(URI_PREFIX)
                                        .contentType(CONTENT_TYPE)
                                        .content(objectMapper.writeValueAsString(todoDTO)));

        ArgumentCaptor<TodoDTO> captor = ArgumentCaptor.forClass(TodoDTO.class);
        verify(todoService, times(1)).addTodo(captor.capture());
        assertThat(captor.getValue().getDescription()).isEqualTo(description);
        actions.andExpect(status().isOk());
    }

    @Test
    void whenRequestAllTodos_thenReturns200OK() throws Exception {
        // given
        TodoDTO todoDTO = TodoDTO.builder().description("Test whenRequestAllTodos_thenReturns200OK").build();
        when(todoService.getAllTodos()).thenReturn(Arrays.asList(todoDTO));

        // when
        MvcResult mvcResult = mockMvc.perform(get(URI_PREFIX).accept(CONTENT_TYPE)).andReturn();

        // then
        String responseBody = mvcResult.getResponse().getContentAsString();
        verify(todoService, times(1)).getAllTodos();
        assertThat(objectMapper.writeValueAsString(Arrays.asList(todoDTO)))
                .isEqualToIgnoringWhitespace(responseBody);
    }

}