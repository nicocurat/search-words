package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Words {
    private List<String> wordsToSearch;
    private List<String> firstLetters;

    public Words(){
        wordsToSearch = new ArrayList<>();
        firstLetters = new ArrayList<>();
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
                List<String> wordsToList = new ArrayList<>(Arrays.asList(wordsRead));
                for (int i = 0; i < wordsToList.size(); i++) {
                    String aWordsRead = wordsToList.get(i);
                    for (int j = 0; j < aWordsRead.length(); j++) {
                        if (aWordsRead.charAt(j) < 97 && aWordsRead.charAt(j) > 122) {
                            wordsToList.remove(aWordsRead);
                            break;
                        }
                    }
                }
                for (String word:
                     wordsToList) {
                    wordsToSearch.add(word);
                }
                firstLetters.add("" + wordsToList.get(0).charAt(0));
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
