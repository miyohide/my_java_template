package com.github.miyohide.my_java_template;

import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TodoController {
  private final TodoService todoService;
  private final UserService userService;

  public TodoController(TodoService todoService, UserService userService) {
    this.todoService = todoService;
    this.userService = userService;
  }

  @GetMapping("/todos")
  public String index(Model model) {
    Iterable<Todo> todos = todoService.getAllTodo();
    model.addAttribute("todos", todos);
    return "todos/index";
  }

  @GetMapping("/todos/{id}")
  public String details(@PathVariable String id, Model model) {
    Optional<Todo> todo = todoService.getTodoById(id);
    if (todo.isPresent()) {
      model.addAttribute("todo", todo.get());
      return "todos/show";
    } else {
      // エラーメッセージの出力
      model.addAttribute("error_message", "ID = " + id + "のTodoが見つかりません。");
      model.addAttribute("alertClass", "alert-danger");
      // todos/indexページのデータを設定する
      Iterable<Todo> todos = todoService.getAllTodo();
      model.addAttribute("todos", todos);
      return "todos/index";
    }
  }

  @GetMapping("/todos/new")
  public String newTodo(Model model) {
    model.addAttribute("todo", new Todo(null, "", "", null, false));
    Map<String, Long> userMap = userService.getUserMap();
    model.addAttribute("userMap", userMap);
    return "todos/new";
  }

  @PostMapping("/todo")
  public String createTodo(@ModelAttribute Todo todo) {
    try {
      todoService.createTodo(todo.getTitle(), todo.getBody(), todo.getUserId(), todo.isCompleted());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "redirect:/todos";
  }

  @DeleteMapping("/todos/{id}")
  public String deleteTodo(@PathVariable String id) {
    todoService.deleteTodo(Long.parseLong(id));
    return "redirect:/todos";
  }
}
