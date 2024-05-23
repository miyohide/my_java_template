package com.github.miyohide.my_java_template;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class TodoServiceTransactionalTest {
  MockMvc mockMvc;

  @Autowired
  private TodoController todoController;

  @SpyBean
  private TodoRepository todoRepository;

  @Test
  public void testCreateTodo() throws Exception {
    // エラー発生設定
    doThrow(new RuntimeException()).when(todoRepository)
            .save(Mockito.any(Todo.class));

    this.mockMvc = MockMvcBuilders.standaloneSetup(todoController).build();

    // リクエスト発行
    MvcResult result = mockMvc.perform(
            post("/todos").contentType(MediaType.APPLICATION_FORM_URLENCODED).content("")).andExpect(status().isOk()).andReturn();
  }
}
