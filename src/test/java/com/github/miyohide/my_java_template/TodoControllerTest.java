package com.github.miyohide.my_java_template;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {
    @Test
    void testCreateTodo(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/todos"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(view().name("todos/index"));
    }

    @Test
    @Sql("/init-todo.sql")
    void testDetails(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/todos/100"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(view().name("todos/show"));
    }

    @Test
    void testIndex() {

    }

    @Test
    void testNewTodo() {

    }
}
