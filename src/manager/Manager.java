package manager;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;

// YELLOW
// Между методами рекомендуется делать один отступ
public class Manager {

    // GREEN
    // Инкапсуляция присутствует, круто!♥♥♥♥
    private HashMap<Long, Task> taskHashMap = new HashMap<>();
    private HashMap<Long, Subtask> subtaskHashMap = new HashMap<>();
    private HashMap<Long, Epic> epicHashMap = new HashMap<>();
    private long nextID = 1;


    public Task getTaskByID(long id) {
        return taskHashMap.get(id);
    }

    public Subtask getSubtaskByID(long id) {
        return subtaskHashMap.get(id);
    }

    public Epic getEpicByID(long id) {
        return epicHashMap.get(id);
    }

    // YELLOW+
    // Было бы здорово сгруппировать методы по назначению вместе




    public void removeTasksByID(long id) {
        taskHashMap.remove(id);
    }

    public void removeSubtaskByID(long id) {
        Subtask subtask = subtaskHashMap.get(id);
        Epic epic = epicHashMap.get(subtask.getEpicID());
        epic.removeSubtaskByID(id);
        subtaskHashMap.remove(id);
    }

    public void removeEpicByID(long id) {
        epicHashMap.remove(id);
    }



    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtaskHashMap.values());
    }

    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epicHashMap.values());
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(taskHashMap.values());

    }



    // RED+
    // Если удалены все эпики, то и все сабтаски должны быть удалены
    public void removeEpics() {
        epicHashMap.clear();
        subtaskHashMap.clear();
    }

    // RED+
    // Лишний метод. Метод по удалению сабтасков находится уже выше в коде
    public void removeAllSubtasks() {
        subtaskHashMap.clear();
        for (Epic epic : epicHashMap.values()) {
            epic.removeSubtasks();
        }
    }

    public void removeTasks() {
        taskHashMap.clear();
    }



    // YELLOW+
    // Необязательно удалять перед тем как путить, потому что сам пут уже заменяет значение
    public void updateTask(Task newTask) {
        taskHashMap.put(newTask.getID(), newTask);
    }

    // RED+
    // Недостаточно обновить задачу в общем хранилище
    // Ее так же необходимо обновить в самом эпике
    public void updateSubtask(Subtask newSubtask) {
        subtaskHashMap.put(newSubtask.getEpicID(), newSubtask);
        Epic epic = epicHashMap.get(newSubtask.getEpicID());
        epic.removeSubtaskByID(newSubtask.getID());
        epic.addSubtask(newSubtask);
        epic.updateStatus();
    }

    // GREEN )))
    // Замечательно, метод по обновлению эпика реализован корректно
    // ведь он сохраняет прежние сабтаски старого эпика
    public void updateEpic(Epic newEpic) {
        Epic oldEpic = epicHashMap.get(newEpic.getID());
        HashMap<Long, Subtask> subtasks = oldEpic.getSubtasks();
        newEpic.setSubtasks(subtasks);
        epicHashMap.put(newEpic.getID(), newEpic);
    }


    public ArrayList<Subtask> getSubtasksByEpicID(long epicID) {
        Epic epic = epicHashMap.get(epicID);
        return new ArrayList<>(epic.getSubtasks().values());
    }


    public void createTask(Task task) {
        task.setID(nextID);
        taskHashMap.put(task.getID(), task);
        nextID++;
    }

    public void createSubtask(Subtask subtask) {
        long epicID = subtask.getEpicID();
        if (!epicHashMap.containsKey(epicID)) {
            System.out.println("Не найден ID((((");
            return;
        }
        Epic epic = epicHashMap.get(epicID);
        subtask.setID(nextID);
        epic.addSubtask(subtask);
        subtaskHashMap.put(subtask.getID(), subtask);
        nextID++;
    }

    public void createEpic(Epic epic) {
        epic.setID(nextID);
        nextID++;
        epicHashMap.put(epic.getID(), epic);

    }
}
