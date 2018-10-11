package test;

import org.junit.jupiter.api.Test;
import spil.Spil;
import spil.Spiller;

import static org.junit.jupiter.api.Assertions.*;

class SpilTest {

    //Denne funktion kører et spil i testtilstand med kun én spiller, der får det
    //antal ture, som der er kast angivet i kast[][]. Funktionen returnerer
    //da et spil, hvor vi bagefter kan aflæse, om der er sket det, vi forventer.

    private Spil koerTestSpil(int[][] kast) {

        System.out.println("################### NYT SPIL ####################");

        Spil spil = new Spil();
        Spiller spiller = new Spiller();
        spil.setSpiller1(spiller);
        spil.getSpiller1().setNavn("testSpiller");
        spil.setSpilIgang(true);

        for (int i = 0; i < kast.length; i++) {
            spil.kastTerningerne(spiller, true, kast[i][0], kast[i][1]);
        }

        return spil;
    }

    @Test
    void kastTerningerne() {

        //Her angiver vi vores tests i form af en array med terningekast.

        int[][] test1 = {{5, 3}, {2, 6}, {4, 1}};
        int[][] test2 = {{2, 3}, {5, 4}, {1, 1}};
        int[][] test3 = {{3, 3}};
        int[][] test4 = {{6, 5}, {5, 6}, {6, 5}, {5, 3}, {5, 5}};
        int[][] test5 = {{3, 4}, {6, 6}, {6, 6}};
        //Eventuelt flere tests..

        //Her ser vi, om de ovenstående tests giver det forventede resultat, altså lykkedes eller fejler.

        /* Test 1 */ assertTrue(koerTestSpil(test1).getSpiller1().getPoint() == 21); //Spiller slår tre kast, der bør give 21 i point i alt.
        /* Test 2 */ assertTrue(koerTestSpil(test2).getSpiller1().getPoint() == 0); //Spiller får nogle point, og mister derefter alle sine point.
        /* Test 3 */ assertTrue(koerTestSpil(test3).getSpiller1().harFaatEkstraTur()); //Spiller slår to ens og får en ekstra tur.
        /* Test 4 */ assertTrue(!koerTestSpil(test4).getSpilIgang()); //Spiller vinder ved at få over 40 point og slå to éns.
        /* Test 5 */ assertTrue(!koerTestSpil(test5).getSpilIgang()); //Spiller vinder ved at slå to 6'ere i to ture i træk.
        //Eventuelt flere tests..

    }

}