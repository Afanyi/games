//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //MyFrame myFrame = new MyFrame();
        MyDequeue deque = new MyDequeue();
        deque.pushFront(1);
        deque.pushFront(2);
        deque.pushRear(3);
        deque.pushRear(4);
        deque.printMyDeque();
        int x = deque.popRear();
        System.out.println(" the value of x is " + x);
        deque.printMyDeque();
        x = deque.popFront();
        System.out.println(" the value of x is " + x);
        deque.printMyDeque();
        x = deque.popFront();
        System.out.println(" the value of x is " + x);
        deque.printMyDeque();
        x = deque.popFront();
        System.out.println(" the value of x is " + x);
        deque.printMyDeque();


    }
}