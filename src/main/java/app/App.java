package app;

import fileOut.HTMLWriter;
import fileOut.TextWriter;
import model.Estate;
import model.EstateList;
import parsers.MorizonParser;
import parsers.OlxParser;
import parsers.ParserMachine;

import javax.swing.text.html.HTML;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        EstateList list = new EstateList();
        ParserMachine.DownloadEstates(list);
        System.out.println("Podaj maksymalna cene: ");
        double maxprice = sc.nextDouble();
        List<Estate> result = list.PriceLowerThan(maxprice);
        HTMLWriter writer = new HTMLWriter();
        writer.writeList(result);
        writer.saveToFile();
        System.out.println("Zapisano dane do pliku");


    }


}

