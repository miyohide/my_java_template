package com.github.miyohide.my_java_template;

import org.springframework.stereotype.Service;

@Service
public class TodoService {
  private final TodoRepository todoRepository;

  public TodoService(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  public Iterable<Todo> getAllTodo() {
    return todoRepository.findAll();
  }
}
