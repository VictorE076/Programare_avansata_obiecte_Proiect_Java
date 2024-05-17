package Food_Delivery.domain;

import java.util.Objects;

public class Burger extends Produs {

    private String tipCarne;


    public Burger(String denumire, double pret, String tipCarne) {
        super(denumire, pret);
        this.tipCarne = tipCarne;
    }


    public String getTipCarne() {
        return tipCarne;
    }

    public void setTipCarne(String tipCarne) {
        this.tipCarne = tipCarne;
    }


    ///
    @Override
    public double calculeazaTaxa() {

        switch (this.tipCarne.toLowerCase())
        {
            case "pui" -> { return 0.01; } // carne pui -> taxa 1%

            case "vita" -> { return 0.02; } // carne vita -> taxa 2%

            case "porc" -> { return 0.03; } // carne porc -> taxa 3%

            default -> { return 0.1; } // orice alt tip de carne -> taxa 10%
        }
    }
    ///


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Burger burger)) return false;
        if (!super.equals(o)) return false;
        return this.tipCarne.compareTo(burger.tipCarne) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.tipCarne);
    }

    @Override
    public String toString() {
        return "Burger{" +
                "tipCarne='" + tipCarne + '\'' +
                ", denumire='" + denumire + '\'' +
                ", pret=" + pret + "lei " +
                '}';
    }
}
