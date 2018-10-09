package spil;

public class Spil {

    private Spiller spiller1;
    private Spiller spiller2;
    private Terninger terninger = new Terninger();
    private boolean spilIgang;

    public Spil() {}

    private void nyTur(Spiller spiller) {

        System.out.println(spiller.getNavn() + "s tur: Hvad vil du gøre?");
        System.out.println("1. Kast terningerne.");
        System.out.println("2. Se stillingen.");
        System.out.println("3. Giv op.");

        switch (BrugSpillet.input(3)) {
            case 1 : {
                kastTerningerne(spiller);
                break;
            }
            case 2 : {
                visStillingen();
                nyTur(spiller);
                break;
            }
            case 3 : {
                System.out.println(spiller.getNavn() + " gav op! Spillet er slut.");
                stopSpil();
                break;
            }
        }

    }

    private void visStillingen() {
        System.out.println("Stillingen er:");
        System.out.println("    " + spiller1.getNavn() + ": " + spiller1.getPoint() + " point.");
        System.out.println("    " + spiller2.getNavn() + ": " + spiller2.getPoint() + " point.");
    }

    private void kastTerningerne(Spiller spiller) {

        terninger.slaaTerninger();

        int t1Vaerdi = terninger.getT1Vaerdi();
        int t2Vaerdi = terninger.getT2Vaerdi();
        int sum = terninger.getSum();
        boolean erEns = terninger.erEns();

        System.out.println(spiller.getNavn() + " slog en " + t1Vaerdi + "'er og en " + t2Vaerdi + "'er.");

        if ((sum == 12) && spiller.harKastet2Seksere) {

            stopSpil();
            System.out.println(spiller.getNavn() + " har vundet spillet!");

        } else if ((t1Vaerdi == 1) && (t2Vaerdi == 1)) {

            spiller.setPoint(0);
            System.out.println(spiller.getNavn() + " mistede alle sine point!");
            spiller.harPasseret40Point = false;
            spiller.harKastet2Seksere = false;

        } else if ((spiller.getPoint() >= 40) && erEns) {

            stopSpil();
            System.out.println(spiller.getNavn() + " har vundet spillet!");

        } else {

            int spillersNyePoint = spiller.getPoint() + sum;
            spiller.setPoint(spillersNyePoint);

            System.out.println(spiller.getNavn() + " fik " + sum + " point og har nu i alt " + spiller.getPoint() + " point!");

            if (spiller.getPoint() >= 40 && !spiller.harPasseret40Point) {

                System.out.println(spiller.getNavn() + " har nu 40 eller flere point og kan vinde ved at slå to ens!");
                spiller.harPasseret40Point = true;

            }

            spiller.harKastet2Seksere = (sum == 12);

            if (erEns) {

                System.out.println(spiller.getNavn() + " slog to ens, og får derfor en ekstra tur.");

                nyTur(spiller);

            }
        }
    }

    public void startSpil() {

        spiller1 = new Spiller();
        spiller2 = new Spiller();
        boolean spillerRaekkefoelge;

        System.out.print("Indtast navn på spiller 1: ");
        spiller1.setNavn(BrugSpillet.in.next());

        System.out.print("Indtast navn på spiller 2: ");
        spiller2.setNavn(BrugSpillet.in.next());

        terninger.slaaTerninger();
        spillerRaekkefoelge = terninger.getT1Vaerdi() > 3;

        spilIgang = true;

        System.out.println("Lad spillet starte!");

        String spillerDerStartersNavn = spillerRaekkefoelge ? spiller1.getNavn() : spiller2.getNavn();

        System.out.println(spillerDerStartersNavn + " er valgt tilfældigt til at starte.");

        while(spilIgang) {

            if (spillerRaekkefoelge) {

                nyTur(spiller1);
                if (spilIgang) {
                    nyTur(spiller2);
                }

            } else {

                nyTur(spiller2);
                if (spilIgang) {
                    nyTur(spiller1);
                }
            }
        }
    }

    private void stopSpil() {

        spilIgang = false;

    }

}
