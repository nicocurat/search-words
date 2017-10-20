package models;

public class Transition {

    private char c;
    private State to;

    public Transition(State to, char c) {
        this.to = to;
        this.c = c;
    }

    public State getTo() {
        return to;
    }

    public void setTo(State to) {
        this.to = to;
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "Transition{" +
                "c=" + c +
                ", to=" + to.getId() +
                '}';
    }
}
