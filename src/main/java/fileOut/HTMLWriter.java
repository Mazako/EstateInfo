package fileOut;

import model.Estate;
import model.EstateList;

import java.io.*;
import java.util.List;

public class HTMLWriter {
    private StringBuilder document;
    public HTMLWriter() {
        document = new StringBuilder();
        document.append("<html>\n");
        document.append("<head>\n");
        document.append("<title>Nieruchomości</title>\n");
        document.append("<meta charset = \"UTF-8\"");
        document.append("</head\n");
        document.append("<body>\n");
    }

    public void writeList(List<Estate> list) {
        document.append("<h1>Lista nieruchomości</h1>\n");
        document.append("<p>").append(TextWriter.date()).append("</p>\n");
        document.append("<ul>\n");
        for (Estate estate : list) {
            document.append("<li> <a href = \"").append(estate.getUrl()).append("\">").append(estate.getUrl()).append("</a>").append(" Cena: ").append(estate.getPrice()).append("</li>\n");
        }
        document.append("</ul>");
    }

    public void saveToFile() {
        document.append("</body></html>");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("site.html"))) {
            writer.write(document.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
