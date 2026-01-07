package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskDbTest {
    private Task_db db;
    private String url = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=PostgreSQL";
    private String user = "sa";
    private String password = "";

    @BeforeEach
    void virtualDbSetUp() {
        db = new Task_db(url, user, password);

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = """
                    DROP TABLE IF EXISTS tasks;
                    CREATE TABLE tasks(
                    id SERIAL PRIMARY KEY, description VARCHAR(255) NOT NULL, is_done BOOLEAN NOT NULL)
                    """ ;
            var statement = conn.createStatement();
            var resultSet = statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addAndShowInfoTest() {
        Task task = new Task("Testing with Junit", true);

        db.addInfo(task);

        List<Task> arrayOfAddedTaskes = db.showInfo();

        Assertions.assertEquals(1, arrayOfAddedTaskes.size());
        Assertions.assertEquals("Testing with Junit", arrayOfAddedTaskes.stream()
                .map(item -> item.getDescription())
                .findFirst()
                .orElseThrow(AssertionError::new));
        Assertions.assertEquals(true, arrayOfAddedTaskes.stream()
                .map(item -> item.isIs_done())
                .findFirst()
                .orElseThrow(AssertionError::new));
    }
}
