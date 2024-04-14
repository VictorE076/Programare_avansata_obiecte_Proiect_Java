package Food_Delivery.domain;

import java.util.Objects;

public class Promotie {

    private String descriere;
    private double reducere; // Procent ( interval (0, 1) )


    public Promotie(String descriere, double reducere) {
        this.descriere = descriere;
        this.reducere = reducere;
    }


    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public double getReducere() {
        return reducere;
    }

    public void setReducere(double reducere) {
        this.reducere = reducere;
    }


    ///
    ///


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Promotie promotie)) return false;
        return Double.compare(this.reducere, promotie.reducere) == 0 && this.descriere.compareTo(promotie.descriere) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.descriere, this.reducere);
    }

    @Override
    public String toString() {
        return "Promotie{" +
                "descriere='" + descriere + '\'' +
                ", reducere=" + reducere +
                '}';
    }
}
