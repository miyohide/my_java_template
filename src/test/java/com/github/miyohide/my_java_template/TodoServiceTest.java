package com.github.miyohide.my_java_template;

import com.google.common.collect.Iterables;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
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
    Todo todo1 = new Todo(1L, "title1", "body1", 1L, true);
    Todo todo2 = new Todo(2L, "title2", "body2", 2L, true);
    todos.add(todo1);
    todos.add(todo2);
    when(todoRepository.findAllByOrderById()).thenReturn(todos);

    var results = todoService.getAllTodo();
    assertEquals(2, Iterables.size(results));
    assertThat(results, Matchers.contains(todo1, todo2));
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