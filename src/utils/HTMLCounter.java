package utils;

public class HTMLCounter {

    private int total;
    private String htmlFileName;

    public HTMLCounter(String htmlFileName) {
        this.total = 0;
        this.htmlFileName = htmlFileName;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getHtmlFileName() {
        return htmlFileName;
    }

    @Override
    public String toString() {
        return "HTMLCounter{" +
                "total=" + total +
                ", htmlFileName='" + htmlFileName + '\'' +
                '}';
    }
}
