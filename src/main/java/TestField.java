import model.Estate;
import model.EstateList;
import parsers.ParserMachine;

import java.util.Comparator;

public class TestField {
    public static void main(String[] args) {
        EstateList list = new EstateList();
        ParserMachine.DownloadEstates(list);
        list.getEstates().stream()
                .filter(x -> x.getPrice() < 2000)
                .sorted(Comparator.comparing(Estate::getPrice))
                .distinct()
                .forEach(System.out::println);
    }
}
