public class Schluesselgenerator {

    private int p;
    private int q;
    private int[] privKey = new int[2];
    private int[] pubKey = new int[2];

    /** Constructor
     * Setzt die Parameter zur Schluesselberechnung, sofern die Primzahlbedingung fuer beide Parameter erfuellt ist.
     * @param p Primzahl zur Schluesselgenerierung.
     * @param q Primzahl zuer Schluesselgenerierung.
     * @throws NotAPrimeException Wird geworfen, wenn p oder q keine Primzahl sind.
     */
    public Schluesselgenerator(int p, int q) throws NotAPrimeException, ProductOfPQTooLittleException {
        if (!pruefeObPrimzahl(p)) {
            throw new NotAPrimeException(p);
        } else if (!pruefeObPrimzahl(q)) {
            throw new NotAPrimeException(q);
        } else if (berechneGeneratorzahl() < 255){
            throw new ProductOfPQTooLittleException(p, q);
        } else {
            this.p = p;
            this.q = q;
        }
    }

    /**
     * Gibt die Primzahl p zurueck.
     * @return Gibt p zurueck.
     */
    public int getP() {
        return p;
    }

    /**
     * Gibt die Primzahl q zurueck.
     * @return Gibt q zurueck.
     */
    public int getQ() {
        return q;
    }

    /**
     * Prueft, ob die eingegebene Zahl eine Primzahl ist.
     * @param mbPrimeNumber ist moeglicherweise ein Primzahl.
     * @return Gibt true zurueck, wenn mbPrimenumber eine Primzahl ist und false, wenn sie es nicht ist.
     */
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

    /**
     * Berechnet die Generatorzahl zur Schluesselgenerierung.
     * @return Gibt die Generatorzahl g zurueck.
     */
    public int berechneGeneratorzahl() {
        int g = getP() * getQ();
        return g;
    }

    /**
     * Berechnet phi fuer das RSA-Verfahren.
     * @return Gibt phi zurueck.
     */
    public int berechnePhi() {
        int phi = (getP() - 1) * (getQ() - 1);
        return phi;
    }

    /**
     * Findet die erste passende Zahl, fuer welche gilt: ggT(e, phi) = 1.
     * @return Gibt die Zahl e zureuck.
     */
    public int findE() {
        int e = 2;
        while (berechneGGT(e, berechnePhi()) != 1) {
            e++;
        }
        return e;
    }

    /**
     * Findet die erste passende Zahl fuer welche gilt: d * e % phi = 1.
     * @return Gibt die Zahl d zureuck.
     */
    public int findD() {
        int d = 0;
        while ((d * findE()) % berechnePhi() != 1) {
            d++;
        }
        return d;
    }

    /**
     * Berechnet den ggT zweier int Werte.
     * @param a erster int-Wert zur Ueberpruefung.
     * @param b zweiter int-Wert zur Ueberpruefung.
     * @return gibt den ggT von a und b aus.
     */
    public int berechneGGT(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return berechneGGT(b, a % b);
        }
    }

    /**
     * Findet einen oeffentlichen Schluessel nach dem RSA-Verfahren.
     * @return Gibt den oeffentlichen Schluessel als int[] zureuck. int[0] = e, int[1] = g
     */
    public int[] berechnePubKey(){
        pubKey[0] = findE();
        pubKey[1] = berechneGeneratorzahl();
        return pubKey;
    }

    /**
     * Findet einen privaten Schluessel nach dem RSA-Verfahren.
     * @return Gibt den privaten Schluessel als int[] zurueck. int[0] = d, int[1] = 1.
     */
    public int[] berechnePrivKey(){
        privKey[0] = findD();
        privKey[1] = berechneGeneratorzahl();
        return privKey;
    }
}
