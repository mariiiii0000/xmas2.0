package manager;

public class Node<E> {
    public E data;
    public manager.Node<E> next;
    public manager.Node<E> prev;

    public Node(manager.Node<E> prev, E data, manager.Node<E> next) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }
}
