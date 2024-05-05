package com.github.miyohide.my_java_template;

import java.util.Optional;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

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

  public Todo createTodo(String title, String body, Long userId, boolean completed) throws Exception {
    Todo todo = new Todo(null, title, body, userId, completed);
    Optional<User> optionalUser = userRepository.findById(userId);
    if (optionalUser.isEmpty()) {
      throw new Exception("User is Empty");
    }
    User user = optionalUser.get();
    user.setNumberOfTodos(user.getNumberOfTodos() + 1);
    userRepository.save(user);

    return todoRepository.save(todo);
  }
}
