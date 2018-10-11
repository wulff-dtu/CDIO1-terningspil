package spil;
import java.util.Scanner;

public class BrugSpillet {

    public static Scanner in = new Scanner(System.in);

    //"input" bruges til at validere næsten alt input fra brugeren (spilleren).
    // Det eneste sted, vi ikke validerer, er ved indtastning af spillernes navn. Dette bør forbedres.

    private static int input(int antalValg) {

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

    //Viser "hovedmenuen", hvor man kan starte et spil, læse reglerne eller afslutte programmet.

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
                System.out.println("Spillet er er mellem to personer. De to spillere skiftes til at slå med to terninger. \n" +
                        "Summen af terningerne lægges til spillerens point. Når en spiller har 40 point eller derover, kan spilleren \n" +
                        "vinde næste gang, han/hun slår to ens. Hvis du slår to 6'ere, får du udover de 12 point en ekstra tur. \n" +
                        "Hvis du slår to 1'ere, mister du alle dine point. En anden måde at vinde på er, hvis du slår to 6'ere \n" +
                        "i to ture i træk. God fornøjelse!");
                visMenu();
                break;
            }
            case 3: {
                System.out.println("Farvel for denne gang!");
                in.close();
                break;
            }
        }
    }

    //Viser den menu, der fremkommer i hver tur i et spil.

    public static void nyTurMenu(Spiller spiller, Spil spil) {

        System.out.println(spiller.getNavn() + "s tur: Hvad vil du gøre?");
        System.out.println("1. Kast terningerne.");
        System.out.println("2. Se stillingen.");
        System.out.println("3. Giv op.");

        switch (input(3)) {
            case 1 : {
                spil.kastTerningerne(spiller, false, 0, 0);
                break;
            }
            case 2 : {
                visStillingen(spil);
                nyTurMenu(spiller, spil);
                break;
            }
            case 3 : {
                System.out.println(spiller.getNavn() + " gav op! Spillet er slut.");
                spil.stopSpil();
                break;
            }
        }
    }

    //Viser stillingen i et spil.

    private static void visStillingen(Spil spil) {
        System.out.println("Stillingen er:");
        System.out.println("    " + spil.getSpiller1().getNavn() + ": " + spil.getSpiller1().getPoint() + " point.");
        System.out.println("    " + spil.getSpiller2().getNavn() + ": " + spil.getSpiller2().getPoint() + " point.");
    }

    public static void main(String[] args) {

        visMenu();

    }

}
