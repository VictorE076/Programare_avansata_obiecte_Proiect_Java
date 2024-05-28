package Food_Delivery.service;

import Food_Delivery.audit.AuditService;
import Food_Delivery.domain.Utilizator;

import java.util.*;

public class UtilizatorService { // Singleton

    private static UtilizatorService instanta = null;
    private final List<Utilizator> utilizatori;

    // Audit
    private AuditService auditService;

    private UtilizatorService() {
        this.utilizatori = new ArrayList<>();

        // Audit
        auditService = AuditService.getInstance();
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

    //
//    public void setUtilizatoriDisponibili(List<Utilizator> useri) {
//        this.utilizatori = useri;
//    }
    //

    public void adaugaUtilizator(Utilizator utilizator, boolean infoAudit) {
        // Regex
        boolean CNP_ok = utilizator.getDatePers().Valid_CNP();

        if(!CNP_ok)
        {
            System.out.println("\n!!! Mesaj: Invalid CNP - User-ul nu are permisiunea de a fi adaugat!\n");
        }
        else
        {
            this.utilizatori.add(utilizator);
            System.out.println("\n!!! Mesaj: User adaugat cu succes!\n");

            if(infoAudit) {
                auditService.logAction("Utilizator adaugat");
            }
        }
    }

    public boolean stergeUtilizator(Utilizator utilizator, boolean infoAudit) {
        if(infoAudit) {
            auditService.logAction("Utilizator sters");
        }

        return this.utilizatori.remove(utilizator);
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

