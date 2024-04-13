package Food_Delivery.domain;

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
