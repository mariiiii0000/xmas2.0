package manager;
import model.Task;
import java.util.LinkedList;

import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private final List<Task> history = new LinkedList<>();
    private final int SIZE = 10;

    @Override
    public void add(Task task) {
        if (history.size() < SIZE)
            history.add(task);
        else {
            history.removeFirst();
            history.add(task);

        }
    }

    @Override
    public List<Task> getHistory(){
        return history;
    }
}
