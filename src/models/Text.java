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
        if(exists(htmlFile)) {

        } else {
            HTMLCounter htmlCounter = new HTMLCounter(htmlFile);
            htmlCounter.setTotal(htmlCounter.getTotal() + 1);
            htmlFiles.add(htmlCounter);
        }
    }

    private boolean exists(String htmlFile){
        for (int i = 0; i < htmlFiles.size(); i++) {
            if(htmlFile.equals(htmlFiles.get(i).getHtmlFileName())) {
                HTMLCounter htmlCounter = new HTMLCounter(htmlFile);
                htmlCounter.setTotal(htmlFiles.get(i).getTotal()+1);
                htmlFiles.set(i, htmlCounter);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Text{" +
                "text='" + text + '\'' +
                ", htmlFiles=" + htmlFiles.toString() +
                '}';
    }
}
