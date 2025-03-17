package manager;

public class Managers {
    // YELLOW+
    // Класс утилитарный - объектов на основе него мы не планируем создавать
    // поэтому можно вообще запретить это делать, объявив приватный конструктор

    private Managers() {
    }

    static public TaskManager getDefault(){
        return new InMemoryTaskManager();
    }
    static public HistoryManager getDefaultHistory(){
        return new InMemoryHistoryManager();
    }
}
