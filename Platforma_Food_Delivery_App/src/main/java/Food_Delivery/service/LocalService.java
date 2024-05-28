package Food_Delivery.service;

import Food_Delivery.audit.AuditService;
import Food_Delivery.domain.Local;
import Food_Delivery.domain.Meniu;
import Food_Delivery.domain.Produs;

import java.util.*;

public class LocalService { // Singleton

    private static LocalService instanta = null;
    private final List<Local> localuri;

    // Audit
    private AuditService auditService;

    private LocalService() {
        this.localuri = new ArrayList<>();

        // Audit
        auditService = AuditService.getInstance();
    }


    public static LocalService getInstanta() {
        if(instanta == null)
        {
            instanta = new LocalService();
        }
        return instanta;
    }


    ///
    public void adaugaLocal(Local local, boolean infoAudit) {
        this.localuri.add(local);

        if(infoAudit) {
            auditService.logAction("Local adaugat");
        }
    }

    public boolean stergeLocal(Local local, boolean infoAudit) {
        if(infoAudit) {
            auditService.logAction("Local sters");
        }

        return this.localuri.remove(local);
    }

    public List<Local> getLocaluriDisponibile() {
        return this.localuri;
    }

    public int gasesteIndexLocal(Local local) {
        return this.localuri.indexOf(local);
    }

    public void UpdateMeniu_for(Local local, Meniu meniu, boolean infoAudit) {
        int indexOf = gasesteIndexLocal(local);

        if(indexOf != -1)
        {
            if(infoAudit) {
                auditService.logAction("Meniu local actualizat");
            }

            this.localuri.set(indexOf, new Local(local.getDenumire(), local.getAdresa(), meniu));
        }
    }

    //// !!! Sort "Produs" by "pret" Desc, then by "denumire" Asc
    public Local SorteazaProduseMeniuLocal_PretDesc_Then_DenumireAsc(Local local) throws ClassCastException, UnsupportedOperationException, IllegalArgumentException {
        List<Produs> ProdMeniu = local.getMeniu().getProduse();
        Collections.sort(ProdMeniu);
        local.getMeniu().setProduse(ProdMeniu);

        return local;
    }

    //// !!! Sort ALL "Produs" by "pret" Desc, then by "denumire" Asc
    public void ALLSorteazaProduseMeniuLocaluri_PretDesc_Then_DenumireAsc(boolean infoAudit) throws NullPointerException, UnsupportedOperationException {
        this.localuri.replaceAll(this::SorteazaProduseMeniuLocal_PretDesc_Then_DenumireAsc);

        if(infoAudit) {
            auditService.logAction("Produsele din meniul fiecarui local au fost sortate");
        }
    }

    private List<String> Local_toString_Helper()
    {
        List<String> localuriStr = new ArrayList<>(this.localuri.size());
        for(Local l : this.localuri)
        {
            localuriStr.add(l.toString());
        }

        return localuriStr;
    }

    ///


    @Override
    public String toString() {
        return "LocalService{" +
                "localuri=" + String.join(", ", Local_toString_Helper()) +
                '}';
    }

}

