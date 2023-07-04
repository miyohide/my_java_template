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
    // 新規作成
    var before_insert_count = todoRepository.count();
    Todo t1 = todoRepository.save(new Todo(null, "title1", "body1"));
    assertNotNull(t1.getId());
    // 新規作成のため、件数は増えること
    assertEquals(before_insert_count + 1, todoRepository.count());
    // 検索
    Optional<Todo> t2 = todoRepository.findById(t1.getId());
    assertTrue(t2.isPresent());
    t2.ifPresent(todo -> assertEquals(t1.getTitle(), todo.getTitle()));
    // 更新
    var before_update_count = todoRepository.count();
    Todo t3 = new Todo(t1.getId(), "updated title", "updated body");
    Todo updatedTodo = todoRepository.save(t3);
    // 更新のため、件数は増えないこと
    assertEquals(before_update_count, todoRepository.count());
    // 更新されていることの確認
    assertEquals(t3.getTitle(), updatedTodo.getTitle());
    assertEquals(t3.getBody(), updatedTodo.getBody());
  }
}
