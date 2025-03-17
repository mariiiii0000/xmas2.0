package manager;

public class Managers {

    private Managers() {
    }

    static public TaskManager getDefault(){
        return new InMemoryTaskManager();
    }
    static public HistoryManager getDefaultHistory(){
        return new InMemoryHistoryManager();
    }
}
