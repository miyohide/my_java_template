package com.github.miyohide.my_java_template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class TodoController {
  private static final Logger log = LoggerFactory.getLogger(TodoController.class);

  private final TodoService todoService;

  public TodoController(TodoService todoService) {
    this.todoService = todoService;
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
    model.addAttribute("todo", new Todo(null, "", ""));
    return "todos/new";
  }

  @PostMapping("/todo")
  public String createTodo(@ModelAttribute Todo todo) {
    todoService.saveTodo(todo);
    return "redirect:/todos";
  }
}
