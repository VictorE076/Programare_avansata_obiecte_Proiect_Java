package Food_Delivery.domain;

import java.util.*;

public class Pizza extends Produs {

    private List<String> ingrediente;


    public Pizza(String denumire, double pret, List<String> ingrediente) {
        super(denumire, pret);

        // Compozitie
        this.ingrediente = new ArrayList<>(ingrediente.size());
        Collections.copy(this.ingrediente, ingrediente);
    }


    public List<String> getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(List<String> ingrediente) {
        // Compozitie
        this.ingrediente = new ArrayList<>(ingrediente.size());
        Collections.copy(this.ingrediente, ingrediente);
    }


    ///
    @Override
    public double calculeazaTaxa() {
        int Numar_Ingrediente = this.ingrediente.size();

        return Numar_Ingrediente * 0.01;
    }
    ///


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pizza pizza)) return false;
        if (!super.equals(o)) return false;
        return this.ingrediente.equals(pizza.ingrediente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.ingrediente);
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "ingrediente=" + String.join(", ", this.ingrediente) +
                ", denumire='" + denumire + '\'' +
                ", pret=" + pret +
                '}';
    }
}
