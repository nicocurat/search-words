package utils;

import models.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Searcher {

    private List<Text> texts;
    private NFA nfa = new NFA();
    private DFA dfa;
    private NfaConverter nfaConverter = new NfaConverter();
    private TextReader textReader = new TextReader();

    public Searcher(String searchTextFile) {
        texts = new ArrayList<>();
        initialize(searchTextFile);
    }

    /**
     * Agarra el archivo de texto con las palabras/frases que hay que buscar.
     * Crea el DFA correspondiente para buscar en los archivos HTML.
     * @param searchTextFile
     */
    private void initialize(String searchTextFile) {
        textReader.readFile(searchTextFile);
        nfa.addWords(textReader.getLetters());
        DiagramFIleUtil nfaDiagram = new DiagramFIleUtil();
        nfaDiagram.create("nfa.dot");
        nfaDiagram.createDiagramNFA(nfa);
        dfa = nfaConverter.converter(nfa);
        DiagramFIleUtil dfaDiagram = new DiagramFIleUtil();
        dfaDiagram.create("dfa.dot");
        dfaDiagram.createDiagramNFA(dfa);
    }

    public void searchText(List<String> htmlFileNames) {
        for (int i = 0; i < htmlFileNames.size(); i++) {
            textReader = new TextReader();
            textReader.readHTMLFile(htmlFileNames.get(i));
            searchInHtml(textReader.getLetters(), htmlFileNames.get(i));
        }
    }

    private void searchInHtml(List<Character> characters, String htmlFileName) {
        State currentState = dfa.getStates().get(0); //Starts at the first state wich is the one with state 0.
        StringBuilder word = new StringBuilder();
        Text text;
        for (int i = 0; i < characters.size(); i++) {
            if (isInDFA(characters.get(i), currentState)) {
                currentState = consumeInDFA(characters.get(i), currentState);
                word.append(characters.get(i));
                if (currentState.isAcceptance()) {
                    if (Character.isDigit(characters.get(i+1)) || Character.isAlphabetic(characters.get(i+1))){
                        word = new StringBuilder();
                        currentState = dfa.getStates().get(0);
                        while (characters.get(i) != 32){
                            i += 1;
                        }
                    } else {
                        if (doesTextExists(word.toString()) != -1) {
                            texts.get(doesTextExists(word.toString())).addHtmlFile(htmlFileName);
                        } else {
                            text = new Text(word.toString());
                            text.addHtmlFile(htmlFileName);
                            texts.add(text);
                        }
                        if (currentState.getTransitions().size() == 0) { //Se fija que el estado en el que esta parado sea el ultimo.
                            word = new StringBuilder();
                            currentState = dfa.getStates().get(0);
                        }
                    }
                }
            } else {
                word = new StringBuilder();
                currentState = dfa.getStates().get(0);
            }
        }
    }

    private int doesTextExists(String text) {
        for (int i = 0; i < texts.size(); i++) {
            if(text.equals(texts.get(i).getText())){
                return i;
            }
        }
        return -1;
    }

    private boolean isInDFA(char c, State currentState) {
        for (Transition transition : currentState.getTransitions()) {
            if(transition.getC() == c) return true;
        }
        return false;
    }

    private State consumeInDFA(char c, State state) {
        List<Transition> currentTransitions = state.getTransitions();
        for (Transition transition: currentTransitions) {
            if(transition.getC() == c) {
                state = transition.getTo();
            }
        }
        return state;
    }

    public List<Text> getTexts() {
        return texts;
    }

    public void setTexts(List<Text> texts) {
        this.texts = texts;
    }

    public NFA getNfa() {
        return nfa;
    }

    public void setNfa(NFA nfa) {
        this.nfa = nfa;
    }

    public DFA getDfa() {
        return dfa;
    }

    public void setDfa(DFA dfa) {
        this.dfa = dfa;
    }
}
