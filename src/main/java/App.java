import model.Estate;
import model.EstateList;
import parsers.MorizonParser;
import parsers.OlxParser;

import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
            EstateList list = new EstateList();
            OlxParser olx = new OlxParser();
            MorizonParser morizon = new MorizonParser();
            olx.downloadEstates(list);
            Scanner sc = new Scanner(System.in);
        System.out.print("Podaj maksykalna cene: ");
        double max = sc.nextDouble();
        sc.nextLine();
            morizon.downloadEstates(list);
            list.getEstates().stream()
                    .filter(x -> x.getPrice() < 1800)
                    .sorted(Comparator.comparing(Estate::getPrice))
                    .forEach(System.out::println);

        System.out.println(list.getEstates().size());

    }


    }

