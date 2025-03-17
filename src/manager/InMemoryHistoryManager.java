package manager;
import model.Task;
import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    public List<Task> history = new ArrayList<>();
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
