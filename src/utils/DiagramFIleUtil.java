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
            fileWriterNFA.write("digraph { \n\n");
            List<State> states = nfa.getStates();
            //Iterate to write nodes on the file!
            for (State state : states) {
                writeNodeToFile(state);
            }
            fileWriterNFA.write("\n");
            fileWriterNFA.write("Node0 -> Node0 [label=\"[otro]\"];\n");
            for (State state: states) {
                writeTransitionToFile(state);
            }
            fileWriterNFA.write("\n");
            fileWriterNFA.write("}");
            fileWriterNFA.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void writeTransitionToFile(State state) {
        String text;
        Transition transition;
        try{

            for (int i = 0; i < state.getTransitions().size(); i++) {
                transition = state.getTransitions().get(i);
                text = "Node" + state.getId() + " -> Node" + transition.getTo().getId() +
                        " [label=\"" + transition.getC() + "\"];\n";
                fileWriterNFA.write(text);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void writeNodeToFile(State state) {
        String text;
        try {
            if (state.isAcceptance()) {
                text = "node [shape=doublecircle] Node" + state.getId() +
                        " [label=\"" + state.getId() + "\"];\n";
            } else {
                text = "node [shape=circle] Node" + state.getId() +
                        " [label=\"" + state.getId() + "\"];\n";
            }
            fileWriterNFA.write(text);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
