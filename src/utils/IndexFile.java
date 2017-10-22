package utils;

import models.Text;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class IndexFile {

    public IndexFile(){}

    public void makeIndexFile(List<Text> texts) {
        try{
            FileWriter fileWriter = new FileWriter("index.txt");
            for (Text text : texts) {
                fileWriter.write("Word: " + text.getText() + "\n");
                List<HTMLCounter> htmlCounters = text.getHtmlFiles();
                for (HTMLCounter htmlCounter : htmlCounters) {
                    fileWriter.write("File: \"" + htmlCounter.getHtmlFileName() + "\"\n");
                    fileWriter.write("Total of appearances: " + htmlCounter.getTotal() + "\n");
                }
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
