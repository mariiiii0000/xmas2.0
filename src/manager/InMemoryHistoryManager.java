package manager;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private final HashMap<Long, Node> tasks = new HashMap<>();
    private final CustomLinkedList<Task> tasksLinkedList = new CustomLinkedList<>();

    public void add(Task task){
        if (tasks.containsKey(task.getID())){
            tasksLinkedList.removeNode(tasks.get(task.getID()));
        }
        Node<Task> node = tasksLinkedList.linkLast(task);
        tasks.put(task.getID(), node);
    }

    public void remove(long id){
        Node<Task> node = tasks.get(id);
        tasksLinkedList.removeNode(node);
        tasks.remove(id);
    }

    @Override
    public List<Task> getHistory() {
        return tasksLinkedList.getTask();
    }



    public static class CustomLinkedList<T>{
        private Node<T> head;
        private Node<T> tail;
        private int size;

        public Node<T> linkLast(T node) {
            final Node<T> oldTail = tail;
            Node<T> newNode = new Node(tail, node, null);
            tail = newNode;
            if (oldTail != null){
                oldTail.next = newNode;
            }
            if (size == 0){
                head = newNode;
            }
            size++;
            return newNode;
        }

        public int getSize(){
            return this.size;
        }

        public List<T> getTask(){
            List<T> tasksList = new ArrayList<>();
            Node<T> cur = head;
            while (cur != null){
                tasksList.add(cur.data);
                cur = cur.next;
            }
            return tasksList;
        }

        public void removeNode(Node<T> node){
            if (node == null){
                return;
            }
            if (node == tail && node == head){
                head = null;
                tail = null;
                size--;
                return;
            } else if (node == tail){
                Node<T> prevNode = node.prev;
                prevNode.next = null;
                tail = prevNode;
            } else if (node == head){
                Node<T> nextNode = node.next;
                nextNode.prev = null;
                head = nextNode;
            } else {
                Node<T> prevNode = node.prev;
                prevNode.next = node.next;
                Node<T> nextNode = node.next;
                nextNode.prev = node.prev;
            }
            size--;
        }
    }

}

