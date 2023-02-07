package com.github.miyohide.my_java_template;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
@Table(name = "MEMBERS")
public class Member {
  @Id private Integer id;
  private String name;
}
