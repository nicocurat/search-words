package utils;

import models.NFA;
import models.State;
import models.Transition;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DiagramFIleUtil {

    private FileWriter fileWriterNFA;
    private FileWriter fileWriterDFA;

    public DiagramFIleUtil() {}

    public void create(){
        try{
            fileWriterNFA = new FileWriter("nfa.dot");
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void createDiagramNFA(NFA nfa) {
        try{
            fileWriterNFA.write("digraph { /n");
            List<State> states = nfa.getStates();
            //Iterate to write nodes on the file!
            for (State state : states) {
                writeNodeToFile(state);
            }
            for (State state: states) {
                writeTransitionToFile(state);
            }

            fileWriterNFA.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void writeTransitionToFile(State state) {
        String text;
//        Transition transition = state
//        try{
//            text = "Node" + state.getId() + " -> Node" + state.getId() + " [label=\""
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
    }

    private void writeNodeToFile(State state) {
        String text;
        try {
            if (state.isAcceptance()) {
                text = "node [shape=doublecircle] Node" + state.getId() +
                        " [label=\"" + state.getId() + "\"];";
            } else {
                text = "node [shape=circle] Node" + state.getId() +
                        " [label=\"" + state.getId() + "\"];";
            }
            fileWriterNFA.write(text);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
