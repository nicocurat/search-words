package models;

import utils.HTMLCounter;

import java.util.ArrayList;
import java.util.List;

public class Text {

    private String text;
    private List<HTMLCounter> htmlFiles; //Archivos en los que aparecio

    public Text(String text) {
        this.text = text;
        htmlFiles = new ArrayList<>();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<HTMLCounter> getHtmlFiles() {
        return htmlFiles;
    }

    public void addHtmlFile(String htmlFile) {
        htmlFiles.add(new HTMLCounter(htmlFile));
    }
}
