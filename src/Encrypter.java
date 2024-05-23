import java.util.ArrayList;
import java.io.BufferedReader;

public class Encrypter {

    private int[] pubKey;

    this.Encrypter(int[] pubKey){
        this.pubKey = pubKey;
    }

    public ArrayList<Integer> konvertiereInNumveric(String text){
        BufferedReader reader = new BufferedReader(text);
        int r;
        ArrayList<Integer> numerics = new ArrayList<Integer>();
        while ((r = reader.read()) != -1){
            
            numerics.add(Character.getNumericValue(r));
        }
    }
}
