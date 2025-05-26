package manager;

// YELLOW
// Класс Node используется только в CustomLinkedList, поэтому его можно засунуть во внутрь класса
// Либо лучше подходит папка model для Node
public class Node<E> {
    public E data;
    public Node<E> next;
    public Node<E> prev;

    public Node(Node<E> prev, E data, Node<E> next) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }
}

