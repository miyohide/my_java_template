package com.github.miyohide.my_java_template;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@Data
@EqualsAndHashCode(of = {"id"})
@ToString
@Table(name = "users")
public class User {
    @Id private Long id;
    private String name;
    private Long number_of_todos;
}
