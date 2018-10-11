package test;

import org.junit.jupiter.api.Test;
import spil.Terninger;

import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.*;

class TerningerTest {

    @Test
    void slaaTerninger() {

        Terninger terninger = new Terninger();

        // Indtast her, hvor mange testkast, du vil teste, samt hvor stor
        // en difference fra den teoretiske sandsynlighed, du vil tillade (i procent).
        // Den angivne difference på 2% gør, at testen ofte lykkedes, men nogle gange fejler.

        int antalTestKast = 1000;
        double tilladtProcentDifference = 0.02;

        //med "f" menes "forekomst af". Med fx "fSummer" menes der "forekomster af summer".
        //"procent" angiver, at det er en procentdel af antallet af testkast. Med "diff" er der tale om en difference.
        //med "s" menes der "sandsynlighed for". Med fx "sSummer" menes der "teoretisk sandsynlighed for summer".
        // med "g" menes "godkendt". Fx er "gDiffEns" en boolean der angiver, om differencen er godkendt.

        int[] fSummer = new int[11];
        int fEns = 0;
        double fEnsProcent;
        double fEnsProcentDiff;
        double[] fSummerProcent = new double[11];
        double[] fSummerProcentDiff= new double[11];
        boolean[] gDiffSummer = new boolean[11];
        boolean gDiffEns;
        double[] sSummer = {1.0/36.0, 2.0/36.0, 3.0/36.0, 4.0/36.0, 5.0/36.0, 6.0/36.0, 5.0/36.0, 4.0/36.0, 3.0/36.0, 2.0/36.0, 1.0/36.0};
        double sEns = 6.0/36.0;

        //Vi kaster terningerne og indsamler data

        for (int i = 0; i < antalTestKast; i++) {

            terninger.slaaTerninger();
            int sum = terninger.getSum();

            fSummer[sum - 2]++;
            if (terninger.erEns()) fEns++;

        }

        // vi regner på data

        fEnsProcent = (double) fEns / antalTestKast;
        fEnsProcentDiff = sEns - fEnsProcent;
        gDiffEns = fEnsProcentDiff < tilladtProcentDifference;

        for (int i = 0; i < 11; i++) {
            fSummerProcent[i] = (double) fSummer[i] / antalTestKast;
            fSummerProcentDiff[i] = sSummer[i] - fSummerProcent[i];
            gDiffSummer[i] = Math.abs(fSummerProcentDiff[i]) < tilladtProcentDifference;
        }

        //Vi tester om udfaldet kan godkendes

        for (boolean godkendelse : gDiffSummer) {
            assertTrue(godkendelse);
        }

        assertTrue(gDiffEns);

        //Vi printer udfaldet af testen til konsollen

        DecimalFormat dc = new DecimalFormat("#.####");

        System.out.println("Udfald af " + antalTestKast + " testkast:");
        String[] dataTyper = {"Sum/ens:", "Forekomster:", "Procentdel", "Teoretisk:", "Difference:", "Godkendt:"};
        System.out.format("%15s%15s%15s%15s%15s%15s\n", dataTyper);

        for (int i = 0; i < 11; i++) {
            String[] tabelLinje = {i + 2 + "", fSummer[i] + "", fSummerProcent[i] + "%", dc.format(sSummer[i]) + "%", dc.format(fSummerProcentDiff[i]) + "%", gDiffSummer[i] + ""};
            System.out.format("%15s%15s%15s%15s%15s%15s\n", tabelLinje);
        }

        String[] tabelLinje = {"Ens", fEns + "", dc.format(fEnsProcent) + "%", dc.format(sEns) + "%", dc.format(fEnsProcentDiff) + "%", gDiffEns + ""};
        System.out.format("%15s%15s%15s%15s%15s%15s\n", tabelLinje);

    }
}