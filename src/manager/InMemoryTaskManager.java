package manager;

import model.Epic;
import model.Subtask;
import model.Task;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class InMemoryTaskManager implements TaskManager {

    protected final HistoryManager historyManager = Managers.getDefaultHistory();
    protected final Map<Long, Task> taskHashMap = new HashMap<>();
    protected final Map<Long, Subtask> subtaskHashMap = new HashMap<>();
    protected final Map<Long, Epic> epicHashMap = new HashMap<>();
    protected long nextID = 1;

    @Override
    public Task getTaskByID(long id) {
        if (taskHashMap.containsKey(id)){
            historyManager.add(taskHashMap.get(id));
            return taskHashMap.get(id);
        } else {
            return null;
        }
    }

    @Override
    public Subtask getSubtaskByID(long id) {
        if (subtaskHashMap.containsKey(id)){
            historyManager.add(subtaskHashMap.get(id));
            return subtaskHashMap.get(id);
        } else {
            return null;
        }
    }

    @Override
    public Epic getEpicByID(long id) {
        if (epicHashMap.containsKey(id)){
            historyManager.add(epicHashMap.get(id));
            return epicHashMap.get(id);
        } else {
            return null;
        }
    }

    @Override
    public void removeTasksByID(long id) {
        taskHashMap.remove(id);
        historyManager.remove(id);

    }

    @Override
    public void removeSubtaskByID(long id) {
        Subtask subtask = subtaskHashMap.get(id);
        Epic epic = epicHashMap.get(subtask.getEpicID());
        epic.removeSubtaskByID(id);
        subtaskHashMap.remove(id);
        historyManager.remove(id);
    }

    @Override
    public void removeEpicByID(long id) {
        List<Subtask> epSubtasks = getSubtasksByEpicID(id);
        for (Subtask subtask : epSubtasks) {
            subtaskHashMap.remove(subtask.getID());
            historyManager.remove(subtask.getID());
        }
        epicHashMap.remove(id);
        historyManager.remove(id);
    }

    @Override
    public List<Subtask> getSubtasks() {
        for (Subtask subtask : subtaskHashMap.values()){
            historyManager.add(subtask);
        }
        return new ArrayList<>(subtaskHashMap.values());
    }

    @Override
    public List<Epic> getEpics() {
        for (Epic epic : epicHashMap.values()){
            historyManager.add(epic);
        }
        return new ArrayList<>(epicHashMap.values());
    }

    @Override
    public List<Task> getTasks() {
        for (Task task : taskHashMap.values()){
            historyManager.add(task);
        }
        return new ArrayList<>(taskHashMap.values());
    }

    @Override
    public void removeEpics() {
        for (Epic epic : epicHashMap.values()){
            historyManager.remove(epic.getID());
        }
        for (Subtask subtask : subtaskHashMap.values()){
            historyManager.remove(subtask.getID());
        }
        subtaskHashMap.clear();
        epicHashMap.clear();
    }

    @Override
    public void removeSubtasks() {
        for (Subtask subtask : subtaskHashMap.values()){
            historyManager.remove(subtask.getID());
        }
        subtaskHashMap.clear();
    }

    @Override
    public void removeAllSubtasks() {
        subtaskHashMap.clear();
        for (Epic epic : epicHashMap.values()) {
            epic.removeSubtasks();
        }
        for (Subtask subtask : subtaskHashMap.values()){
            historyManager.remove(subtask.getID());
        }
    }

    @Override
    public void removeTasks() {
        for (Task task : taskHashMap.values()){
            historyManager.remove(task.getID());
        }
        taskHashMap.clear();
    }

    @Override
    public void updateTask(Task newTask) {
        taskHashMap.remove(newTask.getID(), newTask);
        taskHashMap.put(newTask.getID(), newTask);
        historyManager.remove(newTask.getID());
        historyManager.add(newTask);
    }

    @Override
    public void updateSubtask(Subtask newSubtask) {
        subtaskHashMap.remove(newSubtask.getEpicID(), newSubtask);
        subtaskHashMap.put(newSubtask.getEpicID(), newSubtask);
        Epic epic = epicHashMap.get(newSubtask.getEpicID());
        epic.updateStatus();
        historyManager.remove(newSubtask.getID());
        historyManager.add(newSubtask);
    }

    @Override
    public void updateEpic(Epic newEpic) {
        Epic oldEpic = epicHashMap.get(newEpic.getID());
        Map<Long, Subtask> subtasks = oldEpic.getSubtasks();
        newEpic.setSubtasks(subtasks);
        epicHashMap.put(newEpic.getID(), newEpic);
        historyManager.remove(newEpic.getID());
        historyManager.add(newEpic);
    }

    @Override
    public List<Subtask> getSubtasksByEpicID(long epicID) {
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
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

}
