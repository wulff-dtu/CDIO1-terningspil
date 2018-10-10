package test;

import org.junit.jupiter.api.Test;
import spil.Terninger;

import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.*;

class TerningerTest {

    @Test
    void slaaTerninger() {

        Terninger terninger = new Terninger();

        int antalTestSlag = 1000;
        double tilladtProcentDifference = 0.02;

        int[] forekomstAfSummer = new int[11];
        int forekomstAfEns = 0;
        double forekomstEnsProcent;
        double forekomstEnsProcentDifference;
        double[] forekomsterIprocent = new double[11];
        double[] procentDifference = new double[11];
        boolean[] godkendtDifferenceSummer = new boolean[11];
        boolean godkendtDifferenceEns;
        double[] sandsynlighedForSummer = {1.0/36.0, 2.0/36.0, 3.0/36.0, 4.0/36.0, 5.0/36.0, 6.0/36.0, 5.0/36.0, 4.0/36.0, 3.0/36.0, 2.0/36.0, 1.0/36.0};
        double sandsynlighedForEns = 6.0/36.0;

        for (int i = 0; i < antalTestSlag; i++) {

            terninger.slaaTerninger();
            int sum = terninger.getSum();

            forekomstAfSummer[sum - 2]++;
            if (terninger.erEns()) forekomstAfEns++;

        }

        forekomstEnsProcent = (double) forekomstAfEns / antalTestSlag;
        forekomstEnsProcentDifference = sandsynlighedForEns - forekomstEnsProcent;
        godkendtDifferenceEns = forekomstEnsProcentDifference < tilladtProcentDifference;

        DecimalFormat dc = new DecimalFormat("#.####");

        System.out.println("Udfald af " + antalTestSlag + " testslag:");
        String[] dataTyper = {"Sum/ens:", "Forekomster:", "Procentdel", "Teoretisk:", "Difference:", "Godkendt:"};
        System.out.format("%15s%15s%15s%15s%15s%15s\n", dataTyper);

        for (int i = 0; i < 11; i++) {
            forekomsterIprocent[i] = (double) forekomstAfSummer[i] / antalTestSlag;
            procentDifference[i] = sandsynlighedForSummer[i] - forekomsterIprocent[i];
            godkendtDifferenceSummer[i] = Math.abs(procentDifference[i]) < tilladtProcentDifference;
            String[] tabelLinje = {i + 2 + "", forekomstAfSummer[i] + "", forekomsterIprocent[i] + "%", dc.format(sandsynlighedForSummer[i]) + "%", dc.format(procentDifference[i]) + "%", godkendtDifferenceSummer[i] + ""};
            System.out.format("%15s%15s%15s%15s%15s%15s\n", tabelLinje);
        }



        String[] tabelLinje = {"Ens", forekomstAfEns + "", dc.format(forekomstEnsProcent) + "%", dc.format(sandsynlighedForEns) + "%", dc.format(forekomstEnsProcentDifference) + "%", godkendtDifferenceEns + ""};
        System.out.format("%15s%15s%15s%15s%15s%15s\n", tabelLinje);

        for (boolean godkendelse : godkendtDifferenceSummer) {
            assertTrue(godkendelse);
        }

        assertTrue(godkendtDifferenceEns);

    }
}