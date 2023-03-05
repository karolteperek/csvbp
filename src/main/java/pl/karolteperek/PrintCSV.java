package pl.karolteperek;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVParser;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class PrintCSV {

    private CSVRecord csvRecord;

    private String output;

    public PrintCSV(CSVParser csvParser, String savePath) {
        this.output = savePath;
        if (new File(output).exists()){
            Alert.display("Błąd zapisu", "Podany plik już istnieje");
        } else {
            try{
                writeToFile(csvParser, new PrintStream(output));
            } catch (Exception e) {
                Alert.display("Bład inicjalizacji", e.toString());
            }
        }
    }

    private void writeToFile(CSVParser csvParser,PrintStream fileStream) {
        Double sum = 0.00d;

        List<String[]> strings = new ArrayList<String[]>();

        int i = 0;

        try {
            for(CSVRecord csvRecord : csvParser) {
                String operation = csvRecord.get(0);
                String currency = csvRecord.get(1);
                String transaction = csvRecord.get(2);
                String amount = csvRecord.get(3);
                String description = csvRecord.get(6);
                String address = csvRecord.get(7);
                String address_second = csvRecord.get(8);

                if (transaction.contains("Płatność") ||
                        transaction.contains("Obciążenie" ) ||
                        transaction.contains("Opłata") ||
                        transaction.contains("Przelew") ||
                        transaction.contains("Wpłata") ||
                        transaction.contains("Wypłata") ||
                        transaction.contains("Zwrot")) {
                    i=i+1;
                    String[] transactions = new String[8];
                    System.setOut(fileStream);
                    System.out.println("Numer operacji " + i);
                    System.out.println("---------------");
                    System.out.println("Data operacji : " + operation);
                    System.out.println("Data waluty : " + currency);
                    System.out.println("Typ transakcji : " + transaction);
                    System.out.println("Kwota : " + amount.substring(1));
                    System.out.println("Opis : " + description);
                    System.out.println("Adres : " + address);
                    System.out.println("Adres 2: " + address_second);
                    System.out.println("---------------\n\n");

                    transactions[0] = Integer.toString(i);
                    transactions[1] = operation;
                    transactions[2] = currency;
                    transactions[3] = transaction;
                    transactions[4] = amount.substring(1);
                    transactions[5] = description;
                    transactions[6] = address;
                    transactions[7] = address_second;
                    strings.add(transactions);

                    sum += Double.parseDouble(amount.substring(1));
                }
            }
            System.out.print("Łącznie : " );
            System.out.format("%.2f%n", sum);
            System.setOut(fileStream);
            ScrollableOutput.display("Wynik",strings);
            Alert.display("Ukończono", "Wynik)");
            fileStream.close();
        } catch (Exception e) {
            fileStream.close();
            try {
                Files.deleteIfExists(Paths.get(output));
            } catch (NoSuchFileException e1) {
                Alert.display("Błąd wewnętrzny", e1.toString());
            } catch (DirectoryNotEmptyException e2) {
                Alert.display("Błąd wewnętrzny", e2.toString());
            } catch (IOException e3) {
                Alert.display("Błąd wewnętrzny", e3.toString());
            }
            Alert.display("Błąd", "Nieprawidłowy plik csv lub uszkodzony");
        }
    }
}
