package org.example;

public class Task {
    private int id;
    private String description;
    private boolean is_done;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isIs_done() {
        return is_done;
    }
    public void setId(int newId) {
        if (newId != this.id && newId > 0) this.id = newId;
    }

    public Task(String description, boolean is_done) {
        this.description = description;
        this.is_done = is_done;
    }

    @Override
    public String toString() {
        return "Task{id=" + id + ", desc='" + description + "', done=" + is_done + "}";
    }
}
