package manager;

import model.Epic;
import model.Subtask;
import model.Task;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class InMemoryTaskManager implements TaskManager {
    // RED
    // Мы создали утилитарный класс для того, чтобы он здесь возвращал там менеджера нужного
    // = Managers.getDefaultHistory();
    HistoryManager historyManager = new InMemoryHistoryManager();
    private final Map<Long, Task> taskHashMap = new HashMap<>();
    private final Map<Long, Subtask> subtaskHashMap = new HashMap<>();
    private final Map<Long, Epic> epicHashMap = new HashMap<>();
    private long nextID = 1;

    @Override
    public Task getTaskByID(long id) {
        historyManager.add(taskHashMap.get(id));
        return taskHashMap.get(id);
    }

    @Override
    public Subtask getSubtaskByID(long id) {
        historyManager.add(subtaskHashMap.get(id));
        return subtaskHashMap.get(id);
    }

    @Override
    public Epic getEpicByID(long id) {
        historyManager.add(epicHashMap.get(id));
        return epicHashMap.get(id);
    }

    @Override
    public void removeAllSubtasks() {
        subtaskHashMap.clear();
        for (Epic epic : epicHashMap.values()) {
            epic.removeSubtasks();
        }
    }

    @Override
    public void removeTasksByID(long id) {
        taskHashMap.remove(id);
    }

    @Override
    public void removeSubtaskByID(long id) {
        Subtask subtask = subtaskHashMap.get(id);
        Epic epic = epicHashMap.get(subtask.getEpicID());
        epic.removeSubtaskByID(id);
        subtaskHashMap.remove(id);
    }

    @Override
    public void removeEpicByID(long id) {
        epicHashMap.remove(id);
    }
    // RED
    // Уже изучили полиморфизм, поэтому следует объявлять переменные типом интерфейса или абстрактного класса
    // Чтобы не привязываться к конкретному типу
    @Override
    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtaskHashMap.values());
    }
    // RED
    // Уже изучили полиморфизм, поэтому следует объявлять переменные типом интерфейса или абстрактного класса
    // Чтобы не привязываться к конкретному типу
    @Override
    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epicHashMap.values());
    }
    // RED
    // Уже изучили полиморфизм, поэтому следует объявлять переменные типом интерфейса или абстрактного класса
    // Чтобы не привязываться к конкретному типу
    @Override
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(taskHashMap.values());

    }

    @Override
    public void removeEpics() {
        epicHashMap.clear();
    }

    @Override
    public void removeSubtask() {
        subtaskHashMap.clear();
    }

    @Override
    public void removeTasks() {
        taskHashMap.clear();
    }

    @Override
    public void updateTask(Task newTask) {
        taskHashMap.remove(newTask.getID(), newTask);
        taskHashMap.put(newTask.getID(), newTask);
    }

    @Override
    public void updateSubtask(Subtask newSubtask) {
        subtaskHashMap.put(newSubtask.getEpicID(), newSubtask);
        Epic epic = epicHashMap.get(newSubtask.getEpicID());
        epic.updateStatus();
    }

    @Override
    public void updateEpic(Epic newEpic) {
        Epic oldEpic = epicHashMap.get(newEpic.getID());
        HashMap<Long, Subtask> subtasks = oldEpic.getSubtasks();
        newEpic.setSubtasks(subtasks);
        epicHashMap.put(newEpic.getID(), newEpic);
    }

    @Override
    public ArrayList<Subtask> getSubtasksByEpicID(long epicID) {
        Epic epic = epicHashMap.get(epicID);
        return new ArrayList<>(epic.getSubtasks().values());
    }

    @Override
    public void createTask(Task task) {
        task.setID(nextID);
        taskHashMap.put(task.getID(), task);
        nextID++;
    }

    @Override
    public void createSubtask(Subtask subtask) {
        long epicID = subtask.getEpicID();
        if (!epicHashMap.containsKey(epicID)) {
            System.out.println("Не найден ID((((");
            return;
        }
        Epic epic = epicHashMap.get(epicID);
        subtask.setID(nextID);
        epic.addSubtasks(subtask);
        subtaskHashMap.put(subtask.getID(), subtask);
        nextID++;
    }

    @Override
    public void createEpic(Epic epic) {
        epic.setID(nextID);
        nextID++;
        epicHashMap.put(epic.getID(), epic);

    }
    @Override
    public List<Task> getHistory(){
        return historyManager.getHistory();

    }

}
