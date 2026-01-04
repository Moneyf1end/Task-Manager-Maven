package org.example;
import java.sql.*;
import java.util.Random;

public class Task_db {
    private String url;
    private String user;
    private String password;

    Task_db() {

    }
    Task_db(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void addInfo(String description, boolean flag) {
        try (Connection conn = getConnection()) {
            System.out.println("Trying to connect");

            String sql = "INSERT INTO tasks (description, is_done) VALUES(?, ?)";
            var preparesStatement = conn.prepareStatement(sql);
            preparesStatement.setString(1, description);
            preparesStatement.setBoolean(2, flag);

            preparesStatement.executeUpdate();
            System.out.println("Task was added succesfully");

        } catch(SQLException error) {
            System.out.println(error.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void showInfoAboutTable() throws SQLException{
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM tasks";
            var statement = conn.createStatement();
            var resultSet = statement.executeQuery(sql);  // type of resultSet is ResultSet

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                boolean is_done = resultSet.getBoolean("is_done");

                System.out.println(resultSet.getRow() + " строка. " + id + "\t" + description + "\t" + is_done);
            }
        } catch(SQLException error) {
            System.out.println(error.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteRandomIdTask() {
        try (Connection conn = getConnection()) {
            String sql = "SELECT id FROM tasks ORDER BY RANDOM() LIMIT 1";

            var statement = conn.createStatement();
            var resultSet = statement.executeQuery(sql);

            if(resultSet.next()) {
                int id = resultSet.getInt("id");
                deleteInfo(id);
            } else {
                System.out.println("Nothing to delete");
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteInfo(int id) {
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM tasks WHERE id = ?";

            var statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            var result = statement.executeUpdate();
            System.out.println((result > 0) ? "Successfully" : "Error");

        } catch(SQLException error) {
            System.out.println(error.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
