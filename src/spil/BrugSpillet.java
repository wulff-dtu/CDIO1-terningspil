package spil;
import java.util.Scanner;

public class BrugSpillet {

    public static Scanner in = new Scanner(System.in);

    public static int input(int antalValg) {

        int input = 0;
        boolean godkendtInput = false;

        System.out.print("Indtast dit valg: ");

        do {
            if (in.hasNextInt()) {

                input = in.nextInt();

                if ((input > 0) && (input < antalValg + 1)) {
                    godkendtInput = true;
                } else {
                    System.out.print("Ugyldigt input. Prøv igen: ");
                    in.nextLine();
                }
            } else {
                System.out.print("Ugyldigt input. Prøv igen: ");
                godkendtInput = false;
                in.next();
            }
        } while (!(godkendtInput));

        return input;

    }

    private static void visMenu() {

        System.out.println("Menu:");
        System.out.println("1. Start spillet");
        System.out.println("2. Læs reglerne");
        System.out.println("3. Afslut programmet.");

        switch (input(3)) {
            case 1: {
                Spil spil = new Spil();
                spil.startSpil();
                visMenu();
                break;
            }
            case 2: {
                System.out.println("Spillet er et spil mellem to personer. De to spillere skiftes til at slå med to terninger. \n" +
                        "Summen af terningerne lægges til spillerens point. Når en spiller har 40 point eller derover, kan spilleren \n" +
                        "vinde næste gang, han/hun slår to ens. Hvis du slår to 6'ere, får du udover de 12 point en ekstra tur. \n" +
                        "Hvis du slår to 1'ere, mister du alle dine point. En anden måde at vinde på er, hvis du slår to 6'ere \n" +
                        "i to ture i træk. God fornøjelse!");
                visMenu();
                break;
            }
            case 3: {
                System.out.println("Farvel for denne gang!");
                break;
            }
        }

    }

    public static void main(String[] args) {

        visMenu();

    }
}
