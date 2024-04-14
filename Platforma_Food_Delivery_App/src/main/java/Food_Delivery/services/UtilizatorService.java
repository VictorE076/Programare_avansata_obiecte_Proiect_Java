package Food_Delivery.services;

import Food_Delivery.domain.Comanda;
import Food_Delivery.domain.Utilizator;

import java.util.*;

public class UtilizatorService { // Singleton

    private static UtilizatorService instanta = null;
    private final List<Utilizator> utilizatori;

    private UtilizatorService() {
        this.utilizatori = new ArrayList<>();
    }

    public static UtilizatorService getInstanta() {
        if(instanta == null)
        {
            instanta = new UtilizatorService();
        }
        return instanta;
    }


    ///
    public List<Utilizator> getUtilizatoriDisponibili() {
        return this.utilizatori;
    }

    public void adaugaUtilizator(Utilizator utilizator) {
        // Regex
        boolean CNP_ok = utilizator.getDatePers().Valid_CNP();

        if(CNP_ok) {
            utilizatori.add(utilizator);
            System.out.println("\n!!! Mesaj: User adaugat cu succes!\n");
        }
        else
        {
            System.out.println("\n!!! Mesaj: Invalid CNP - User-ul nu are permisiunea de a fi adaugat!\n");
        }
    }

    public void stergeUtilizator(Utilizator utilizator) {
        utilizatori.remove(utilizator);
    }

    private List<String> Utilizator_toString_Helper()
    {
        List<String> useriStr = new ArrayList<>(this.utilizatori.size());
        for(Utilizator u : this.utilizatori)
        {
           useriStr.add(u.toString());
        }

        return useriStr;
    }
    ///


    @Override
    public String toString() {
        return "UtilizatorService{" +
                "utilizatori=" + String.join(", ", Utilizator_toString_Helper()) +
                '}';
    }
}

