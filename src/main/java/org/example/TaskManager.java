package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Random;

public class TaskManager {
    public void taskAppExecutor() {
        Task_db db_check = new Task_db();
        try(BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            while(true) {
                System.out.println("\n--- Tasks`s menu ---");
                System.out.println("1. Show list");
                System.out.println("2. Add task");
                System.out.println("3. Delete with id");
                System.out.println("4. Update status of task on completed");
                System.out.println("0. Exit");
                System.out.print("Choose number: ");

                String line = bf.readLine();
                    switch (line) {
                        case "1" -> infoStream(db_check.showInfo());
                        case "2" -> db_check.addInfo( addInfoGenerator(bf));
                        case "3" -> db_check.deleteInfo(deleteGenerator(bf));
                        case "0" -> {
                            return;
                        }
                    }
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private Task addInfoGenerator(BufferedReader bf) throws IOException {
        String desc = "";
        boolean isDone = false;

        while(true) {
            System.out.println("Add description");
            desc = bf.readLine();

            if (desc != null && !desc.trim().isEmpty()) {
                break;
            } else {
                System.out.println("Validation error: Description cannot be empty!");
            }
        }

        System.out.println("Add isDone (true or false)");
        while(true) {
            try {
                String input = bf.readLine();
                if (input == null) break;

                if (input.equalsIgnoreCase("true")) {
                    isDone = true;
                    break;
                } else if (input.equalsIgnoreCase("false")) {
                    isDone = false;
                    break;
                } else {
                    System.out.println("Error. Please enter 'true' or 'false'.");
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }

        Task task = new Task(desc, isDone);
        return task;
    }

    private void infoStream(List<Task> arrayOfTasksIsDone) {
        arrayOfTasksIsDone.stream()
                .forEach(elem -> System.out.println(elem));
    }

    private int deleteGenerator(BufferedReader bf) throws IOException{
        while(true) {
            try {
                System.out.println("Enter id: ");
                int getIdFromDb = Integer.parseInt(bf.readLine());
                return getIdFromDb;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private int updateTaskGenerator(BufferedReader bf) {
        Task_db task_dbForReadingIds = new Task_db();
        while(true) {
            try {
                System.out.println("Enter the id of task you would rather be update");
                int idOfTask = Integer.parseInt(bf.readLine());
                List<Task> array = task_dbForReadingIds.showInfo();
                for (Task elemGetId : array) {

                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
