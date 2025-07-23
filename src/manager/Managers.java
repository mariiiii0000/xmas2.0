package manager;

public class Managers {

    private Managers() {
    }
    // RED++++
    // Необходимо здесь в фабрике менеджеров написать метод создания нового менеджера
    // и через этот класс получать новые объекты менеджера

    public static TaskManager createTaskManager() {
        return new InMemoryTaskManager();
    }

    public static HistoryManager createHistoryManager() {
        return new InMemoryHistoryManager();
    }
}