import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class InputOutput {
    public InputOutput(){}

    /**
     * Liest den Inhalt einer Datei und gibt ihn als String zurück.
     * ISO_8859_1, da die Datei mit UTF-8 nicht gelesen werden kann.
     * @param dateiPfad Pfad zur Datei
     * @return Inhalt der Datei als String
     * @throws IOException
     */
    public String readAllLines(String dateiPfad) throws IOException {
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(dateiPfad, StandardCharsets.ISO_8859_1))) {
            return reader.lines()
                    .collect(Collectors.joining(System.lineSeparator()));
        }
    }

    public void writeToFile(String dateiPfad, String text) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(dateiPfad, StandardCharsets.ISO_8859_1));
        writer.write(text);
        writer.close();
    }

    public void printOutNumeric(ArrayList<Integer> numeric){
        for (int i = 0; i < numeric.size(); i++) {
            System.out.print(numeric.get(i) + " ");
        }
        System.out.println();
    }

    public String arrayToString(ArrayList<Integer> numeric){
        StringBuilder sb = new StringBuilder();
        numeric.forEach(num -> {
            sb.append(num).append(" ");
        });
        return sb.toString();
    }

}
