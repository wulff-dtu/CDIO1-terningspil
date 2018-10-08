package spil;

public class Spiller {

    private String navn;
    private int point;
    boolean harPasseret40Point = false;
    boolean harKastet2Seksere = false;

    public Spiller() {
        point = 0;
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
