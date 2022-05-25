package parsers;

import model.Estate;
import model.EstateList;
import model.Site;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class OlxParser implements Parsers {

    private static final String BASE_LINK = "https://www.olx.pl/nieruchomosci/mieszkania/wynajem/wroclaw/?search%5Border%5D=filter_float_price%3Aasc&page=";
    private static final String PRICE_TAG = "p[class =\"price\"]";
    private static final String ESTATE_CLASS = "offer-wrapper";
    private static final String LAST_PAGE = "a[data-cy=\"page-link-last\"]";
    public static Site site = Site.OLX;

    @Override
    public Site getSite() {
        return Site.OLX;
    }

    @Override
    public void downloadEstates(EstateList estateList) {
        int sites = getSites(BASE_LINK);
        for(int a = 1; a <= sites; a++) {
            String url = BASE_LINK + a;
            Document document = Parsers.getRawSite(url);
            ArrayList<String> links = getEstateLinks(document);
            ArrayList<Double> prices = getEstatePrices(document);
            for (int i = 0; i < links.size(); i++) {
                Estate estate = new Estate(links.get(i), prices.get(i), 0, Site.OLX);
                if (estate.getUrl().contains("www.olx.pl"))
                    estateList.add(estate);
            }
        }

    }

    private int getSites(String url) {
        Document doc = Parsers.getRawSite(url);
        String select = doc.select(LAST_PAGE).text();
        return Integer.parseInt(select);
    }

    private ArrayList<Double> getEstatePrices(Document document) {
        ArrayList<String> pricesTxt = new ArrayList<>();
        Elements elementsByClass = document.getElementsByClass(ESTATE_CLASS);
        for (Element byClass : elementsByClass) {
            pricesTxt.add(byClass.select(PRICE_TAG).first().text());
        }
        return Parsers.parsePrices(pricesTxt);
    }


    private ArrayList<String> getEstateLinks(Document doc) {
        ArrayList<String> links = new ArrayList<>();
        for (Element elementsByClass : doc.getElementsByClass(ESTATE_CLASS)) {
            String href = elementsByClass.select("a[href]").first().attr("href");
            links.add(href);
        }
        return links;
    }



}
