package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextReader {

    private List<Character> letters;
    private List<Character> firstLetters;

    public TextReader() {
        letters = new ArrayList<>();
        firstLetters = new ArrayList<>();
    }

    public void readFile(String file) {
        try{
            FileReader fileReader = new FileReader(file);
            int a = fileReader.read();
            while (a != -1) {
                Character character = (char) a;
                letters.add(Character.toLowerCase(character));
                a = fileReader.read();
            }
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Failed to read the file!");
        }
    }

    public void readHTMLFile(String htmlFile) {
        try{
            FileReader fileReader = new FileReader(htmlFile);
            int a = fileReader.read();
            while (a != -1) {
                //Condicion para fijarse que no sea tag.
                if(a == 60){
                    //Si empieza un tag, guardo todas los caracteres en una lista auxiliar
                    //hasta que sepa que si hay un closing tag los borro,
                    //pero si hay un open tag antes entonces significa que es texto y
                    //agrego la lista aux en la lista de letras!
                    List<Character> aux = new ArrayList<>();
                    aux.add((char) a);
                    a = fileReader.read();
                    while (a != 62){ //Mientras que no sea un tag!
                        if(a == 60){
                            letters.addAll(aux);
                            aux = new ArrayList<>();
                        } else aux.add((char) a);
                        a = fileReader.read();
                    }
                    a = fileReader.read();
                    continue;
                }
                Character character = (char) a;
                letters.add(Character.toLowerCase(character));
                a = fileReader.read();
            }
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Failed to read the file!");
        }
    }


    public List<Character> getLetters() {
        return letters;
    }

    public List<Character> getFirstLetters() {
        return firstLetters;
    }
}
