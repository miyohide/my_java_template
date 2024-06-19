package com.github.miyohide.my_java_template;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {
    @Autowired
    private DataSource dataSource;

    @BeforeEach
    public void setUp() throws Exception {
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO users VALUE (9999, 'test user 999', 0)");
            statement.execute("INSERT INTO todos VALUES (9999, 'title', 'body', 9999, false)");
        }
    }

    @AfterEach
    public void tearDown() throws Exception {
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();) {
                    statement.execute("DELETE FROM todos WHERE id = 9999");
                    statement.execute("DELETE FROM users WHERE id = 9999");
        }
    }

    @Test
    void testCreateTodo(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/todos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("todos/index"));
    }

    @Test
    void testDetails(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/todos/999"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(view().name("todos/show"));
    }

    @Test
    void testIndex(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/todos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("todos/index"));
    }

    @Test
    void testNewTodo() {

    }
}
