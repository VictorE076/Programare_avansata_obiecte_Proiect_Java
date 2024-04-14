package Food_Delivery.domain;

import java.util.Objects;

public abstract class Produs implements Comparable<Produs> {

    protected String denumire;
    protected double pret;


    public Produs(String denumire, double pret) {
        this.denumire = denumire;
        this.pret = pret;
    }


    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }


    ///
    public abstract double calculeazaTaxa();

    public double calculPretTotal(int cantitate) {
        return this.pret * cantitate;
    }
    ///


    @Override
    public int compareTo(Produs prod) { // Sort by "pret" Desc, then by "denumire" Asc
        int pretCompare = Double.compare(prod.pret, this.pret);
        int denumireCompare = this.denumire.compareTo(prod.denumire);

        if(pretCompare == 0) {
            return denumireCompare;
        }

        return pretCompare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produs produs)) return false;
        return Double.compare(this.pret, produs.pret) == 0 && this.denumire.compareTo(produs.denumire) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.denumire, this.pret);
    }

    @Override
    public String toString() {
        return "Produs{" +
                "nume='" + denumire + '\'' +
                ", pret=" + pret +
                '}';
    }
}
