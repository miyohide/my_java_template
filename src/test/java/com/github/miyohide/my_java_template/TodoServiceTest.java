package com.github.miyohide.my_java_template;

import com.google.common.collect.Iterables;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.hamcrest.MockitoHamcrest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
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
    verify(todoRepository, times(1)).findAllByOrderById();
  }

  @Test
  void getTodoById() {
    Todo todo1 = new Todo(1L, "title1", "body1", 1L, true);
    when(todoRepository.findById(1L)).thenReturn(Optional.of(todo1));

    var result = todoService.getTodoById("1");
    assertTrue(result.isPresent());
    assertThat(result.get(), Matchers.is(todo1));
    verify(todoRepository, times(1)).findById(1L);
  }

  @Test
  void createTodo() {
    User user1 = new User(1L, "name1", 0L);
    when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
    when(todoRepository.save(any(Todo.class))).thenAnswer(i -> i.getArguments()[0]);
    var result = todoService.createTodo("title1", "body1", 1L, false);

    assertNotNull(result);
    verify(todoRepository, times(1)).save(any(Todo.class));
    verify(userRepository, times(1)).save(
            MockitoHamcrest.argThat(
                    hasProperty("numberOfTodos", equalTo(1L))
            ));
  }

  @Test
  void deleteTodo() {
    Todo todo1 = new Todo(1L, "title1", "body1", 1L, true);
    User user1 = new User(1L, "name1", 2L);
    when(todoRepository.findById(1L)).thenReturn(Optional.of(todo1));
    when(userRepository.findById(1L)).thenReturn(Optional.of(user1));

    todoService.deleteTodo(1L);
    verify(todoRepository, times(1)).findById(1L);
    verify(userRepository, times(1)).findById(1L);
    verify(userRepository, times(1)).save(
            MockitoHamcrest.argThat(
                    hasProperty("numberOfTodos", equalTo(1L))
            )
    );
    verify(todoRepository, times(1)).deleteById(1L);
  }
}