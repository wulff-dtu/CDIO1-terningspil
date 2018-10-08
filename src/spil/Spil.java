package spil;

public class Spil {

    private Spiller spiller1;
    private Spiller spiller2;
    private Terning terning1 = new Terning();
    private Terning terning2 = new Terning();
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

        int t1Resultat = terning1.slaaTerning();
        int t2Resultat = terning2.slaaTerning();
        int kastSum = t1Resultat + t2Resultat;

        System.out.println(spiller.getNavn() + " slog en " + t1Resultat + "'er og en " + t2Resultat + "'er.");

        if ((kastSum == 12) && spiller.harKastet2Seksere) {

            stopSpil();
            System.out.println(spiller.getNavn() + " har vundet spillet!");

        } else if ((t1Resultat == 1) && (t2Resultat == 1)) {

            spiller.setPoint(0);
            System.out.println(spiller.getNavn() + " mistede alle sine point!");
            spiller.harPasseret40Point = false;
            spiller.harKastet2Seksere = false;

        } else if ((spiller.getPoint() >= 40) && t1Resultat == t2Resultat) {

            stopSpil();
            System.out.println(spiller.getNavn() + " har vundet spillet!");

        } else {

            int spillersNyePoint = spiller.getPoint() + kastSum;
            spiller.setPoint(spillersNyePoint);

            System.out.println(spiller.getNavn() + " fik " + kastSum + " point og har nu i alt " + spiller.getPoint() + " point!");

            if (spiller.getPoint() >= 40 && !spiller.harPasseret40Point) {

                System.out.println(spiller.getNavn() + " har nu 40 eller flere point og kan vinde ved at slå to ens!");
                spiller.harPasseret40Point = true;

            }

            if (t1Resultat == t2Resultat) {

                System.out.println(spiller.getNavn() + " slog to ens, og får derfor en ekstra tur.");

                if (kastSum == 12) {
                    spiller.harKastet2Seksere = true;
                }

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

        spillerRaekkefoelge = terning1.slaaTerning() > 3;

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
