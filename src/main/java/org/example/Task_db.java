package org.example;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Task_db implements Taskable{
    private String url;
    private String user;
    private String password;

    Task_db() {
        Yaml yaml = new Yaml();

        try (InputStream inputStream = Task_db.class
                .getClassLoader()
                .getResourceAsStream("application.yaml")) {
            if (inputStream == null) throw new RuntimeException("File application.yaml does not found");

            Config config = yaml.loadAs(inputStream, Config.class);
            if (!config.database.isValid()) throw new RuntimeException("Config is empty");
            this.url = config.database.url;
            this.user = config.database.user;
            this.password = config.database.password;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Config`s error", e);
        }

    }
    Task_db(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public void addInfo(Task task) {
        String sql = "INSERT INTO tasks (description, is_done) VALUES(?, ?)";
        try (Connection conn = getConnection();
             var preparesStatement = conn.prepareStatement(sql)) {
            System.out.println("Trying to connect");

            preparesStatement.setString(1, task.getDescription());
            preparesStatement.setBoolean(2, task.isIs_done());

            preparesStatement.executeUpdate();
            System.out.println("Task was added succesfully");

        } catch(SQLException error) {
            System.out.println(error.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<Task> showInfo() {
        List<Task> arrayOfTasks = new ArrayList<>();
        String sql = """
                SELECT * FROM tasks
                ORDER BY id;""";
        try (Connection conn = getConnection();
             var statement = conn.createStatement();
             var resultSet = statement.executeQuery(sql)) {
            // type of resultSet is ResultSet

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                boolean is_done = resultSet.getBoolean("is_done");

                Task task = new Task(description, is_done);
                task.setId(id);

                arrayOfTasks.add(task);
            }
        } catch(SQLException error) {
            System.out.println(error.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return arrayOfTasks;
    }

    @Override
    public void deleteInfo(int id) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        try (Connection conn = getConnection();
             var statement = conn.prepareStatement(sql)) {

            statement.setInt(1, id);

            var result = statement.executeUpdate();
            System.out.println((result > 0) ? "Successfully" : "Error");

        } catch(SQLException error) {
            System.out.println(error.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateStatusOfTask(int idOfTasks) {
        String sql = """
                    UPDATE tasks
                    SET is_done = true
                    WHERE id = ? """;
        try (Connection conn = getConnection();
             var statement = conn.prepareStatement(sql)) {
            statement.setInt(1, idOfTasks);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
