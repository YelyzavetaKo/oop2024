import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
public class Decrypter {
    public Decrypter(){}

    public ArrayList<Integer> decrypt(int[] privKey, ArrayList<Integer> numericChar){
        ArrayList<Integer> decrypted = new ArrayList<>();
        Iterator<Integer> it = numericChar.iterator();
        while(it.hasNext()){
            int current = it.next();
            //{d,g} = {privKey[0], privKey[1]}
            //decrypted = (current^d) mod g
            decrypted.add((int) Math.pow(current, privKey[0]) % privKey[1]);
        }
        return decrypted;
    }
}
