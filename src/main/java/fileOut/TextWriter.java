package fileOut;

import model.Estate;
import model.EstateList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class TextWriter {
    public static String fileName = "MIESZKANIA.TXT";


    public static String date() {
        LocalDateTime time = LocalDateTime.now();
        String date = time.getDayOfMonth() + " - " + time.getMonthValue() + " - " + time.getYear() + "  "
                + time.getHour() + ":" + time.getMinute();
        return "Wygenerowano: " + date;
    }

    public void saveToFile(List<Estate> list) {
        String header = date();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(header);
            writer.newLine();
            for (Estate estate : list) {
                writer.write(estate.toString());
                writer.newLine();
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
