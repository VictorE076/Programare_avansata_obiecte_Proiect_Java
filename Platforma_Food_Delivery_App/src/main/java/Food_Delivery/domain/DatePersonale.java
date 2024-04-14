package Food_Delivery.domain;

import java.util.Objects;

public class DatePersonale {

    private String nume;
    private String prenume;
    private String cnp;


    public DatePersonale(String nume, String prenume, String cnp) {
        this.nume = nume;
        this.prenume = prenume;
        this.cnp = cnp;
    }


    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }


    ///

    /*

    (?i)            -> makes it case-insensitive
    (^)             -> asserts that we are at the beginning of the String
    (?=.*[a-z])     -> checks that we have at least 1 letter
    [a-z0-9]{8,20}  -> matches 8 to 20 letters or digits (uppercase letters also accepted)
    $               -> asserts that we've reached the end of the String

    */
    public boolean Valid_CNP() {
        return this.cnp.matches("(?i)^(?=.*[a-z])[a-z0-9]{8,20}$");
    }

    ///


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DatePersonale datePers)) return false;
        return this.nume.compareTo(datePers.nume) == 0 && this.prenume.compareTo(datePers.prenume) == 0 && this.cnp.compareTo(datePers.cnp) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.nume, this.prenume, this.cnp);
    }

    @Override
    public String toString() {
        return "datePersonale{" +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", cnp='" + cnp + '\'' +
                '}';
    }
}

