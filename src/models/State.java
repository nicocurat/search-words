package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class State {

    private int id;
    private boolean acceptance;
    private List<Transition> transitions;

    public State(boolean acceptance, int id) {
        this.acceptance = acceptance;
        this.id = id;
        transitions = new ArrayList<>();
    }

    public boolean isAcceptance() {
        return acceptance;
    }

    public void setAcceptance(boolean acceptance) {
        this.acceptance = acceptance;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }

    public void addTransition(Transition transition) {
        this.transitions.add(transition);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "State{" +
                "id=" + id +
                ", acceptance=" + acceptance +
                ", transitions=" + transitions.toString() +
                '}';
    }
}
