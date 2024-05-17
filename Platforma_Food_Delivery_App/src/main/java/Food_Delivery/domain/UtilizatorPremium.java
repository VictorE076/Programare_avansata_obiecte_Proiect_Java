package Food_Delivery.domain;

import java.util.Objects;

public class UtilizatorPremium extends Utilizator {

    private static int numarUtilizatoriPremium = 0;

    private double discount; // procent ( intervalul (0, 1) ) -- discount = 0 by default


    public UtilizatorPremium(DatePersonale datePers, String adresa, double discount) {
        super(datePers, adresa);
        this.discount = discount;

        numarUtilizatoriPremium++;
    }

    public UtilizatorPremium(Utilizator utilizator, double discount) {
        super(utilizator.datePers, utilizator.adresa);

        if(utilizator instanceof UtilizatorPremium) // Check if parameter user is "Premium" or not
        {
            // System.out.println("! Utilizator Premium !\n");
            this.discount = Double.max(((UtilizatorPremium) utilizator).discount, discount); // Downcast -- Maximum "discount" value assigned
        }
        else // Simple User
        {
            // System.out.println("! Utilizator Simplu !\n");
            this.discount = discount;
        }

        numarUtilizatoriPremium++;
    }


    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }


    ///
    public static int getNumar_UtilizatoriPremium() {
        return numarUtilizatoriPremium;
    }
    ///


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UtilizatorPremium premiumUser)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(this.discount, premiumUser.discount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.discount);
    }

    @Override
    public String toString() {
        return "UtilizatorPremium{" +
                "discount=" + (int)(100 * discount) + "% " +
                ", datePers=" + datePers.toString() +
                ", adresa='" + adresa + '\'' +
                '}';
    }
}
