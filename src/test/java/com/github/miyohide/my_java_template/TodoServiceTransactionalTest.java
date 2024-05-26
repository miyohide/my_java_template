package com.github.miyohide.my_java_template;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoServiceTransactionalTest {
  private MockMvc mockMvc;

  @Autowired
  private TodoController todoController;
  @SpyBean
  private TodoRepository todoRepository;
  @Autowired
  private UserRepository userRepository;

  @Test
  @Sql(value = "classpath:/fixtures/init_users.sql")
  public void testCreateTodo() throws Exception {
    // エラー発生設定
    doThrow(new RuntimeException("error message")).when(todoRepository)
            .save(Mockito.any(Todo.class));

    mockMvc = MockMvcBuilders.standaloneSetup(todoController).build();
    User user = userRepository.findById(999L).get();
    assertEquals(0L, user.getNumberOfTodos());
    // リクエスト発行
    mockMvc.perform(
            post("/todo")
                    .param("title", "title1")
                    .param("body", "body1")
                    .param("userId", "999")
                    .param("completed", "true")
            )
            .andExpect(status().isOk()).andReturn();

    user = userRepository.findById(999L).get();
    assertEquals(0L, user.getNumberOfTodos());
  }
}
