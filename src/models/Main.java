package models;

import utils.IndexFile;
import utils.Searcher;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Searcher searcher = new Searcher("hola.txt");
        List<String> htmlFiles = new ArrayList<>();
        htmlFiles.add("test.html");
        htmlFiles.add("t.html");
        searcher.searchText(htmlFiles);
        IndexFile indexFile = new IndexFile();
        indexFile.makeIndexFile(searcher.getTexts());
        searcher.getTexts().forEach(System.out::println);
    }
}
