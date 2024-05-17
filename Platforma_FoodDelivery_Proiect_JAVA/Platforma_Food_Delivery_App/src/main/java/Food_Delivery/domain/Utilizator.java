package Food_Delivery.domain;

import java.util.Objects;

public class Utilizator {

    protected static int numarUtilizatori = 0; // Total number of users (Simple or Premium)

    protected DatePersonale datePers;
    protected String adresa;


    public Utilizator(DatePersonale datePers, String adresa) {
        this.datePers = datePers;
        this.adresa = adresa;

        numarUtilizatori++;
    }


    public DatePersonale getDatePers() {
        return datePers;
    }

    public void setDatePers(DatePersonale datePers) {
        this.datePers = datePers;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }


    ///
    public static int getNumar_Utilizatori() {
        return numarUtilizatori;
    }
    ///


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utilizator user)) return false;
        return this.datePers.equals(user.datePers) && this.adresa.compareTo(user.adresa) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.datePers, this.adresa);
    }

    @Override
    public String toString() {
        return "Utilizator{" +
                "datePersonale=" + datePers.toString() +
                ", adresa='" + adresa + '\'' +
                '}';
    }
}
