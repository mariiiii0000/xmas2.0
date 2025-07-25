package manager;

import model.Epic;
import model.Subtask;
import model.Task;
// RED+++
// Неиспользуемый импорт

public class TaskParserImpl implements TaskParser {



    @Override
    public String toString(Subtask subtask) {

        return TypesOfTasks.SUBTASK + "," +
                subtask.getID() + "," +
                subtask.getName() + "," +
                subtask.getDescription() + "," +
                subtask.getStatus() + "," +
                subtask.getEpicID();
    }

    @Override
    public String toString(Epic epic) {

        return TypesOfTasks.EPIC + "," +
                epic.getID() + "," +
                epic.getName() + "," +
                epic.getDescription() + "," +
                epic.getStatus();
    }

    @Override
    public String toString(Task task) {

        return TypesOfTasks.TASK + "," +
                task.getID() + "," +
                task.getName() + "," +
                task.getDescription() + "," +
                task.getStatus();
    }

    @Override
    public Task toTask(String string) {
        String[] data = string.split(",");
        long ID  = Long.parseLong(data[1]);
        String name = data[2];
        String description = data[3];
        Status status = Status.NEW;
        return new Task(ID, name, description, status);
    }

    @Override
    public Subtask toSubtask(String string) {
        String[] data = string.split(",");
        Status status = Status.NEW;
        String name = data[2];
        String description = data[3];
        long ID = Long.parseLong(data[1]);
        long epicID = Long.parseLong(data[5]);
        return new Subtask(ID, name, description, status, epicID);
    }

    @Override
    public Epic toEpic(String string) {
        String[] data = string.split(",");
        long ID = Long.parseLong(data[1]);
        String name = data[2];
        String description = data[3];
        return new Epic(name, description, ID);
    }
}
