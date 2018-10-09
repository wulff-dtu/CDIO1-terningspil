package spil;

public class Terninger {

    private int t1Vaerdi;
    private int t2Vaerdi;
    private int sum;
    private boolean ens;

    public Terninger() {};

    public void slaaTerninger() {
        t1Vaerdi = (int) (Math.random() * 6 + 1);
        t2Vaerdi = (int) (Math.random() * 6 + 1);
        sum = t1Vaerdi + t2Vaerdi;
        ens = (t1Vaerdi == t2Vaerdi);
    }

    public int getT1Vaerdi() {
        return t1Vaerdi;
    }

    public int getT2Vaerdi() {
        return t2Vaerdi;
    }

    public int getSum() {
        return sum;
    }

    public boolean erEns() {
        return ens;
    }
}
