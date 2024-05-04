package com.github.miyohide.my_java_template;

import java.util.Optional;
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

  public Optional<Todo> getTodoById(String id) {
    return todoRepository.findById(Long.parseLong(id));
  }

  public Todo saveTodo(String title, String body, Long userId) {
    Todo todo = new Todo(null, title, body, userId, false);
    return todoRepository.save(todo);
  }
}
