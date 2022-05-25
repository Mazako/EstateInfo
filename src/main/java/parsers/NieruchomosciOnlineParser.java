package parsers;

import model.Estate;
import model.EstateList;
import model.Site;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class NieruchomosciOnlineParser implements Parsers {

    public static final String BASE_LINK = "https://www.nieruchomosci-online.pl/szukaj.html?3,mieszkanie,wynajem,,Wroc%C5%82aw&p=";
    public final Site site = Site.NIERUCHOMOSCI_ONLINE;

    @Override
    public Site getSite() {
        return Site.NIERUCHOMOSCI_ONLINE;
    }

    @Override
    public void downloadEstates(EstateList estateList) {
        int i = 1;
        String url = BASE_LINK + i;
        Document doc;
        do {
            doc = Parsers.getRawSite(url);
            ArrayList<String> links = getEstateLinks(doc);
            ArrayList<Double> prices = getEstatePrices(doc);
            for(int j = 0; j < links.size(); j++){
                Estate estate = new Estate(links.get(j),prices.get(j),0, Site.NIERUCHOMOSCI_ONLINE);
                if (estate.getUrl().contains("wroclaw.nieruchomosci-online.pl"))
                    estateList.add(estate);
            }

            i++;
            url = BASE_LINK + i;
        }while(!doc.text().contains("archiwalne"));

    }

    private ArrayList<Double> getEstatePrices(Document doc) {
        ArrayList<String> pricesTxt = new ArrayList<>();
        Elements select = doc.select("p[class=\"title-a primary-display\"]");
        for (Element element : select) {
            pricesTxt.add(element.select("span").first().text());
        }

        return Parsers.parsePrices(pricesTxt);
    }

    private ArrayList<String> getEstateLinks(Document doc) {
        ArrayList<String> result = new ArrayList<>();
        Elements select = doc.select("h2[class=\"name\"]").select("a");
        for (Element element : select) {
            result.add(element.attr("href"));
        }
        return result;
    }
}
