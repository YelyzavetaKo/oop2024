import java.rmi.server.ExportException;

public class ProductOfPQTooLittleException
        extends ExportException {

    public ProductOfPQTooLittleException(int p, int q){
        super("FEHLER: p und q muessen miteinander multipliziert groesser als 255 sein, um den kompletten Zeichensatz kodieren zu koennen.");
    }
}

