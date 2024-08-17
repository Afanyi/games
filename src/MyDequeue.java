import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyDequeue  implements Iterable<Integer>{
    private int size;
    DNode front;
    DNode rear;
    public MyDequeue() {
        size = 0;
        front = null;
        rear = null;
    }

    int peekFront() {
        int value;
        if (front == null) {
            throw new NoSuchElementException();
        }
        else{
            value = front.value;
            return value;
        }
    }
    int peekRear() {
        int value;
        if (rear == null) {
            throw new NoSuchElementException();
        }
        else{
            value = rear.value;
            return value;
        }
    }

    void printMyDeque() {
        System.out.println("my deque: ");
        DNode current = front;
        while (current != null) {
            System.out.println(current.value);
            current = current.next;
        }
    }

    void pushFront(int value) {
        DNode newNode = new DNode(value);
        if (front == null) {
            front = newNode;
            rear = newNode;
        }
        else {
            front.prev = newNode;
            newNode.next = front;
            front = newNode;
        }
        size++;
    }

    int popFront() {
        int value;
        if (front == null) {
            throw new NoSuchElementException();
        }
        if(size == 1) {
             value = front.value;
            front = null;
            rear = null;
        }
        else {
             value = front.value;
            front = front.next;
            front.prev = null;
        }
        size--;
        return value;
    }

    int popRear() {
        int value;
        if (rear == null) {
            throw new NoSuchElementException();
        }
        if(size == 1) {
            value = rear.value;
            rear = null;
            front = null;
        }
        else {
            value = rear.value;
            rear = rear.prev;
            rear.next = null;
        }
        size--;
        return value;
    }

    void pushRear(int value) {
        DNode newNode = new DNode(value);
        if (rear == null) {
            rear = newNode;
            front = newNode;
        }
        else {
            rear.next = newNode;
            newNode.prev = rear;
            rear = newNode;

        }
        size++;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<Integer>{
        DNode current = front;

        MyIterator(){}
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
    }
}
