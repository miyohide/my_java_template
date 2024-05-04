package com.github.miyohide.my_java_template;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@Data
@EqualsAndHashCode(of = {"id"})
@ToString
@Table(name = "todos")
public class Todo {
  @Id private Long id;
  private String title;
  private String body;
  private Long userId;
  private boolean completed;
}
