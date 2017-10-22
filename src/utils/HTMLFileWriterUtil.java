package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Will be used to create new html file from existing one but without tags.
 */
public class HTMLFileWriterUtil {

    private FileWriter fileWriter;

    public HTMLFileWriterUtil() {}

    public void create(String fileName){
        try{
            fileWriter = new FileWriter(fileName);
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void write(List<Character> characters){
        try{
            for (Character character : characters) {
                fileWriter.write(character);
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
