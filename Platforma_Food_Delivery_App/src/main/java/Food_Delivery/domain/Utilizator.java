package Food_Delivery.domain;

public class Utilizator {

    protected static int Numar_Utilizatori = 0; // Total number of users (Simple or Premium)

    protected DatePersonale datePers;
    protected String adresa;


    public Utilizator(DatePersonale datePers, String adresa) {
        this.datePers = datePers;
        this.adresa = adresa;

        Numar_Utilizatori++;
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
        return Numar_Utilizatori;
    }
    ///


    @Override
    public String toString() {
        return "Utilizator{" +
                "datePersonale=" + datePers.toString() +
                ", adresa='" + adresa + '\'' +
                '}';
    }
}
