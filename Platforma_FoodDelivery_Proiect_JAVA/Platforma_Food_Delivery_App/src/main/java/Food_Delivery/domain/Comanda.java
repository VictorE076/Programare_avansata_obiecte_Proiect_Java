package Food_Delivery.domain;

import java.util.Objects;

public class Comanda {

    private Utilizator utilizator;
    private Local local;
    private Sofer sofer;
    private String stadiuLivrare;
    private double suma;
    private String metodaPlata;


    public Comanda(Utilizator utilizator, Local local, Sofer sofer, String stadiuLivrare, double suma, String metodaPlata) {
        this.utilizator = utilizator;
        this.local = local;
        this.sofer = sofer;
        this.stadiuLivrare = stadiuLivrare;
        this.suma = suma;
        this.metodaPlata = metodaPlata;
    }


    public Utilizator getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public Sofer getSofer() {
        return sofer;
    }

    public void setSofer(Sofer sofer) {
        this.sofer = sofer;
    }

    public String getStadiuLivrare() {
        return stadiuLivrare;
    }

    public void setStadiuLivrare(String stadiuLivrare) {
        this.stadiuLivrare = stadiuLivrare;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    public String getMetodaPlata() {
        return metodaPlata;
    }

    public void setMetodaPlata(String metodaPlata) {
        this.metodaPlata = metodaPlata;
    }


    ///

    ///


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comanda comanda)) return false;
        return Double.compare(this.suma, comanda.suma) == 0 && this.utilizator.equals(comanda.utilizator) && this.local.equals(comanda.local) && this.sofer.equals(comanda.sofer) && this.stadiuLivrare.compareTo(comanda.stadiuLivrare) == 0 && this.metodaPlata.compareTo(comanda.metodaPlata) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.utilizator, this.local, this.sofer, this.stadiuLivrare, this.suma, this.metodaPlata);
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "utilizator=" + utilizator.toString() +
                ", local=" + local.toString() +
                ", sofer=" + sofer.toString() +
                ", stadiuLivrare='" + stadiuLivrare + '\'' +
                ", suma=" + suma +
                ", metodaPlata='" + metodaPlata + '\'' +
                '}';
    }
}
