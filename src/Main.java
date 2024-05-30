import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Schluesselgenerator sg = new Schluesselgenerator(11, 17);
            String action = "decrypt";
            Manager manager = new Manager("src/output.txt", sg, action);
            manager.ablauf();
        } catch (NotAPrimeException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

