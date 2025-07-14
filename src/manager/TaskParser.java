package manager;

import model.Epic;
import model.Subtask;
import model.Task;

public interface TaskParser {

    String toString(Task task);
    String toString(Subtask subtask);
    String toString(Epic epic);

    Task toTask(String string);
    Subtask toSubtask(String string);
    Epic toEpic(String string);




}
