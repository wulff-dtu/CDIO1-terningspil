package spil;

public class Spiller {

    private String navn;
    private int point;
    boolean harPasseret40Point;
    boolean harKastet2Seksere;

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

}
