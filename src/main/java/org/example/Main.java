package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
//        Task_db db_check = new Task_db("jdbc:postgresql://localhost:5432/test_bd", "postgres", "12345");
//        db_check.addInfo("Sobaka sutulya", true);
//        db_check.showInfoAboutTable();
//        db_check.deleteInfo(3);
//        db_check.showInfoAboutTable();

        TaskManager testExecuteOfApp = new TaskManager();
        testExecuteOfApp.taskAppExecutor();



    }
}