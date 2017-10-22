package utils;

import models.DFA;
import models.NFA;
import models.NfaConverter;
import models.Text;

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
        dfa = nfaConverter.converter(nfa);
    }

    public void searchText(List<String> htmlFileNames) {
        for (int i = 0; i < htmlFileNames.size(); i++) {
            textReader = new TextReader();
            textReader.readFile(htmlFileNames.get(i));
            searchInHtml(textReader.getLetters());
        }
    }

    private void searchInHtml(List<Character> characters) {
        for (int i = 0; i < characters.size(); i++) {
            modifyHTML(characters);
        }
    }

    private void modifyHTML(List<Character> characters) {
        List<Character> aux = new ArrayList<>();
        HTMLFileWriterUtil htmlFileWriterUtil = new HTMLFileWriterUtil();
        for (int i = 0; i < characters.size(); i++) {
            if((int) characters.get(i) == 60){ //60 = open tag

            }
        }
    }

//    private boolean isTag(String text) {
//
//    }

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
