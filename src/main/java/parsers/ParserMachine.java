package parsers;

import model.EstateList;

public class ParserMachine {
    private static Parsers[] parsers = {
            new DomiportaParser(),
            new MorizonParser(),
            new OlxParser(),
            new NieruchomosciOnlineParser(),
            new OtoDomParser()
    };

    public static void DownloadEstates(EstateList list) {
        for (Parsers parser : parsers) {

            System.out.println("Pobieram informacje z " + parser.getSite());
            parser.downloadEstates(list);
        }
    }
}
