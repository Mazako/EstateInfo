package parsers;

import model.Estate;
import model.EstateList;
import model.Site;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class OtoDomParser implements Parsers {

    public static final String BASE_LINK = "https://www.otodom.pl/pl/oferty/wynajem/mieszkanie/wroclaw?distanceRadius=0&page=1&limit=300&market=ALL&ownerTypeSingleSelect=ALL&locations=%5Bcities_6-39%5D&viewType=listing&by=PRICE&direction=ASC";
    public static Site site = Site.OTODOM;

    @Override
    public Site getSite() {
        return Site.OTODOM;
    }

    @Override
    public void downloadEstates(EstateList estateList) {
        Document doc = Parsers.getRawSite(BASE_LINK);
        ArrayList<String> links = getEstateLinks(doc);
        ArrayList<Double> prices = getEstatePrices(doc);
        System.out.println(links);
        System.out.println(prices);
        for(int i = 0; i < links.size(); i++) {
            Estate estate = new Estate(links.get(i),prices.get(i),0, Site.OTODOM);
            if(estate.getUrl().contains("www.otodom.pl"))
                estateList.add(estate);
        }


    }

    private ArrayList<Double> getEstatePrices(Document doc) {
        ArrayList<String> result = new ArrayList<>();
        Elements select = doc.select("div[class=\"css-i38lnz eclomwz1\"]");
        for (Element element : select) {
            String first = element.getElementsByClass("css-rmqm02 eclomwz0").first().text();
            result.add(first);
        }

        return Parsers.parsePrices(result);
    }

    private ArrayList<String> getEstateLinks(Document doc) {
        ArrayList<String> result = new ArrayList<>();
        Elements select = doc.select("ul[class=\"css-14cy79a e3x1uf06\"]").select("li[class=\"css-p74l73 es62z2j17\"]").select("a");
        for (Element element : select) {
            result.add("https://www.otodom.pl" + element.attr("href"));
        }
        return result;
    }


}
