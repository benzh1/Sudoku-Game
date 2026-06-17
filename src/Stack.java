import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Stack {

    private ArrayList<StackNode> nodes;
    private int size = 0;

    public Stack() {
        nodes = new ArrayList<>();
    }

    public Stack(StackNode node) {
        nodes = new ArrayList<>(Collections.singletonList(node));
        size++;
    }

    public void push(StackNode node) {
        nodes.add(node);
        size++;
    }

    public StackNode pop() {
        if (size > 0) {
            StackNode node = nodes.getLast();
            nodes.removeLast();
            size--;
            return node;
        } else {
            throw new NullPointerException();
        }
    }

    public StackNode peak() {
        if (size > 0) {
            return nodes.getLast();
        } else {
            return null;
        }
    }

    public int size() {
        return size;
    }
}
