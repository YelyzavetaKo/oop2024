import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
       try {
            Schluesselgenerator sg = new Schluesselgenerator(11, 17);
            Decrypter decrypter = new Decrypter();
            String action = "decrypt";
            Manager manager = new Manager("src/output.txt", sg, action);
            manager.ablauf();
            /**int[] key = sg.berechnePrivKEy();
            int[] pubKey = sg.berechnePubKey();
            System.out.println("Public Key: " + pubKey[0] + " " + pubKey[1]);
            Integer testValue = decrypter.calculateModuloOfKPowE(109, key);
            System.out.println();
            System.out.println(testValue);
            ArrayList<Integer> numeric = new ArrayList<>();
            numeric.add(252);
            numeric.add(223);
            String tttt = decrypter.konvertiereZuString(numeric);
            System.out.println(tttt);
             */
        } catch (NotAPrimeException e) {
           System.out.println(e.getMessage());
       } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

