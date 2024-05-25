public class Schluesselgenerator {

    private int p;
    private int q;
    private int[] privKey = new int[2];
    private int[] pubKey = new int[2];

    public Schluesselgenerator(int p, int q) throws notAPrimeException {
        if (!pruefeObPrimzahl(p)) {
            throw new notAPrimeException(p);
        } else if (!pruefeObPrimzahl(q)) {
            throw new notAPrimeException(q);
        } else {
            this.p = p;
            this.q = q;
        }
    }

    public int getP() {
        return p;
    }

    public int getQ() {
        return q;
    }

    public int getG() {
        return g;
    }

    public boolean pruefeObPrimzahl(int mbPrimeNumber) {
        boolean istPrimzahl = true;
        for (int i = 2; i <= mbPrimeNumber / 2; ++i) {
            if (mbPrimeNumber % i == 0) {
                istPrimzahl = false;
                break;
            }
        }
        return istPrimzahl;
    }

    public int berechneGeneratorzahl() {
        int g = getP() * getQ();
        return g;
    }

    public int berechnePhi() {
        int phi = (getP() - 1) * (getQ() - 1);
        return phi;
    }

    public int getPhi() {
        return phi;
    }

    public int findE() {
        int e = 2;
        while (berechneGGT(e, berechnePhi()) != 1) {
            e++;
        }
        return e;
    }

    public int findD() {
        int d = 0;
        while ((d * findE()) % berechnePhi() != 1) {
            d++;
        }
        return d;
    }

    public int berechneGGT(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return berechneGGT(b, a % b);
        }
    }

    public int[] berechnePubKey(){
        pubKey[0] = findE();
        pubKey[1] = berechneGeneratorzahl();
        return pubKey;
    }

    public int[] berechnePrivKEy(){
        privKey[0] = findD();
        privKey[1] = berechneGeneratorzahl();
        return privKey;
    }
}

