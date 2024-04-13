package Food_Delivery.domain;

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
    public String toString() {
        return "Promotie{" +
                "descriere='" + descriere + '\'' +
                ", reducere=" + reducere +
                '}';
    }
}
