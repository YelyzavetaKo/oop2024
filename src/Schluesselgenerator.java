import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;

public class Encrypter {

    int[] key;
    public Encrypter(int p, int q) throws notAPrimeException {
        Schluesselgenerator sg = new Schluesselgenerator(p,q);
        this.key = sg.berechnePubKey();
    }

    public int[] getKey(){
        return key;
    }

    public ArrayList<Integer> konvertiereInNumeric(String text){
        int numeric;
        ArrayList<Integer> numerics = new ArrayList<Integer>();
        for (int i = 0; i < text.length(); i++) {
            String s = String.valueOf(text.charAt(i));
            try {
                numeric = Byte.toUnsignedInt((s.getBytes("US-ASCII"))[0]);
                numerics.add(numeric);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            /*
            //throws SonderzeichenException //einfuegen
            if (numeric != -2) {
                numerics.add(numeric);
            } else {
                throw new SonderzeichenException();
            }
             */
        }
        System.out.println(numerics);
        return numerics;
    }

    public ArrayList<Integer> encrypt(int[] pubKey, @NotNull ArrayList<Integer> numerics) {
        ArrayList<Integer> encrypted = new ArrayList<Integer>();
        for (int i = 0; i < numerics.size(); i++) {
            BigInteger kPowE = new BigInteger(new BigInteger(Integer.toString(numerics.get(i))).pow(pubKey[0]).toByteArray());
            encrypted.add(kPowE.intValue() % pubKey[1]);
        }
        return encrypted;
    }
}

