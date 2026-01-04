package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Random;

public class TaskManager {
    private static final String[] array = new String[]{"Bye", "Hello", "Fuck off", "Smth gorgeous", "Visibility", "Testing"};
    private static final Boolean[] arrayAnother = new Boolean[]{true, false};
    private Random random = new Random();

    public <T> T randomGenerator(T[] arrayOfElements) {
        return arrayOfElements[random.nextInt(arrayOfElements.length)];
    }

    public void taskAppExecutor() {
        Task_db db_check = new Task_db("jdbc:postgresql://localhost:5432/YOUR_DB_NAME", "YOUR_USERNAME", "YOUR_PASSWORD");
        try(BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            while(true) {
                System.out.println("\n--- Tasks`s menu ---");
                System.out.println("1. Show list");
                System.out.println("2. Add task");
                System.out.println("3. Delete with id");
                System.out.println("0. Exit");
                System.out.print("Choose number: ");

                String line = bf.readLine();
                    switch (line) {
                        case "1" -> db_check.showInfoAboutTable();
                        case "2" -> db_check.addInfo(randomGenerator(array), randomGenerator(arrayAnother));
                        case "3" -> db_check.deleteRandomIdTask();
                        case "0" -> {
                            return;
                        }
                    }
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
