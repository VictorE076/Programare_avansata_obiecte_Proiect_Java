package Food_Delivery.domain;

import java.util.*;

public class Meniu {

    private List<Produs> produse;


    public Meniu(List<Produs> produse) {
        this.produse = produse;
    }


    public List<Produs> getProduse() {
        return produse;
    }

    public void setProduse(List<Produs> produse) {
        this.produse = produse;
    }


    ///
    private List<String> Produs_toString_Helper()
    {
        List<String> produseStr = new ArrayList<>(this.produse.size());
        for(Produs prod : this.produse)
        {
            produseStr.add(prod.toString());
        }

        return produseStr;
    }
    ///


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meniu meniu)) return false;
        return this.produse.equals(meniu.produse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.produse);
    }

    @Override
    public String toString() {
        return "Meniu{" +
                "produse=" + String.join(", ", Produs_toString_Helper()) +
                '}';
    }
}
