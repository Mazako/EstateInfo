package parsers;

import model.Estate;
import model.EstateList;
import model.Site;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class MorizonParser implements Parsers {

    public final Site site = Site.MORIZON;
    public static final String ESTATE_CLASS = "mz-card__item";

    private static final String BASE_LINK = "https://www.morizon.pl/do-wynajecia/mieszkania/najtansze/wroclaw/?page=";

    @Override
    public Site getSite() {
        return Site.MORIZON;
    }

    @Override
    public void downloadEstates(EstateList estateList) {
        int sites = getSites(BASE_LINK);
        for (int a = 1; a <= sites; a++) {
            String url = BASE_LINK + a;
            Document document = Parsers.getRawSite(url);
            ArrayList<String> links = getEstateLinks(document);
            ArrayList<Double> prices = getEstatePrices(document);
            for (int i = 0; i < links.size(); i++) {
                Estate estate = new Estate(links.get(i), prices.get(i), 0, Site.MORIZON);
                if (estate.getUrl().contains("www.morizon.pl"))
                    estateList.add(estate);
            }
        }
    }

    private ArrayList<Double> getEstatePrices(Document document) {
        ArrayList<String> pricesTxt = new ArrayList<>();
        Elements select = document.getElementsByClass(ESTATE_CLASS).select("p[class=\"single-result__price\"]");
        for (Element element : select) {
            pricesTxt.add(element.text());
        }

        return Parsers.parsePrices(pricesTxt);
    }

    private ArrayList<String> getEstateLinks(Document document) {
        ArrayList<String> links = new ArrayList<>();
        Elements select = document.getElementsByClass(ESTATE_CLASS).select("a[class=\"property_link property-url\"]");
        for (Element element : select) {
            links.add(element.attr("href"));
        }
        return links;
    }

    private int getSites(String url) {
        Document doc = Parsers.getRawSite(url);
        String[] pages = doc.select("ul[class=\"nav nav-pills mz-pagination-number\"]").text().split(" ");
        return Integer.parseInt(pages[pages.length-1]);
    }
}
