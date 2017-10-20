package models;

import java.util.*;

/**
 * Created by Tomás Iturralde on 20/10/2017.
 * Materia: MateDis.
 */
public class NfaConverter {

    /**
    TODO:
    1. Agregar transicion entre nuevos estados(con estados anteriores).
    2. Agregar las transiciones de los estados compuestos por dos o mas estados anteriores.
    3. Lograr moverse correctamente a estados nuevos para seguir el algoritmo.
     */

    public DFA converter(NFA nfa){
        DFA res = new DFA();
        Stack<State> modStates = new Stack<>();
        State aux1 = nfa.getStates().get(0);
        while (!aux1.getTransitions().isEmpty()){
            List<Transition> auxTrans = aux1.getTransitions();
            while (!auxTrans.isEmpty()){
                if (containsMoreThanOneTrans(auxTrans)){
                    State modState = new State(checkAcceptance(auxTrans, aux1), nfa.getTotal()+1);
                    modState.setTransitions(moddedTransitions(auxTrans));
                    res.getStates().add(modState);
                    res.getCurrentState().addTransition(new Transition(modState, auxTrans.get(0).getC()));
                    auxTrans = removeAll(auxTrans, auxTrans.get(0).getC());
                    modStates.push(modState);
                } else {
                    res.getStates().add(auxTrans.get(0).getTo());
                    res.getCurrentState().addTransition(auxTrans.get(0));
                    auxTrans.remove(0);
                }
            }
            aux1 = modStates.pop();
        }
        return res;
    }

    private boolean containsMoreThanOneTrans(List<Transition> transitions){
        Transition aux = transitions.get(0);
        for (Transition transition : transitions) {
            if (aux.getC() == transition.getC())
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

    private boolean checkAcceptance(List<Transition> transitions, State state){
        if (state.isAcceptance())
            return true;
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
            if (aux.getC() == transition.getC())
                result.add(transition);
        }
        return result;
    }
}
