import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
public class Decrypter {
    public Decrypter(){}

    public ArrayList<Integer> decrypt(int[] privKey, String text){
        System.out.println(privKey[0]);
        System.out.println(privKey[1]);
        ArrayList<Integer> numericChar = new ArrayList<>();
        String[] split = text.split(" ");
        for (int i = 0; i < split.length; i++) {
            numericChar.add(Integer.parseInt(split[i]));
        }
        ArrayList<Integer> decrypted = new ArrayList<>();
        Iterator<Integer> it = numericChar.iterator();
        while(it.hasNext()){
            Integer decryptedChar= it.next();
            decrypted.add(calculateModuloOfKPowE(decryptedChar, privKey));
        }
        return decrypted;
    }

    public Integer calculateModuloOfKPowE(int v, int[] key){
        Integer mod = 1;
        for (int i = 0; i < key[0]; i++){
            mod = mod * v % key[1];
            //System.out.println(mod);
        }
        return mod;
    }

    public String konvertiereZuString(ArrayList<Integer> numeric){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numeric.size(); i++) {
            if (numeric.get(i) == -1){
                sb.append("\n");
            } else {
                sb.append((char)numeric.get(i).intValue());
            }
        }
        return sb.toString();
    }
}
