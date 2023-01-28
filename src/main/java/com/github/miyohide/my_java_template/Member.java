package com.github.miyohide.my_java_template;

import org.springframework.data.annotation.Id;

public class Member {
  @Id private final Integer id;
  private final String name;

  public Member(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
