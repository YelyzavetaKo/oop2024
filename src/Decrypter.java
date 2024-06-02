import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
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
            if(decryptedChar == -1){
                System.out.println();
            } else { System.out.println(decryptedChar);
            }
            decryptedChar = calculateModuloOfKPowE(decryptedChar, privKey);


            if(decryptedChar == -1){
                System.out.println();
            } else { System.out.println(decryptedChar);
            }
            decrypted.add(decryptedChar);

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
        //ISO_8859_1
        numeric.forEach(num -> {
            if(num == -1){
                sb.append("\n");
            } else {
                byte[] bytes = new byte[]{num.byteValue()};
                sb.append(new String(bytes, StandardCharsets.ISO_8859_1));
            }
        });
        return sb.toString();
    }
}
