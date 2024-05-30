    public class NotAPrimeException extends Exception {

        public NotAPrimeException(int mbPrime){
            super("FEHLER: " + mbPrime + " ist keine Primzahl und kann nicht zur Schluesselgenerierung genutzt werden.");
        }
    }

