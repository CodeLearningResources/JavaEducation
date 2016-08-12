package Generic_Programming;

import java.util.ArrayList;

/**
 * Created by mikim on 2016-08-11.
 */
public class Stack <E> {
    // Ch 6.1 Stack class
    private ArrayList<E> arrayList;

    public Stack() {
        arrayList = new ArrayList<E>();
    }

    public void push(E item) {
        arrayList.add(item);
    }

    public E pop() {
        int lastIndex = arrayList.size() - 1;
        return arrayList.remove(lastIndex);
    }

    public boolean isEmpty() {
        return arrayList.isEmpty();
    }


    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        System.out.println("Empty Stack:");
        System.out.println(stack.isEmpty());
        System.out.println("Adding two items");
        stack.push(3);
        stack.push(5);
        System.out.println(stack.pop());
        System.out.println(stack.isEmpty());
        stack.push(10);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
