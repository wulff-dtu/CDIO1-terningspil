package spil;

public class Spil {

    private Spiller spiller1;
    private Spiller spiller2;
    private Terninger terninger = new Terninger();
    private boolean spilIgang;

    public Spil() {}

    public Spiller getSpiller1() {
        return spiller1;
    }

    public Spiller getSpiller2() {
        return spiller2;
    }

    public void setSpiller1(Spiller spiller1) {
        this.spiller1 = spiller1;
    }

    //setSpiller2 bruges pt. ikke til noget, men kan evt. bruges til eventuelle nye tests.
    public void setSpiller2(Spiller spiller2) {
        this.spiller2 = spiller2;
    }

    public boolean erSpilIgang() {
        return spilIgang;
    }

    public void setSpilIgang(boolean spilIgang) {
        this.spilIgang = spilIgang;
    }

    //"kastTerningerne" er den primære funktion i spillet, der tager sig af
    //de forskellige udfald, som de to terninger kan give.
    //Parameterne "testTilstand", "t1Test" og "t2Test" bruges, når vi tester spillet.

    public void kastTerningerne(Spiller spiller, Boolean testTilstand, int t1Test, int t2Test) {

        terninger.slaaTerninger();

        if (testTilstand) {
            terninger.setT1Vaerdi(t1Test);
            terninger.setT2Vaerdi(t2Test);
            terninger.setSum(t1Test + t2Test);
        }

        int t1Vaerdi = terninger.getT1Vaerdi();
        int t2Vaerdi = terninger.getT2Vaerdi();
        int sum = terninger.getSum();
        boolean erEns = terninger.erEns();

        System.out.println(spiller.getNavn() + " slog en " + t1Vaerdi + "'er og en " + t2Vaerdi + "'er.");

        //Hvis en spiller kastede to 6'ere i sidste tur, og kaster to 6'ere igen.
        if ((sum == 12) && spiller.isHarKastet2Seksere()) {

            stopSpil();
            System.out.println(spiller.getNavn() + " har vundet spillet!");

        //Hvis en spiller slår to 1'ere og mister alle sine point.
        } else if ((t1Vaerdi == 1) && (t2Vaerdi == 1)) {

            spiller.setPoint(0);
            System.out.println(spiller.getNavn() + " mistede alle sine point!");
            spiller.setHarPasseret40Point(false);
            spiller.setHarKastet2Seksere(false);

        //Hvis en spiller har over 40 point og slår to ens.
        } else if ((spiller.getPoint() >= 40) && erEns) {

            stopSpil();
            System.out.println(spiller.getNavn() + " har vundet spillet!");

        //Alle andre udfald. Her får spilleren summen af terningerne i point.
        } else {

            int spillersNyePoint = spiller.getPoint() + sum;
            spiller.setPoint(spillersNyePoint);

            System.out.println(spiller.getNavn() + " fik " + sum + " point og har nu i alt " + spiller.getPoint() + " point!");

            //Registrerer om spilleren netop lige har passeret 40 point.
            if (spiller.getPoint() >= 40 && !spiller.isHarKastet2Seksere()) {

                System.out.println(spiller.getNavn() + " har nu 40 eller flere point og kan vinde ved at slå to ens!");
                spiller.setHarPasseret40Point(true);

            }

            //Registrerer om spilleren lige har kastet to 6'ere.
            spiller.setHarKastet2Seksere(sum == 12);

            //Hvis spilleren har slået to ens, får han/hun en ny tur.
            if (erEns) {

                System.out.println(spiller.getNavn() + " slog to ens, og får derfor en ekstra tur.");

                if (!testTilstand) {
                    BrugSpillet.nyTurMenu(spiller, this);
                } else {
                    System.out.println("Spilleren fik her en ekstra tur.");
                    spiller.setHarFaaetEkstraTur(true);
                }
            }
        }
    }

    //Starter et nyt spil

    public void startSpil() {

        spiller1 = new Spiller();
        spiller2 = new Spiller();
        boolean spillerRaekkefoelge;
        spilIgang = true;

        //Her beder vi spillerne indtaste deres navne.

        System.out.print("Indtast navn på spiller 1: ");
        spiller1.setNavn(BrugSpillet.in.next());

        System.out.print("Indtast navn på spiller 2: ");
        spiller2.setNavn(BrugSpillet.in.next());

        //Her slår vi med terningerne og aflæser, om den ene ternings værdi er over 3 (sandsynlighed = 1/2).
        // Dette bruger vi til at bestemme, hvilken spiller der skal starte.

        terninger.slaaTerninger();
        spillerRaekkefoelge = terninger.getT1Vaerdi() > 3;
        String spillerDerStartersNavn = spillerRaekkefoelge ? spiller1.getNavn() : spiller2.getNavn();

        System.out.println("Lad spillet starte!");
        System.out.println(spillerDerStartersNavn + " er valgt tilfældigt til at starte.");

        //Dette styrer spillets gang, som er at de to spiller skiftes til at have en tur.
        //Der tages her højde for, hvilken spiller der skal starte.

        while(spilIgang) {

            if (spillerRaekkefoelge) {

                BrugSpillet.nyTurMenu(spiller1, this);
                if (spilIgang) {
                    BrugSpillet.nyTurMenu(spiller2, this);
                }

            } else {

                BrugSpillet.nyTurMenu(spiller2, this);
                if (spilIgang) {
                    BrugSpillet.nyTurMenu(spiller1, this);
                }
            }
        }
    }

    public void stopSpil() {

        spilIgang = false;

    }

}
