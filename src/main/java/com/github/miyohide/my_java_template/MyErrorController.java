package com.github.miyohide.my_java_template;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
public class MyErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(Model model) {
        model.addAttribute("errorDateTime", LocalDateTime.now());
        return "errors/error";
    }
}
