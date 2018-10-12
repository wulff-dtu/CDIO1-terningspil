package spil;

public class Spiller {

    private String navn;
    private int point;
    private boolean harPasseret40Point;
    private boolean harKastet2Seksere;
    private boolean harFaaetEkstraTur; //Bruges kun til at teste spillet med

    public Spiller() {
        point = 0;
        harPasseret40Point = false;
        harKastet2Seksere = false;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getNavn() {
        return navn;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    public boolean harFaaetEkstraTur() {
        return harFaaetEkstraTur;
    }

    public void setHarFaaetEkstraTur(boolean harFaaetEkstraTur) {
        this.harFaaetEkstraTur = harFaaetEkstraTur;
    }

    public void setHarKastet2Seksere(boolean harKastet2Seksere) {
        this.harKastet2Seksere = harKastet2Seksere;
    }

    public boolean isHarKastet2Seksere() {
        return harKastet2Seksere;
    }

    public boolean isHarPasseret40Point() {
        return harPasseret40Point;
    }

    public void setHarPasseret40Point(boolean harPasseret40Point) {
        this.harPasseret40Point = harPasseret40Point;
    }
}
