package Food_Delivery.domain;

public class Local {

    private String denumire;
    private String adresa;
    private Meniu meniu;


    public Local(String denumire, String adresa, Meniu meniu) {
        this.denumire = denumire;
        this.adresa = adresa;
        this.meniu = meniu;
    }


    public String getNume() {
        return denumire;
    }

    public void setNume(String nume) {
        this.denumire = nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Meniu getMeniu() {
        return meniu;
    }

    public void setMeniu(Meniu meniu) {
        this.meniu = meniu;
    }


    ///
    ///


    @Override
    public String toString() {
        return "Local{" +
                "nume='" + denumire + '\'' +
                ", adresa='" + adresa + '\'' +
                ", meniu=" + meniu.toString() +
                '}';
    }
}
