import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Manager {
    private Decrypter decrypter;
    private InputOutput inputOutput;
    private String dateiPfad;

    public Manager(String dateiPfad){
        this.dateiPfad = dateiPfad;
        inputOutput = new InputOutput();
        decrypter = new Decrypter();
    }

    public String getDateiPfad(){
        return dateiPfad;
    }

    public void setDateiPfad(String dateiPfad){
        this.dateiPfad = dateiPfad;
    }

    public void ablauf()  {
        String gedicht = "";
        try {
            gedicht = inputOutput.readAllLines(dateiPfad);
            System.out.println(gedicht);
        } catch (FileNotFoundException e) {
            System.out.println("Datei nicht gefunden");
        } catch (Exception e) {
            System.out.println("Fehler beim Lesen der Datei");
        }
        try {
            dateiPfad = "src/entschluesselt.txt";
            inputOutput.writeToFile(dateiPfad, gedicht);
        } catch (Exception e) {
            System.out.println("Fehler beim Schreiben der Datei");
        }


    }

}
