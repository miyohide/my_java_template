package com.github.miyohide.my_java_template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
