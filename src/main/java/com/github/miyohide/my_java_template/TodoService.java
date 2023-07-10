package com.github.miyohide.my_java_template;

import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TodoService {
  private final TodoRepository todoRepository;

  public TodoService(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  @Retryable
  public Iterable<Todo> getAllTodo() {
    return todoRepository.findAll();
  }

  public Optional<Todo> getTodoById(String id) {
    return todoRepository.findById(Long.parseLong(id));
  }

  public Todo saveTodo(Todo todo) {
    return todoRepository.save(todo);
  }
}
