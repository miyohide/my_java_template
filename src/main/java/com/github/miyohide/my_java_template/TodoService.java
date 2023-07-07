package com.github.miyohide.my_java_template;

import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

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

  public Todo saveTodo(Todo todo) {
    return todoRepository.save(todo);
  }
}
