import java.util.Iterator;

public class MyList implements Iterable<Integer> {
    int size;
    Node head;
    Node tail;

    public MyList() {
        size = 0;
    }

    public MyList(int size) {
        this.size = 0;
        for (int i = 0; i < size; i++) {
            push(0);
        }


    }
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }

    public void set(int index, int value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.value = value;
    }

    public void push(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    int at(int index) {
        Node current = head;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        else {
            for (int i = 1; i <= index; i++) {
                current = current.next;
            }
        }
        if (current == null) {
            throw new IndexOutOfBoundsException();
        }
        return current.value;
    }

    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.println(current.value);
            current = current.next;
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new MyListIterator();
    }

    private class MyListIterator implements Iterator<Integer> {
        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Integer next() {
            int value = current.value;
            current = current.next;
            return value;
        }

        @Override
        public void remove() {
            if (current == null || current.prev == null) {
                throw new IllegalStateException("No element to remove");
            }

            Node toRemove = current.prev;
            if (toRemove.prev != null) {
                toRemove.prev.next = current;
                current.prev = toRemove.prev;
            } else {
                head = current;
                head.prev = null;
            }

            size--;
        }
    }
}
