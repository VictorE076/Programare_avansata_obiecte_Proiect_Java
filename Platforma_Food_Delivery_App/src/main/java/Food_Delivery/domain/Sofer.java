package Food_Delivery.domain;

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
    public String toString() {
        return "Sofer{" +
                "datePersonale=" + datePers.toString() +
                ", vehicul='" + vehicul + '\'' +
                '}';
    }
}
