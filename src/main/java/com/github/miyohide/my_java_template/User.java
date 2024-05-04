package com.github.miyohide.my_java_template;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode(of = {"id"})
@ToString
@Table(name = "users")
public class User {
    @Id private final Long id;
    private final String name;
    private final Long number_of_todos;
}
