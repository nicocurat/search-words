package models;

public class Node<T> {

    private T value;
    private String[] input;
    private String[] output;

    public Node(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
