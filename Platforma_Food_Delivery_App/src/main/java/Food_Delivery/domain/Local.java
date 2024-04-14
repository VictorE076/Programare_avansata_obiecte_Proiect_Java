package Food_Delivery.domain;

import java.util.Objects;

public class Local {

    private String denumire;
    private String adresa;
    private Meniu meniu;


    public Local(String denumire, String adresa, Meniu meniu) {
        this.denumire = denumire;
        this.adresa = adresa;
        this.meniu = meniu;
    }


    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Local local)) return false;
        return this.denumire.compareTo(local.denumire) == 0 && this.adresa.compareTo(local.adresa) == 0 && this.meniu.equals(local.meniu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.denumire, this.adresa, this.meniu);
    }

    @Override
    public String toString() {
        return "Local{" +
                "nume='" + denumire + '\'' +
                ", adresa='" + adresa + '\'' +
                ", meniu=" + meniu.toString() +
                '}';
    }
}
