package model;

import java.util.HashMap;

public class Epic extends Task {
    private HashMap<Long, Subtask> subtasks = new HashMap<>();


    public Epic(String name, String description) {
        super(name, description, "NEW");
    }

    public Epic(String name, String description, long ID) {
        super(name, description, "NEW");
        this.ID = ID;
    }

    public void removeSubtaskByID(long id) {
        subtasks.remove(id);
        updateStatus();

    }


    public void addSubtasks(Subtask subtask) {
        subtasks.put(subtask.getID(), subtask);
        updateStatus();
    }


    public void updateStatus() {
        boolean hasNew = false;
        boolean hasInProcess = false;
        boolean hasDone = false;

        for (Subtask subtask : subtasks.values()) {
            switch (subtask.getStatus()) {
                case "NEW":
                    hasNew = true;
                    break;
                case "IN PROCESS":
                    hasInProcess = true;
                    break;
                case "DONE":
                    hasDone = true;
                    break;
            }

            if (hasInProcess || (hasNew && hasDone)) {
                status = "IN PROCESS";
            } else if (hasNew) {
                status = "NEW";
            } else if (hasDone) {
                status = "DONE";
            } else {
                status = "NEW";
            }
        }
    }

    public void removeSubtasks() {
        subtasks.clear();
        status = "NEW";
    }


    public HashMap<Long, Subtask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(HashMap<Long, Subtask> subtasks) {
        this.subtasks = subtasks;
        updateStatus();
    }

    @Override
    public String toString() {
        return "Epic{" +
                "subtasks=" + subtasks +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ID=" + ID +
                ", status='" + status + '\'' +
                '}';
    }

}


