package manager;
import model.Task;
import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    public List<Task> history = new ArrayList<>(size);
    private static final int size = 10;

    @Override
    public void add(Task task) {
        history.add(task);
    }

    @Override
    public List<Task> getHistory(){
        return history;
    }
}
