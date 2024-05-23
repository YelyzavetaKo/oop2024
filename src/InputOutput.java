import java.io.*;
import java.nio.charset.StandardCharsets;
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

}
