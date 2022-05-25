package model;

public class Estate {
    private String url;
    private double price;
    private double meters;
    private Site site;

    public Estate(String url, double price, double meters, Site site) {
        this.url = url;
        this.price = price;
        this.meters = meters;
        this.site = site;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMeters() {
        return meters;
    }

    public void setMeters(double meters) {
        this.meters = meters;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    @Override
    public String toString() {
        return "link: " + url + ", cena " + price + ", metraż: " + meters + ", portal: " + site;
    }
}
