package manager;
import model.Subtask;
import model.Task;
import model.Epic;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FileBackedTaskManager extends InMemoryTaskManager {


    final static protected TaskParser taskParser = new TaskParserImpl();
    private final String pathTo;
    private final String pathFrom;

    public FileBackedTaskManager(String to, String from) {
        this.pathTo = to;
        this.pathFrom = from;
    }

    public String getPathTo() {
        return pathTo;
    }

    public String getPathFrom() {
        return pathFrom;
    }

    String historyToString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : historyManager.getHistory()) {
            long id = task.getID();
            stringBuilder.append(id).append(",");
        }
        if (!stringBuilder.isEmpty()) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    static List<Long> historyFromString(String value){
        String[] values = value.split(",");
        List<Long> ids = new ArrayList<>();

        for (String num : values) {
            ids.add(Long.parseLong(num));
        }
        return ids;
    }


    public static FileBackedTaskManager loadFromFile(String pathTo, String pathFrom){
        FileBackedTaskManager fileBackedTaskManager = new FileBackedTaskManager(pathTo, pathFrom);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathFrom))) {
            bufferedReader.readLine();
            String line = bufferedReader.readLine();
            if (line == null){
                return fileBackedTaskManager;
            }
            while (!line.isBlank()){
                String[] data = line.split(",");
                TypesOfTasks typeOfTask = TypesOfTasks.valueOf(data[0]);
                switch (typeOfTask){
                    case TypesOfTasks.TASK:
                        Task task = taskParser.toTask(line);
                        fileBackedTaskManager.createTask(task);
                        break;
                    case TypesOfTasks.SUBTASK:
                        Subtask subtask = taskParser.toSubtask(line);
                        fileBackedTaskManager.createSubtask(subtask);
                        break;
                    case TypesOfTasks.EPIC:
                        Epic epic = taskParser.toEpic(line);
                        fileBackedTaskManager.createEpic(epic);
                        break;
                    default:
                        System.out.println("Неизвестный тип задачи.");
                        break;
                }
                line = bufferedReader.readLine();
            }
            line = bufferedReader.readLine();
            historyFromString(line);
        } catch (IOException e) {
            System.out.println("Ошибка во время чтения файла.");
        }
        return fileBackedTaskManager;
    }

    @Override
    public void removeEpics() {
        super.removeEpics();
        save(pathTo);
    }

    @Override
    public void removeSubtasks() {
        super.removeSubtasks();
        save(pathTo);
    }

    @Override
    public void removeAllSubtasks() {
        super.removeAllSubtasks();
        save(pathTo);
    }

    @Override
    public void removeTasks() {
        super.removeTasks();
        save(pathTo);
    }

    @Override
    public void removeTasksByID(long id) {
        super.removeTasksByID(id);
        save(pathTo);
    }

    @Override
    public void removeSubtaskByID(long id) {
        super.removeSubtaskByID(id);
        save(pathTo);
    }

    @Override
    public void removeEpicByID(long id) {
        super.removeEpicByID(id);
        save(pathTo);
    }

    @Override
    public void createSubtask(Subtask subtask){
        super.createSubtask(subtask);
        save(pathTo);
    }

    @Override
    public void createTask(Task task) {
        super.createTask(task);
        save(pathTo);
    }

    @Override
    public void createEpic(Epic epic) {
        super.createEpic(epic);
        save(pathTo);
    }

    @Override
    public Task getTaskByID(long id) {
        Task task = super.getTaskByID(id);
        save(pathTo);
        return task;
    }

    @Override
    public Subtask getSubtaskByID(long id) {
        Subtask subtask = super.getSubtaskByID(id);
        save(pathTo);
        return subtask;
    }

    @Override
    public Epic getEpicByID(long id) {
        Epic epic = super.getEpicByID(id);
        save(pathTo);
        return epic;
    }

    @Override
    public List<Subtask> getSubtasks() {
        List<Subtask> list = new ArrayList<>(super.getSubtasks());
        save(pathTo);
        return list;
    }

    @Override
    public List<Epic> getEpics() {
        List<Epic> list = new ArrayList<>(super.getEpics());
        save(pathTo);
        return list;
    }

    @Override
    public List<Task> getTasks() {
        List<Task> list = new ArrayList<>(super.getTasks());
        save(pathTo);
        return list;
    }

    @Override
    public void updateTask(Task newTask) {
        super.updateTask(newTask);
        save(pathTo);
    }

    @Override
    public void updateSubtask(Subtask newSubtask) {
        super.updateSubtask(newSubtask);
        save(pathTo);
    }

    @Override
    public void updateEpic(Epic newEpic) {
        super.updateEpic(newEpic);
        save(pathTo);
    }

    @Override
    public List<Subtask> getSubtasksByEpicID(long epicID) {
        List<Subtask> list = new ArrayList<>(super.getSubtasksByEpicID(epicID));
        save(pathTo);
        return list;
    }

    public void save(String fileName){
        try (FileWriter fileWriter = new FileWriter(fileName)){
            StringBuilder stringBuilder = new StringBuilder("type,id,name,description,status\n");
            for (Task task: taskHashMap.values()) {
                stringBuilder.append(taskParser.toString(task)).append("\n");
            }
            for (Epic epic: epicHashMap.values()) {
                stringBuilder.append(taskParser.toString(epic)).append("\n");
            }
            for (Subtask subtask: subtaskHashMap.values()) {
                stringBuilder.append(taskParser.toString(subtask)).append("\n");
            }
            stringBuilder.append("\n");
            stringBuilder.append(historyToString());
            fileWriter.write(stringBuilder.toString());

        }catch (IOException e){
            System.out.println("Ошибка во время чтения файла.");
        }
    }

}
