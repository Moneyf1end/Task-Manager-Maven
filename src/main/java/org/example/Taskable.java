package org.example;

import java.util.List;

public interface Taskable {
    void addInfo(Task task);
    void deleteInfo(int id);
    List<Task> showInfo();
    void updateStatusOfTask(int id);
}
