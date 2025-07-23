package manager;

public class Managers {

    private Managers() {
    }

    // RED
    // Необходимо здесь в фабрике менеджеров написать метод создания нового менеджера
    // и через этот класс получать новые объекты менеджера
    static public TaskManager getDefault(){
        return new InMemoryTaskManager();
    }
    static public HistoryManager getDefaultHistory(){
        return new InMemoryHistoryManager();
    }
}
