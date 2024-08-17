import java.util.Iterator;

public class MyStack implements Iterable<Integer>{
    private SNode top;
    private int size;

    public MyStack() {
        this.top = null;
        this.size = 0;
    }

    // Push operation: Add an element to the top of the stack
    public void push(int data) {
        SNode newNode = new SNode(data);
        newNode.next = top;
        top = newNode;
        size++;
    }

    // Pop operation: Remove and return the top element from the stack
    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        int poppedValue = top.data;
        top = top.next;
        size--;
        return poppedValue;
    }

    // Peek operation: Return the top element without removing it
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return top.data;
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return top == null;
    }

    // Get the size of the stack
    public int getSize() {
        return size;
    }

    // Print the stack elements
    public void printStack() {
        SNode current = top;
        System.out.print("Stack: ");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    @Override
    public Iterator<Integer> iterator() {
        return new MyIterator();
    }
    private class MyIterator implements Iterator<Integer> {
        private SNode current;
        MyIterator(){
            current = top;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Integer next() {
            int value = current.data;
            current = current.next;
            return value;
        }
    }
}
