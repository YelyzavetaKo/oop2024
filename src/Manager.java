import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Manager {
    private Decrypter decrypter;
    private InputOutput inputOutput;
    private String dateiPfad;
    private String action;
    private Schluesselgenerator sg;
    public Manager(String dateiPfad, Schluesselgenerator sg, String action){
        this.dateiPfad = dateiPfad;
        inputOutput = new InputOutput();
        decrypter = new Decrypter();
        this.sg = sg;
        this.action = action;
    }

    public String getDateiPfad(){
        return dateiPfad;
    }

    public void setDateiPfad(String dateiPfad){
        this.dateiPfad = dateiPfad;
    }

    public void ablauf() throws IOException {
        String gedicht = inputOutput.readAllLines(dateiPfad);
        System.out.println(gedicht);
        ArrayList<Integer> numerics = new ArrayList<>();

        try {
            if (action.equals("encrypt")) {
                Encrypter encrypter = new Encrypter(sg.getP(), sg.getQ());
                int[] pubKey = encrypter.getKey();
                System.out.println("Public Key: " + pubKey[0] + " " + pubKey[1]);
                numerics = encrypter.encrypt(gedicht);
                dateiPfad = "src/output.txt";
            } else if (action.equals("decrypt")) {
                Decrypter decrypter = new Decrypter();
                numerics = decrypter.decrypt(sg.berechnePrivKEy(), gedicht);
                inputOutput.printOutNumeric(numerics);
                System.out.println();
                gedicht = decrypter.konvertiereZuString(numerics);
                System.out.println(gedicht);
                dateiPfad = "src/entschluesselt.txt";
            }

        } catch (Exception e) {
            System.out.println("Fehler beim Lesen der Datei");
        }
        try {
            inputOutput.writeToFile(dateiPfad, gedicht);
        } catch (Exception e) {
            System.out.println("Fehler beim Schreiben der Datei");
        }


    }

}
