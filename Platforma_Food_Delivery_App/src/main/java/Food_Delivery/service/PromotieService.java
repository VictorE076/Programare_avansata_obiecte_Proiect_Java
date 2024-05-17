package Food_Delivery.service;

import Food_Delivery.domain.Promotie;

import java.util.*;

public class PromotieService { // Singleton

    private static PromotieService instanta = null;
    private final Set<Promotie> promotii; // !!! Never Contains Duplicate Elements (tuples -> (descriere, reducere))


    private PromotieService() {
        this.promotii = new TreeSet<>((p1, p2) -> {
            int reducereComp = Double.compare(p2.getReducere(), p1.getReducere());
            int descriereComp = p1.getDescriere().compareTo(p2.getDescriere());

            if(reducereComp == 0) {
                return descriereComp;
            }
            return reducereComp;
        }); // !!! Ordered Desc by "Reducere", then Asc by "Descriere"
    }

    public static PromotieService getInstanta() {
        if(instanta == null)
        {
            instanta = new PromotieService();
        }
        return instanta;
    }


    ///
    public void adaugaPromotie(Promotie promotie) {
        this.promotii.add(promotie);
    }

    public boolean stergePromotie(Promotie promotie) {
        return this.promotii.remove(promotie);
    }

    public Set<Promotie> getPromotiiDisponibile() {
        return this.promotii;
    }

    public boolean gasestePromotia(Promotie promotie) {
        return this.promotii.contains(promotie);
    }
    ///


    public void afiseazaSetPromotiiDisponibile() {
        System.out.println("Set Promotii Disponibile:");
        System.out.println(this.promotii + "\n");
    }
}
