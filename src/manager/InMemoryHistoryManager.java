package manager;
import model.Task;
import java.util.LinkedList;

import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    // YELLOW++++
    // В данном случае больше подойдет LinkedList,
    // так как часто происходит удаление и добавление начальных и конечных элементов
    // List<Task> history = new LinkedList<>();

    // RED++
    // Отсутствует инкапсуляция, тк поле является публичным
    private final List<Task> history = new LinkedList<>();
    private final int SIZE = 10;

    // YELLOW
    // Действие добавления задачи мы делаем в обоих случаях,
    // поэтому его можно вынести за условную конструкцию
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
