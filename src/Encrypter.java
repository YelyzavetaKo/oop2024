import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Encrypter {

    int[] key;

    /** Constructor
     * Erstellt einen Schluesselgenerator, um mithilfe der Parameter zuer Verschluesselung einen oeffentlichen Schluessel zu erstellen.
     * @param p Primzahl zur Schluesselgenerierung.
     * @param q Primzahl zur Schluesselgenerierung.
     * @throws NotAPrimeException Falls p oder q keine Primzahlen sind, kann kein Schluessel gemaess RSA-Verfahren generiert werden, deshalb wird in diesem Fall ein Fehler ausgeworfen.
     */
    public Encrypter(int p, int q) throws NotAPrimeException {
        Schluesselgenerator sg = new Schluesselgenerator(p,q);
        this.key = sg.berechnePubKey();
    }

    /** Getter
     *
     * @return Gibt den oeffentlichen Schluessel zurueck, der der Verschluesselung dient.
     */
    public int[] getKey(){
        return key;
    }

    /**
     * Zeilenumbrueche werden mit -1 codiert. Andere Zeichen werden nach Umwandlung in ByteValues mittels RSA codiert.
     * @param plainText Zu codierender String.
     * @see InputOutput hat diesen eingelesen.
     * @return Gibt eine ArrayList mit allen in integer codierten Zeichen zurück.
     */
    public ArrayList<Integer> encrypt(String plainText){
        int numeric;
        int[] pubKey = getKey();
        ArrayList<Integer> encrypted = new ArrayList<Integer>();
        for (int i = 0; i < plainText.length(); i++){
            String s = String.valueOf(plainText.charAt(i));
            if (s.equals("\n")){
                encrypted.add(-1);
            } else {
                try {
                    numeric = Byte.toUnsignedInt(s.getBytes("US-ASCII")[0]);
                    encrypted.add(calculateModuloOfKPowE(numeric));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException("Zeichen <" + s + "> kann nicht codiert werden.");
                }
            }
        }
        return encrypted;
    }

    /**
     * Berechnet k hoch e fuer das RSA-Verfahren. Da die Zahlen recht groß werden koennen, wird mit dieser schrittweisen Berechnung eine unnoetige Typkonvertierung vermieden.
      * @param k ist der zu verschluesselnde int-Wert.
     * @return Gibt das Ergebnis der Verschluesselung zurueck.
     */
    public int calculateModuloOfKPowE(int k){
        int mod = 1;
        for (int i = 0; i < getKey()[0]; i++){
            mod = mod * k % getKey()[1];
            //System.out.println(mod);
        }
        return mod;
    }

}
