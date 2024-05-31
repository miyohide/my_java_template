package com.github.miyohide.my_java_template;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoServiceTransactionalTest {

  @Autowired
  private DataSource dataSource;

  @Autowired
  private TodoController todoController;
  @SpyBean
  private TodoRepository todoRepository;
  @Autowired
  private UserRepository userRepository;

  @BeforeEach
  public void setUp() throws Exception {
    try (
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()
    ) {
      statement.execute("INSERT INTO users VALUES (999, 'test user 999', 0)");
    }
  }

  @AfterEach
  public void tearDown() throws Exception {
    try (
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ) {
      statement.execute("DELETE FROM users WHERE id = 999");
    }
  }

  @Test
  public void testCreateTodo() throws Exception {
    // エラー発生設定
    doThrow(new RuntimeException("error message")).when(todoRepository)
            .save(Mockito.any(Todo.class));

    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(todoController).build();
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
