package models;

import utils.TextReader;

import java.util.List;

public class Reader {

    private NFA nfa;
    private TextReader reader;

    public Reader(NFA nfa, TextReader reader) {
        this.nfa = nfa;
        this.reader = reader;
    }

    public void addNFA(){
        List<Character> letters = reader.getLetters();
        nfa.addWords(letters);
    }
}
