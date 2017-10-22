package models;

import java.util.ArrayList;
import java.util.List;

public class DFA implements FA{
    private int total;
    private List<State> states = new ArrayList<>();
    private State currentState;

    public DFA() {
        total = 0;
        currentState = new State(false, 0);
        states.add(currentState);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<State> getStates() {
        return states;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }
}
