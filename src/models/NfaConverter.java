package models;

import java.util.*;

/**
 * Created by Tomás Iturralde on 20/10/2017.
 * Materia: MateDis.
 */
public class NfaConverter {

    /**
    TODO:
    1. Pasarle todos los estados no modificados al dfa.
     */

    public DFA converter(NFA nfa){
        DFA res = new DFA();
        Stack<State> modStates = new Stack<>();
        State aux1 = nfa.getStates().get(0);
        int newId = nfa.getTotal();
        List<State> unmodifiedStates = new ArrayList<>();

        while (!aux1.getTransitions().isEmpty()){
            List<Transition> auxTrans = aux1.getTransitions();
            while (!auxTrans.isEmpty()){
                if (containsMoreThanOneEqualTrans(auxTrans)){
                    State modState = new State(checkAcceptance(auxTrans), ++newId);
                    modState.setTransitions(moddedTransitions(auxTrans));
                    res.getStates().add(modState);
                    res.getCurrentState().setTransitions(removeAll(res.getCurrentState().getTransitions(), auxTrans.get(0).getC()));
                    res.getCurrentState().addTransition(new Transition(modState, auxTrans.get(0).getC()));
                    auxTrans = removeAll(auxTrans, auxTrans.get(0).getC());
                    modStates.push(modState);
                } else if (!aux1.equals(res.getCurrentState())){
                    res.getStates().add(auxTrans.get(0).getTo());
                    res.getCurrentState().addTransition(auxTrans.get(0));
                    for (Transition transition : auxTrans) {
                        unmodifiedStates.add(transition.getTo());
                    }
                    auxTrans.remove(0);
                } else {
                    for (Transition transition : auxTrans) {
                        unmodifiedStates.add(transition.getTo());
                    }
                    break;
                }
            }
            if (!modStates.isEmpty()) {
                res.setCurrentState(modStates.peek());
                aux1 = modStates.pop();
            }
            else
                break;
        }
        addUnmodifiedStates(unmodifiedStates, res.getStates());
        return res;
    }

    private boolean containsMoreThanOneEqualTrans(List<Transition> transitions){
        Transition aux = transitions.get(0);
        for (int i = 1; i < transitions.size(); i++) {
            if (aux.getC() == transitions.get(i).getC())
                return true;
        }
        return false;
    }

    private List<Transition> removeAll(List<Transition> transitions, char c){
        List<Transition> result = new ArrayList<>();
        for (int i = 0; i < transitions.size(); i++) {
            if (transitions.get(i).getC() != c)
                result.add(transitions.get(i));
        }
        return result;
    }

    private boolean checkAcceptance(List<Transition> transitions){
        Transition aux = transitions.get(0);
        for (Transition transition : transitions) {
            if (aux.getC() == transition.getC() && transition.getTo().isAcceptance())
                return true;
        }
        return false;
    }

    private List<Transition> moddedTransitions(List<Transition> transitions){
        List<Transition> result = new ArrayList<>();
        Transition aux = transitions.get(0);
        for (Transition transition : transitions) {
            if (aux.getC() == transition.getC()) {
                List<Transition> auxTrans = transition.getTo().getTransitions();
                for (Transition newTrans: auxTrans) {
                   result.add(newTrans);
                }
            }
        }
        return result;
    }

    private void addUnmodifiedStates(List<State> unmodifiedStates, List<State> states){
        for (State state : unmodifiedStates) {
            while (!state.getTransitions().isEmpty()){
                states.add(state);
                state = state.getTransitions().get(0).getTo();
            }
            states.add(state);
        }

    }
}
