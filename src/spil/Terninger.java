package spil;

public class Terninger {

    private int t1Vaerdi;
    private int t2Vaerdi;
    private int sum;

    public Terninger() {};

    public void slaaTerninger() {
        t1Vaerdi = (int) (Math.random() * 6 + 1);
        t2Vaerdi = (int) (Math.random() * 6 + 1);
        sum = t1Vaerdi + t2Vaerdi;
    }

    public int getT1Vaerdi() {
        return t1Vaerdi;
    }

    public int getT2Vaerdi() {
        return t2Vaerdi;
    }

    //Bruges kun til at teste spillet
    public void setT1Vaerdi(int t1Vaerdi) {
        this.t1Vaerdi = t1Vaerdi;
    }

    //Bruges kun til at teste spillet
    public void setT2Vaerdi(int t2Vaerdi) {
        this.t2Vaerdi = t2Vaerdi;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public boolean erEns() {
        return t1Vaerdi == t2Vaerdi;
    }
}
