package models;

import utils.TextReader;

/**
 * Created by Tomás Iturralde on 20/10/2017.
 * Materia: .
 */
public class ConverterTester {
    public static void main(String[] args) {
        TextReader txt = new TextReader();
        txt.readFile("test.txt");
        NFA nfa = new NFA();
        nfa.addWords(txt.getLetters());
        nfa.getStates().forEach(System.out::println);
        NfaConverter converter = new NfaConverter();
        System.out.println("------------");
        DFA dfa = converter.converter(nfa);
        dfa.getStates().forEach(System.out::println);
    }
}
