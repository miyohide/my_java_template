package com.github.miyohide.my_java_template;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TodoService {
  private final TodoRepository todoRepository;
  private final UserRepository userRepository;

  public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
    this.todoRepository = todoRepository;
    this.userRepository = userRepository;
  }

  public Iterable<Todo> getAllTodo() {
    return todoRepository.findAllByOrderById();
  }

  public Optional<Todo> getTodoById(String id) {
    return todoRepository.findById(Long.parseLong(id));
  }

  @Transactional
  public Todo createTodo(String title, String body, Long userId, boolean completed) {
    Todo todo = new Todo(null, title, body, userId, completed);
    User user = userRepository.findById(userId).orElse(null);
    if (user == null) {
      return null;
    }
    user.setNumberOfTodos(user.getNumberOfTodos() + 1);
    userRepository.save(user);

    return todoRepository.save(todo);
  }

  public void deleteTodo(Long id) {
    Todo todo = todoRepository.findById(id).orElse(null);
    if (todo != null) {
      User user = userRepository.findById(todo.getUserId()).orElse(null);
      if (user != null) {
        user.setNumberOfTodos(user.getNumberOfTodos() - 1);
        userRepository.save(user);
      }
      todoRepository.deleteById(id);
    }
  }
}
