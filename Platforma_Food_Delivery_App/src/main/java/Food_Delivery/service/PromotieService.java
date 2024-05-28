package Food_Delivery.service;

import Food_Delivery.audit.AuditService;
import Food_Delivery.domain.Promotie;

import java.util.*;

public class PromotieService { // Singleton

    private static PromotieService instanta = null;
    private final Set<Promotie> promotii; // !!! Never Contains Duplicate Elements (tuples -> (descriere, reducere))

    // Audit
    private AuditService auditService;

    private PromotieService() {
        this.promotii = new TreeSet<>((p1, p2) -> {
            int reducereComp = Double.compare(p2.getReducere(), p1.getReducere());
            int descriereComp = p1.getDescriere().compareTo(p2.getDescriere());

            if(reducereComp == 0) {
                return descriereComp;
            }
            return reducereComp;
        }); // !!! Ordered Desc by "Reducere", then Asc by "Descriere"

        // Audit
        auditService = AuditService.getInstance();
    }

    public static PromotieService getInstanta() {
        if(instanta == null)
        {
            instanta = new PromotieService();
        }
        return instanta;
    }


    ///
    public void adaugaPromotie(Promotie promotie, boolean infoAudit) {
        this.promotii.add(promotie);

        if(infoAudit) {
            auditService.logAction("Promotie adaugata");
        }
    }

    public boolean stergePromotie(Promotie promotie, boolean infoAudit) {
        if(infoAudit) {
            auditService.logAction("Promotie stearsa");
        }

        return this.promotii.remove(promotie);
    }

    public Set<Promotie> getPromotiiDisponibile() {
        return this.promotii;
    }

    public boolean gasestePromotia(Promotie promotie, boolean infoAudit) {
        if(infoAudit) {
            auditService.logAction("Gaseste promotia");
        }

        return this.promotii.contains(promotie);
    }
    ///


    public void afiseazaSetPromotiiDisponibile(boolean infoAudit) {
        System.out.println("Set Promotii Disponibile:");
        System.out.println(this.promotii + "\n");

        if(infoAudit) {
            auditService.logAction("Afisarea tuturor promotiilor");
        }
    }
}
