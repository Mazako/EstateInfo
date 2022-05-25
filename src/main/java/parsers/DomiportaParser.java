package parsers;

import model.Estate;
import model.EstateList;
import model.Site;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DomiportaParser implements Parsers{

    public static final String BASE_LINK = "https://www.domiporta.pl/mieszkanie/wynajme/dolnoslaskie/wroclaw?PageNumber=";
    public Site site = Site.DOMIPORTA;


    @Override
    public Site getSite() {
        return Site.DOMIPORTA;
    }

    @Override
    public void downloadEstates(EstateList estateList) {
        int sites = getSites(BASE_LINK);
        for(int i = 1; i<= sites; i++) {
            String url = BASE_LINK + i;
            Document doc = Parsers.getRawSite(url);
            ArrayList<String> links = getEstateLinks(doc);
            ArrayList<Double> prices = getEstatePrices(doc);
            for(int j = 0; j < links.size(); j++) {
                Estate estate = new Estate(links.get(j),prices.get(j),0, Site.DOMIPORTA);
                estateList.add(estate);
            }
        }
    }

    private ArrayList<Double> getEstatePrices(Document doc) {
        ArrayList<String> txtPrice = new ArrayList<>();
        Elements select = doc.select("div[class=\"sneakpeak__priceRolled\"]");
        for (Element element : select) {
            txtPrice.add(element.select("span").first().text());
        }
        return Parsers.parsePrices(txtPrice);
    }

    private ArrayList<String> getEstateLinks(Document doc) {
        ArrayList<String> result = new ArrayList<>();
        Elements select = doc.select("article[class=\"sneakpeak \"]");
        for (Element element : select) {
            String attr = Objects.requireNonNull(element.select("a").first()).attr("href");
            String link = "https://www.domiporta.pl" + attr;
            result.add(link);
        }
        return result;
    }

    private int getSites(String baseLink) {
        Document doc = Parsers.getRawSite(baseLink);
        Pattern pattern = Pattern.compile("\\d+");
        Elements select = doc.select("ul[class=\"pagination\"]").select("li");
        int i = 0;
        for (Element element : select) {
            Matcher matcher = pattern.matcher(element.text());
            if(matcher.matches())
                i++;
        }
        return i;
    }
}
