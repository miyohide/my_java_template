package com.github.miyohide.my_java_template;

import jakarta.validation.constraints.Size;
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

  @Size(min = 1, max = 50)
  private String title;

  @Size(min = 1, max = 1000)
  private String body;

  private Long userId;
  private boolean completed;
}
