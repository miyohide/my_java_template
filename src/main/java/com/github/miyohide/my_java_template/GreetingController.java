package com.github.miyohide.my_java_template;

import java.util.concurrent.atomic.AtomicLong;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class GreetingController {
  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @GetMapping("/greeting")
  public String greeting(
      @RequestParam(value = "name", defaultValue = "World") String name, Model model) {
    log.info("GET /greeting start");
    model.addAttribute(
        "name", new Greeting(counter.incrementAndGet(), String.format(template, name)).toString());
    log.info("GET /greeting end");
    return "greeting";
  }
}
