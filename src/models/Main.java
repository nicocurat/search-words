package models;

import utils.IndexFile;
import utils.Searcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write the .txt file's directory with the words to be searched:");
        String text = scanner.next();
        Searcher searcher = new Searcher(text);
        List<String> htmlFiles = new ArrayList<>();
        System.out.println("Write the .html files in which the words are going to be searched (Write -1 to close" +
                "the loading of files): ");
        text = scanner.next();
        while (!text.equals("-1")){
            htmlFiles.add(text);
            text = scanner.next();
        }
        searcher.searchText(htmlFiles);
        IndexFile indexFile = new IndexFile();
        indexFile.makeIndexFile(searcher.getTexts());
        System.out.println("Searching done! Look for the files nfa.dot or dfa.dot to see the automatons created,\n" +
                "or look for the file index.txt for the amount of words found in the .html files.");
    }
}
