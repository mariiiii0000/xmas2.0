import manager.Manager;
import model.Subtask;
import model.Task;
import model.Epic;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();


        Task task1 = new Task("task 1", "cook pasta", "NEW");
        manager.createTask(task1);
        System.out.println(manager.getTasks());
        Task UpdTask1 = new Task(task1.getID(), "task 2 upd", "feed at 15", "IN PROCESS");


        Epic epic1 = new Epic("epic 1", "math");
        manager.createEpic(epic1);
        Epic updEpic1 = new Epic("epic 2 upd", "eng", epic1.getID());


        Subtask subtask1 = new Subtask("subtask 1 ", "312, 311", "new", 2);
        manager.createSubtask(subtask1);
        Subtask updSubtask1 = new Subtask(subtask1.getID(), "subtask 2 upd", "s.112-113",
                "new", 2);
        Subtask subtask3 = new Subtask("subtask 3", "-", "new", 2);
        manager.createSubtask(subtask3);

        System.out.println(manager.getEpics());
        System.out.println(manager.getSubtasks());

        System.out.println("------------------------------   GET BY ID");


        System.out.println(manager.getSubtaskByID(subtask1.getID()));

        System.out.println(manager.getTaskByID(task1.getID()));

        System.out.println(manager.getEpicByID(epic1.getID()));

        System.out.println("-------------------------------   UPDATE");

        System.out.println("BEFORE");
        System.out.println(manager.getSubtasks());
        System.out.println("AFTER");
        manager.updateSubtask(updSubtask1);
        System.out.println(manager.getSubtasks());

        System.out.println("BEFORE");
        System.out.println(manager.getEpics());
        System.out.println("AFTER");
        manager.updateEpic(updEpic1);
        System.out.println(manager.getEpics());

        System.out.println("BEFORE");
        System.out.println(manager.getTasks());
        System.out.println("AFTER");
        manager.updateTask(UpdTask1);
        Task updatedTask = manager.getTaskByID(UpdTask1.getID());
        System.out.println(updatedTask);


        System.out.println("------------------------------   GET SBTSK BY ID");


        System.out.println(manager.getSubtasksByEpicID(epic1.getID()));


        System.out.println("------------------------------   REMOVE ALLLLLLLLL HAHAHA");

        manager.removeSubtaskByID(subtask1.getID());
        System.out.println(manager.getSubtaskByID(subtask1.getID()));
        manager.removeTasksByID(task1.getID());
        System.out.println(manager.getTasks());
        manager.removeEpicByID(epic1.getID());
        System.out.println(manager.getEpics());


        manager.removeAllSubtasks();
        manager.removeTasks();
        manager.removeEpics();
        manager.removeSubtask();
        System.out.println(manager.getTasks());
        System.out.println(manager.getSubtasks());
        System.out.println(manager.getEpics());


        System.out.println("------------------------------");


        System.out.println("the end ☺");

    }
}
