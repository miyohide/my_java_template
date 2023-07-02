package com.github.miyohide.my_java_template;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TodoRepositoryTest {
  @Autowired private TodoRepository todoRepository;

  @Test
  void test() {
    Todo t1 = todoRepository.save(new Todo(null, "title1", "body1"));
    assertNotNull(t1.getId());
    Optional<Todo> t2 = todoRepository.findById(t1.getId());
    assertTrue(t2.isPresent());
    t2.ifPresent(todo -> assertEquals(t1.getTitle(), todo.getTitle()));
  }
}
