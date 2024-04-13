package Food_Delivery.domain;

public class UtilizatorPremium extends Utilizator {

    private static int Numar_UtilizatoriPremium = 0;

    private double discount; // procent ( intervalul (0, 1) ) -- discount = 0 by default


    public UtilizatorPremium(DatePersonale datePers, String adresa, double discount) {
        super(datePers, adresa);
        this.discount = discount;

        Numar_UtilizatoriPremium++;
    }

    public UtilizatorPremium(Utilizator utilizator, double discount) {
        super(utilizator.datePers, utilizator.adresa);

        if(utilizator instanceof UtilizatorPremium) // Check if parameter user is "Premium" or not
        {
            this.discount = Double.max(((UtilizatorPremium) utilizator).discount, discount); // Downcast -- Maximum "discount" value assigned
        }
        else // Simple User
        {
            this.discount = discount;
        }

        Numar_UtilizatoriPremium++;
    }


    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }


    ///
    public static int getNumar_UtilizatoriPremium() {
        return Numar_UtilizatoriPremium;
    }
    ///


    @Override
    public String toString() {
        return "UtilizatorPremium{" +
                "discount=" + discount +
                ", datePers=" + datePers.toString() +
                ", adresa='" + adresa + '\'' +
                '}';
    }
}