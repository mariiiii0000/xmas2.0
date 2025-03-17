package model;

import manager.Status;
import java.util.HashMap;
import java.util.Map;

public class Epic extends Task {

    private Map<Long, Subtask> subtasks = new HashMap<>();


    public Epic(String name, String description) {
        super(name, description, Status.NEW);
    }

    public Epic(String name, String description, long ID) {
        super(name, description, Status.NEW);
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
                case Status.NEW:
                    hasNew = true;
                    break;
                case Status.IN_PROCESS:
                    hasInProcess = true;
                    break;
                case Status.DONE:
                    hasDone = true;
                    break;
            }

            if (hasInProcess || (hasNew && hasDone)) {
                status = Status.IN_PROCESS;
            } else if (hasNew) {
                status = Status.NEW;
            } else if (hasDone) {
                status = Status.DONE;
            } else {
                status = Status.NEW;
            }
        }
    }

    public void removeSubtasks() {
        subtasks.clear();
        status = Status.NEW;
    }


    public Map<Long, Subtask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(Map<Long, Subtask> subtasks) {
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


