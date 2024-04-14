package Food_Delivery.services;

import Food_Delivery.domain.Comanda;
import Food_Delivery.domain.Sofer;

import java.util.*;

public class ComandaService { // Singleton

    private static ComandaService instanta = null;
    private final List<Comanda> comenzi;


    private ComandaService() {
        this.comenzi = new ArrayList<>();
    }

    public static ComandaService getInstanta() {
        if(instanta == null)
        {
            instanta = new ComandaService();
        }
        return instanta;
    }


    ///
    public List<Comanda> getComenziDisponibile() {
        return this.comenzi;
    }

    public void plaseazaComanda(Comanda comanda) {
        this.comenzi.add(comanda);
    }

    public int gasesteIndexComanda(Comanda comanda) {
        return this.comenzi.indexOf(comanda);
    }

    public Comanda atribuieSoferComenzii(Comanda comanda, Sofer sofer) {
        comanda.setSofer(sofer);
        comanda.setStadiuLivrare("În curs de livrare");

        return comanda;
    }

    public Comanda actualizareStadiuComanda(Comanda comanda, String nouStadiuLivrare) {
        comanda.setStadiuLivrare(nouStadiuLivrare);

        return comanda;
    }

    public String getDetaliiPlataComanda(Comanda comanda) {
        String detaliiPlata = null;
        int indexOf = gasesteIndexComanda(comanda);

        if(indexOf != -1)
        {
            return "Comanda " + indexOf + ", avand valoarea: " + comanda.getSuma() + ", Metoda Plata: " + comanda.getMetodaPlata();
        }

        return detaliiPlata;
    }

    private List<String> Comanda_toString_Helper()
    {
        List<String> comenziStr = new ArrayList<>(this.comenzi.size());
        for(Comanda l : this.comenzi)
        {
            comenziStr.add(l.toString());
        }

        return comenziStr;
    }
    ///


    @Override
    public String toString() {
        return "ComandaService{" +
                "comenzi=" + String.join(", ", Comanda_toString_Helper()) +
                '}';
    }
}

