import java.util.Arrays;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Decrypter {
    public Decrypter(){}

    public ArrayList<Integer> decrypt(int[] privKey, String text){
        System.out.println(privKey[0]);
        System.out.println(privKey[1]);
        ArrayList<Integer> numericChar = new ArrayList<>();
        String[] split = text.split(" ");
        numericChar.addAll(Arrays.stream(split).map(Integer::parseInt).collect(Collectors.toList()));
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
        numeric.forEach(num -> sb.append(num == -1 ? "\n" : new String(Character.toChars(num))));
        return sb.toString();
    }
}
