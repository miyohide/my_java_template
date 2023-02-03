package com.github.miyohide.my_java_template;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
    @GetMapping("/")
    public String index() {
        return "welcome";
    }
}
