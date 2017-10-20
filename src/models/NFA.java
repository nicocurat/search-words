package models;

import java.util.ArrayList;
import java.util.List;

public class NFA {

    private int total;
    private List<State> states = new ArrayList<>(); //Mapa que tiene su estado con cada transicion posible!
    private State currentState;

    public NFA() {
        total = 0;
        currentState = new State(false, 0);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    private void consume(char c, boolean acceptance) {
        State nextState = new State(acceptance, total+1);
        Transition transition = new Transition(nextState, c);
        currentState.addTransition(transition);
        if(!states.contains(currentState)) {
            states.add(currentState); // Agrega a la lista el estado anterior.
        }
        currentState = nextState;
        total++;
    }

    public void addWords(List<Character> characters) {
        for (int i = 0; i < characters.size(); i++) {
            if(i == characters.size() - 1){
                consume(characters.get(i), true);
                states.add(currentState);
            } else {
                if ((int) characters.get(i + 1) == 32) {
                    consume(characters.get(i), true);
                }
                if((int) characters.get(i + 1) == 10) {
                    consume(characters.get(i), true);
                    states.add(currentState);
                }
                if (isEndOfLine(characters.get(i))) {
                    currentState = states.get(0);
                }
                if ((int) characters.get(i + 1) != 32 && (int) characters.get(i+1) != 10 && !isEndOfLine(characters.get(i))) {
                    consume(characters.get(i), false);
                }
            }
        }
    }

    private boolean isEndOfLine(char c) {
        return ((int) c == 10);
    }

    public List<State> getStates() {
        return states;
    }
}
