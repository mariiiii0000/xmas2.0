package manager;
import model.Epic;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {

    Task getTaskByID(long id);

    Subtask getSubtaskByID(long id);

    Epic getEpicByID(long id);


    void removeAllSubtasks();


    void removeTasksByID(long id);

    void removeSubtaskByID(long id);

    void removeEpicByID(long id);


    ArrayList<Subtask> getSubtasks();

    ArrayList<Epic> getEpics();

    ArrayList<Task> getTasks();


    void removeEpics();

    void removeSubtask();

    void removeTasks();


    void updateTask(Task newTask);

    void updateSubtask(Subtask newSubtask);

    void updateEpic(Epic newEpic);


    ArrayList<Subtask> getSubtasksByEpicID(long epicID);


    void createTask(Task task);

    void createSubtask(Subtask subtask);

    void createEpic(Epic epic);

    List<Task> getHistory();
}
