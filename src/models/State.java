package models;

import java.util.List;
import java.util.Map;

public class State {

    private Map<Character, List<State>> stateMap;
    private boolean acceptance;

    public State(Map<Character, List<State>> stateMap, boolean acceptance) {
        this.stateMap = stateMap;
        this.acceptance = acceptance;
    }

    public Map<Character, List<State>> getStateMap() {
        return stateMap;
    }

    public void setStateMap(Map<Character, List<State>> stateMap) {
        this.stateMap = stateMap;
    }

    public boolean isAcceptance() {
        return acceptance;
    }

    public void setAcceptance(boolean acceptance) {
        this.acceptance = acceptance;
    }
}
