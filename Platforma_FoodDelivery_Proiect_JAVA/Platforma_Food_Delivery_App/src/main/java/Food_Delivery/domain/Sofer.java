package Food_Delivery.domain;

import java.util.Objects;

public class Sofer {

    private DatePersonale datePers;
    private String vehicul;


    public Sofer(DatePersonale datePers, String vehicul) {
        this.datePers = datePers;
        this.vehicul = vehicul;
    }


    public DatePersonale getDatePers() {
        return datePers;
    }

    public void setDatePers(DatePersonale datePers) {
        this.datePers = datePers;
    }

    public String getVehicul() {
        return vehicul;
    }

    public void setVehicul(String vehicul) {
        this.vehicul = vehicul;
    }


    ///
    ///


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sofer sofer)) return false;
        return this.datePers.equals(sofer.datePers) && this.vehicul.compareTo(sofer.vehicul) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.datePers, this.vehicul);
    }

    @Override
    public String toString() {
        return "Sofer{" +
                "datePersonale=" + datePers.toString() +
                ", vehicul='" + vehicul + '\'' +
                '}';
    }
}
