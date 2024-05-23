public class Schluesselgenerator {

    private int[] privKey = new int[2];
    private int[] pubKey = new int[2];
    int p;
    int q;

    public Schluesselgenerator(int p, int q){
        this.p = p;
        this.q = q;
        this.privKey = berechnePrivKey();
        this.pubKey = berechnePubKey();
    }

    public int getP(){
        return p;
    }

    public int getQ(){
        return q;
    }

    public void berechnePrivKey(){
        int[] privKey = new int[2];
        privKey[1] = findE();
        privKey[2] = berechneGeneratorzahl();
        this.privKey = privKey;
    }

    public void berechnePubKey(){
        int[] pubKey = new int[2];
        pubKey[1] = findD();
        pubKey[2] = berechneGeneratorzahl();
        this.pubKey = pubKey;
    }

    public int[] getPrivKey(){
        return privKey;
    }

    public int[] getPubKey(){
        return pubKey;
    }

    public int berechneGeneratorzahl(){
        return getP() * getQ();
    }

    public int berechneVarPhi(){
        return (getP() - 1) * (getQ() - 1);
    }

    public int berechneGGT(int a, int b){
        if (b == 0){
            return a;
        } else {
            return berechneGGT(b, a%b);
        }
    }

    public int findE(){
        int e = 0;
        while (berechneGGT(e, berechneVarPhi()) != 1){
            e++;
        }
        return e;
    }

    public int findD(){
        int d = 0;
        while ((d * findE())%berechneVarPhi() != 1){
            d++;
        }
        return d;
    }

}
