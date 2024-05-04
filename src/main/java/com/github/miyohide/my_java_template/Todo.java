package com.github.miyohide.my_java_template;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode(of = {"id"})
@ToString
@Table(name = "todos")
public class Todo {
  @Id private final Long id;
  private final String title;
  private final String body;
  private final Long userId;
  private final boolean completed;
}
