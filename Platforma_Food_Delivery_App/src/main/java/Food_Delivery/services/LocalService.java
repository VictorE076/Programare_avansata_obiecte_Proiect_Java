package Food_Delivery.services;

import Food_Delivery.domain.Local;
import Food_Delivery.domain.Meniu;
import Food_Delivery.domain.Produs;

import java.util.*;

public class LocalService { // Singleton

    private static LocalService instanta = null;
    private final List<Local> localuri;


    private LocalService() {
        this.localuri = new ArrayList<>();
    }


    public static LocalService getInstanta() {
        if(instanta == null)
        {
            instanta = new LocalService();
        }
        return instanta;
    }


    ///
    public boolean adaugaLocal(Local local) {
        return this.localuri.add(local);
    }

    public boolean stergeLocal(Local local) {
        return this.localuri.remove(local);
    }

    public List<Local> getLocaluriDisponibile() {
        return this.localuri;
    }

    public int gasesteIndexLocal(Local local) {
        return this.localuri.indexOf(local);
    }

    public void UpdateMeniu_for(Local local, Meniu meniu) {
        int indexOf = gasesteIndexLocal(local);

        if(indexOf != -1)
        {
            this.localuri.set(indexOf, new Local(local.getDenumire(), local.getAdresa(), meniu));
        }
    }

    //// !!! Sort "Produs" by "pret" Desc, then by "denumire" Asc
    public Local SorteazaProduseMeniuLocal_PretDesc_Then_DenumireAsc(Local local) {
        List<Produs> ProdMeniu = local.getMeniu().getProduse();
        Collections.sort(ProdMeniu);
        local.getMeniu().setProduse(ProdMeniu);

        return local;
    }

    //// !!! Sort "Produs" by "pret" Desc, then by "denumire" Asc
    public void ALL_SorteazaProduseMeniuLocaluri_PretDesc_Then_DenumireAsc() {
        ////// !! DE CONTINUAT AICI
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

