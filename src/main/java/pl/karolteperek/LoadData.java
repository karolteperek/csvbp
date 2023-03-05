package pl.karolteperek;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoadData {


    private String source;
    private String destination;

    public LoadData(String source, String destination) {
        try {
            if (source.contains(".csv")) {
                this.source = source;
                this.destination = destination;
                loadData();
            }
        } catch (Exception e) {
            Alert.display("Bład", "Plik CSV nie został wybrany.");
        }
    }

    private void loadData() {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(source), Charset.forName("Cp1250"));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            new PrintCSV(csvParser, destination);

        } catch (IOException e) {
            Alert.display("Błąd odczytu", "Brak pliku.");
        }
    }
}


