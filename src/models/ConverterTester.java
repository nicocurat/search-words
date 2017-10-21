package models;

import utils.DiagramFIleUtil;
import utils.TextReader;

/**
 * Created by Tomás Iturralde on 20/10/2017.
 * Materia: .
 */
public class ConverterTester {
    public static void main(String[] args) {
        TextReader txt = new TextReader();
        txt.readFile("hola.txt");
        NFA nfa = new NFA();
        nfa.addWords(txt.getLetters());
        DiagramFIleUtil nfaDiagram = new DiagramFIleUtil();
        nfaDiagram.create("nfa.dot");
        nfaDiagram.createDiagramNFA(nfa);
        NfaConverter converter = new NfaConverter();
        System.out.println("------------");
        DFA dfa = converter.converter(nfa);
        DiagramFIleUtil dfaDiagram = new DiagramFIleUtil();
        dfaDiagram.create("dfa.dot");
        dfaDiagram.createDiagramNFA(dfa);
    }
}
