package com.github.miyohide.my_java_template;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode(of = {"id"})
@ToString
public class Member {
  @Id private final Integer id;
  private final String name;
}
