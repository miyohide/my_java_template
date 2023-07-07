package com.github.miyohide.my_java_template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class MyJavaTemplateApplication {

  public static void main(String[] args) {
    SpringApplication.run(MyJavaTemplateApplication.class, args);
  }
}
