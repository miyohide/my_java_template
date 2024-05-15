package com.github.miyohide.my_java_template;

import com.google.common.collect.Iterables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TodoServiceTest {
  @InjectMocks
  private TodoService todoService;

  @Mock
  private TodoRepository todoRepository;

  @Mock
  private UserRepository userRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getAllTodo() {
    List<Todo> todos = new ArrayList<>();
    todos.add(new Todo(1L, "title1", "body1", 1L, true));
    todos.add(new Todo(2L, "title2", "body2", 2L, true));
    when(todoRepository.findAllByOrderById()).thenReturn(todos);

    var results = todoService.getAllTodo();
    assertEquals(2, Iterables.size(results));
    assertAll(() -> {
      verify(todoRepository, times(1));
    });
  }

  @Test
  void getTodoById() {
  }

  @Test
  void createTodo() {
  }

  @Test
  void deleteTodo() {
  }
}