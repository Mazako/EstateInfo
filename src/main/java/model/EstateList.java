package model;

import java.util.ArrayList;

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
}
