package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EstateList {
    private ArrayList<Estate> estates;

    public EstateList() {
        estates = new ArrayList<>();
    }

    public ArrayList<Estate> getEstates() {
        return estates;
    }
    public void add(Estate estate) {
        estates.add(estate);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Estate estate : estates) {
            builder.append(estate).append("\n");
        }
        return builder.toString();
    }

    public List<Estate> PriceLowerThan(double price) {
        return estates.stream()
                .filter(x -> x.getPrice() <= price)
                .sorted(Comparator.comparing(Estate::getPrice))
                .distinct()
                .collect(Collectors.toList());
    }
}
