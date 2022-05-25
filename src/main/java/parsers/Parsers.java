package parsers;

import model.EstateList;
import model.Site;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public interface Parsers {

    Site getSite();

    void downloadEstates(EstateList estateList);

    static ArrayList<Double> parsePrices(ArrayList<String> element) {
        return element.stream().map(Parsers::getExactPrice).collect(Collectors.toCollection(ArrayList::new));

    }

    private static double getExactPrice(String element) {
        element = element.trim();
        char[] charTab = element.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (char c : charTab) {
            if (Character.isDigit(c)) {
                builder.append(c);
            }
        }
        return Double.parseDouble(builder.toString());
    }

    static Document getRawSite(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  doc;
    }
}
