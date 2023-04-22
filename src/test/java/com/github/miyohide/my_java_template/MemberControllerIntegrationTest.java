package com.github.miyohide.my_java_template;

import static org.hamcrest.Matchers.containsString;

import com.github.miyohide.my_java_template.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = MyJavaTemplateApplication.class)
@AutoConfigureMockMvc
public class MemberControllerIntegrationTest {
  @Autowired private MockMvc mvc;

  @Test
  void test() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string(containsString("All members page")));
  }
}
