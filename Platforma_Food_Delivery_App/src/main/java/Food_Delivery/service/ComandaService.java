package Food_Delivery.service;

import Food_Delivery.audit.AuditService;
import Food_Delivery.domain.Comanda;
import Food_Delivery.domain.Sofer;

import java.util.*;

public class ComandaService { // Singleton

    private static ComandaService instanta = null;
    private final List<Comanda> comenzi;

    // Audit
    private AuditService auditService;

    private ComandaService() {
        this.comenzi = new ArrayList<>();

        // Audit
        this.auditService = AuditService.getInstance();
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

    public void plaseazaComanda(Comanda comanda, boolean infoAudit) {
        if(infoAudit) {
            auditService.logAction("Comanda plasata");
        }

        this.comenzi.add(comanda);
    }

    public boolean stergeComanda(Comanda comanda, boolean infoAudit) {
        if(infoAudit) {
            auditService.logAction("Comanda stearsa");
        }

        return this.comenzi.remove(comanda);
    }

    public int gasesteIndexComanda(Comanda comanda) {
        return this.comenzi.indexOf(comanda);
    }

    public Comanda atribuieNouSoferComenzii(Comanda comanda, Sofer sofer) {
        comanda.setSofer(sofer);
        comanda.setStadiuLivrare("In curs de livrare");

        return comanda;
    }

    public Comanda actualizareStadiuComanda(Comanda comanda, String nouStadiuLivrare, boolean infoAudit) {
        comanda.setStadiuLivrare(nouStadiuLivrare);

        if(infoAudit) {
            auditService.logAction("Comanda actualizata");
        }

        return comanda;
    }

    public String getDetaliiPlataComanda(Comanda comanda, boolean infoAudit) {
        String detaliiPlata = null;
        int indexOf = gasesteIndexComanda(comanda);

        if(indexOf != -1)
        {
            if(infoAudit) {
                auditService.logAction("Informatii detalii plata comanda");
            }

            return "Comanda " + (indexOf + 1) + ", avand valoarea: " + comanda.getSuma() + ", Metoda Plata: " + comanda.getMetodaPlata();
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

