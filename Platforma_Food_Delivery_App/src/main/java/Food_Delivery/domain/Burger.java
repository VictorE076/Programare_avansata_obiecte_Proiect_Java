package Food_Delivery.domain;

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
        double taxa;

        switch (this.tipCarne.toLowerCase())
        {
            case "pui":
                taxa = 0.01; // carne pui -> taxa 1%
                break;
            case "vita":
                taxa = 0.02; // carne vita -> taxa 2%
                break;
            case "porc":
                taxa = 0.03; // carne porc -> taxa 3%
                break;
            default:
                taxa = 0.1; // orice alt tip de carne -> taxa 10%
        }

        return taxa;
    }
    ///


    @Override
    public String toString() {
        return "Burger{" +
                "tipCarne='" + tipCarne + '\'' +
                ", denumire='" + denumire + '\'' +
                ", pret=" + pret +
                '}';
    }
}
