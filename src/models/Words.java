package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Words {
    private List<String> wordsToSearch;
    private List<String> firstLetters;

    public Words(int amtOfWords){
        wordsToSearch = new ArrayList<>(amtOfWords);
        firstLetters = new ArrayList<>(amtOfWords);
    }

    public void wordReader(String fileFrom){
        try {
            FileReader fr = new FileReader(fileFrom);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            String[] wordsRead;
            while(line != null){
                line = line.toLowerCase();
                wordsRead = line.split(" ");
                Collections.addAll(wordsToSearch, wordsRead);
                firstLetters.add("" + line.charAt(0));
                line = br.readLine();
            }
            br.close();
            fr.close();
        }catch (IOException e){
            System.out.println("Fail!");
        }
    }

    public List<String> getWordsToSearch() {
        return wordsToSearch;
    }

    public List<String> getFirstLetters() {
        return firstLetters;
    }
}
