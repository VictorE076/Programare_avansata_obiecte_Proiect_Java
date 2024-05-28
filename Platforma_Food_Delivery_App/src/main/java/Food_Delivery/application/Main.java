package Food_Delivery.application;

import Food_Delivery.database.service.*;
import Food_Delivery.domain.*;
import Food_Delivery.service.ComandaService;
import Food_Delivery.service.LocalService;
import Food_Delivery.service.PromotieService;
import Food_Delivery.service.UtilizatorService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        ////// PromotieService
        PromotieService promotieService = PromotieService.getInstanta();

        // 1. Adaugarea a 5 promotii disponibile in aplicatia de Food Delivery
        // (Promotiile vor fi sortate descrescator dupa valoarea reducerii, si in caz de egalitate, crescator lexicografic dupa descriere).
        // (Nu vor fi stocate in memorie promotiile egale din punct de vedere al continutului).
        promotieService.adaugaPromotie(new Promotie("Reducere la pizza Margherita!", 0.2));
        promotieService.adaugaPromotie(new Promotie("Reducere la pizza Diavola!", 0.15));
        promotieService.adaugaPromotie(new Promotie("Reducere la pizza Napoletana!", 0.26));
        promotieService.adaugaPromotie(new Promotie("Reducere la burger Black Angus!", 0.15));
        promotieService.adaugaPromotie(new Promotie("Reducere la cheeseburger!", 0.18));

        // 2. Afisarea tuturor promotiilor disponibile.
        promotieService.afiseazaSetPromotiiDisponibile();

        // 3. Gasirea unei promotii adaugate si stergerea acesteia din Service-ul respectiv
        // (Se va afisa un mesaj corespunzator pentru operatiile de gasire si stergere, care au fost efectuate cu succes)/
        Promotie promotie1 = new Promotie("Reducere la pizza Quattro Stagioni", 0.16);
//        Promotie promotie2 = new Promotie("Reducere la pizza Quattro Stagioni", 0.15);
        promotieService.adaugaPromotie(promotie1);
//        promotieService.afiseazaSetPromotiiDisponibile();
//        promotieService.adaugaPromotie(promotie2);
//        promotieService.afiseazaSetPromotiiDisponibile();
        boolean found_promotie1 = promotieService.gasestePromotia(promotie1);

        if(found_promotie1) {
            System.out.println("Am gasit <promotie1>!");

            boolean delete_promotie1 = promotieService.stergePromotie(promotie1);
            if(delete_promotie1) {
                System.out.println("<promotie1> sters cu succes!");
            }
        }
        else {
            System.out.println("N-am gasit <promotie1>!");
        }


//        promotieService.afiseazaSetPromotiiDisponibile();
//        found_promotie1 = promotieService.gasestePromotia(promotie1);
//        System.out.println(found_promotie1);

        ///
        System.out.println("\n//////////////////////////////////////////////////////\n");

        ////// UtilizatorService
        UtilizatorService userService = UtilizatorService.getInstanta();

        // 4. Adaugarea a 2 utilizatori normali si inca un utilizator premium (doar cei care au CNP-ul valid).
        userService.adaugaUtilizator(new Utilizator(new DatePersonale("Pop", "Ion", "6040412019556"), "Strada Livezilor Nr.5, Bucuresti"));
//        System.out.println(userService);

        Utilizator u2 = new UtilizatorPremium(new DatePersonale("Lupsa", "Andrei", "0777134225262543535455343135476464645"), "Strada Lacramioarei Nr.10, Iasi", 0.4);
        userService.adaugaUtilizator(u2); // Invalid CNP -> more than 20 number of digits
//        System.out.println(userService);

        u2.getDatePers().setCnp("077713422526");
        userService.adaugaUtilizator(u2);
//        System.out.println(userService);

        Utilizator u3 = new Utilizator(new DatePersonale("Mincu", "Robert", "a088989862123"), "Strada Sperantei Nr.1, Timisoara");
        userService.adaugaUtilizator(u3); // Invalid CNP -> one letter included
//        System.out.println(userService);

        u3.getDatePers().setCnp("088989862123");
        userService.adaugaUtilizator(u3);
//        System.out.println(userService);

        // 5. Afisarea tuturor utilizatorilor inregistrati pana in acest moment.
        System.out.println(userService);

        // 6. Afisarea numarului de utilizator normali / premium instantiati.

        // Utilizatori normali instantiati
        System.out.println(Utilizator.getNumar_Utilizatori() - UtilizatorPremium.getNumar_UtilizatoriPremium());

        // Utilizatori premium instantiati
        System.out.println(UtilizatorPremium.getNumar_UtilizatoriPremium());

        // 7. Stergerea unui utilizator normal si reafisarea tuturor utilizatorilor inregistrati.

        boolean simpleUserdeleted = userService.stergeUtilizator(u3);

        if(simpleUserdeleted) {
            System.out.println("Utilizatorul a fost sters cu succes!");
        }
        else {
            System.out.println("Utilizatorul nu a putut fi sters din aplicatie!");
        }

        System.out.println(userService);

        ///
        System.out.println("\n//////////////////////////////////////////////////////\n");

        ////// LocalService
        LocalService localService = LocalService.getInstanta();

        //// --> Ingrediente Pizza
        List<String> ingredientePizza1 = new ArrayList<>();
        ingredientePizza1.add("rosii");
        ingredientePizza1.add("ardei");
        ingredientePizza1.add("branza");

        List<String> ingredientePizza2 = new ArrayList<>();
        ingredientePizza2.add("rosii");
        ingredientePizza2.add("salam");

        //// --> Produse
        Produs burger1 = new Burger("Cheeseburger", 31.8, "Pui");
        Produs burger2 = new Burger("Black Angus", 55, "Vita");
        Produs pizza1 = new Pizza("Diavola", 25.1, ingredientePizza1);

        Produs pizza2 = new Pizza("Margherita", 18.5, ingredientePizza2);
        Produs burger3 = new Burger("Black Angus", 45, "Vita");

        Produs burger4 = new Burger("Hamburger", 15.9, "Porc");

        List<Produs> produse1 = new ArrayList<>();
        produse1.add(burger1);
        produse1.add(burger2);
        produse1.add(pizza1);

        List<Produs> produse2 = new ArrayList<>();
        produse2.add(pizza2);
        produse2.add(burger3);

        List<Produs> produse3 = new ArrayList<>();
        produse3.add(burger4);

        // 8. Adaugarea a 2 localuri diferite in aplicatie.

        Local l1 = new Local("PizzaHut", "Strada Foamei Nr.2, Bucuresti", new Meniu(produse1));
        Local l2 = new Local("Trattoria Monza", "Strada Mancarii Nr.7, Brasov", new Meniu(produse2));

        localService.adaugaLocal(l1);
        localService.adaugaLocal(l2);

        // 9. Sortarea tututor produselor din fiecare meniu, descrescator dupa pret si in caz de egalitate, crescator dupa denumirea acestora + afisarea localurilor din aplicatie.

        localService.ALLSorteazaProduseMeniuLocaluri_PretDesc_Then_DenumireAsc();
        System.out.println(localService);

        // 10. Calcularea tuturor taxelor produselor din meniul primului local prenzent in aplicatie.
        System.out.println("Taxele produselor din localul: " + localService.getLocaluriDisponibile().getFirst().getDenumire());

        for(Produs prod : localService.getLocaluriDisponibile().getFirst().getMeniu().getProduse()) {
            System.out.print(prod);
            System.out.println(" --> taxa produs: " + prod.calculeazaTaxa());
        }
        System.out.println("\n");

        // 11. Afisarea tuturor localurilor + vom inlocui lista produselor din meniul celui de-al 2-lea local cu un nou meniu + afisarea tuturor localurilor dupa modificare.
        System.out.println(localService);
        localService.UpdateMeniu_for(localService.getLocaluriDisponibile().get(1), new Meniu(produse3));
        System.out.println(localService);

        ///
        System.out.println("\n//////////////////////////////////////////////////////\n");

        ////// ComandaService
        ComandaService comandaService = ComandaService.getInstanta();

        // 12. Crearea unei singure comenzi (utilizatorul premium adaugat anterior va da o comanda primului local disponibil in baza de date) + adaugarea acesteia in baza de date a aplicatiei + afisarea acelei comenzi plasate.
        Comanda comanda1 = new Comanda(u2, localService.getLocaluriDisponibile().getFirst(), new Sofer(new DatePersonale("Dobrescu", "Radu", "123456789"), "Motocicleta"), "Nelivrat", 120.3, "Card bancar");

        comandaService.plaseazaComanda(comanda1);

        System.out.println(comandaService);
        System.out.println("\n");

        // 13. Atribuirea unui nou sofer comenzii din sistem, comanda avand stadiul de livrare : "In curs de livrare" + afisarea acelei comenzi plasate.
        Comanda comandaAux1 = comandaService.atribuieNouSoferComenzii(comandaService.getComenziDisponibile().getFirst(), new Sofer(new DatePersonale("Damian", "Marius", "975313579"), "Masina"));
        comandaService.getComenziDisponibile().set(0, comandaAux1);

        System.out.println(comandaService);
        System.out.println("\n");

        // 14. Afisarea detaliilor de plata ale comenzii plasate, prezenta in baza de date.
        String infoPlataComanda1 = comandaService.getDetaliiPlataComanda(comandaService.getComenziDisponibile().getFirst());
        System.out.println(infoPlataComanda1);

        ///
        System.out.println("\n//////////////////////////////////////////////////////\n");

        ///
        Sofer sofer1 = new Sofer(new DatePersonale("Nedelcu", "Andrei", "764568026"), "Motocicleta");

        List<Sofer> soferi1 = new ArrayList<>();
        soferi1.add(new Sofer(new DatePersonale("Popa", "Dan", "333245256"), "Masina"));
        soferi1.add(new Sofer(new DatePersonale("Ionescu", "Marcel", "09876543"), "Trotineta electrica"));
        soferi1.add(sofer1);

        ///

        ////// ETAPA 2:

        //// 1. Crearea tabelelor: Sofer, Utilizator, Promotie, Produs


        /// Sofer
        SoferDBService soferDBService = SoferDBService.getInstance();
        soferDBService.createTable();

        // DROP TABLE
        // soferDBService.dropTable();


        /// Utilizator
        UtilizatorDBService utilizatorDBService = UtilizatorDBService.getInstance();
        utilizatorDBService.createTable();

        // DROP TABLE
        // utilizatorDBService.dropTable();


        /// Promotie
        PromotieDBService promotieDBService = PromotieDBService.getInstance();
        promotieDBService.createTable();

        // DROP TABLE
        // promotieDBService.dropTable();


        /// Produs
        ProdusDBService produsDBService = ProdusDBService.getInstance();
        produsDBService.createTable();

        // DROP TABLE
        // produsDBService.dropTable();


        /// 2. Inseram informatii in tabelele create:

        System.out.println("-> CREATE Operation:\n");

        /*
        /// Promotie
        for(Promotie promotie : promotieService.getPromotiiDisponibile()) {
            System.out.println(promotie);
            promotieDBService.addPromotie(promotie);
        }
        System.out.println("\n");


        /// Produs
        for(Produs produs : produse1) {
            System.out.println(produs);
            produsDBService.addProdus(produs);
        }
        System.out.println("\n");


        /// Sofer
        for(Sofer sofer : soferi1) {
            System.out.println(sofer);
            soferDBService.addSofer(sofer);
        }
        System.out.println("\n");


        /// Utilizator
        for(Utilizator user : userService.getUtilizatoriDisponibili()) {
            System.out.println(user);
            utilizatorDBService.addUtilizator(user);
        }
        System.out.println("\n");

        */


        /// 3. Afisam/Preluam informatii din tabele:

        System.out.println("-> READ Operation:\n\n");

        /// Produs: Afisam toate produsele in ordine crescatoare dupa pretul acestora.
        System.out.println("Informatii Produs:");
        List<Produs> produseBD = produsDBService.getAllProdus_LowerPrice();
        for(Produs produs : produseBD) {
            System.out.println(produs);
        }
        System.out.println("\n");


        /// Promotie: Afisam toate promotiile in ordine descrescatoare dupa valoarea discount-ului.
        System.out.println("Informatii Promotie:");
        List<Promotie> promotiiBD = promotieDBService.getAllPromotii_GreaterDiscount();
        for(Promotie promotie : promotiiBD) {
            System.out.println(promotie);
        }
        System.out.println("\n");


        /// Sofer: Afisam toti soferii inregistrati.
        System.out.println("Informatii Soferi:");
        List<Sofer> soferiBD = soferDBService.getAllSoferi();
        for(Sofer sofer : soferiBD) {
            System.out.println(sofer);
        }
        System.out.println("\n");


        /// Utilizator: Afisam utilizatorul (simplu sau premium) cu id = 2;
        System.out.println("Informatii Utilizatori:");
        int searched_byID = 2;
        Utilizator userBD = utilizatorDBService.getUtilizator_byID(searched_byID);
        System.out.println(userBD + "\n");


        /// 4. Modificam linii din tabele:

        System.out.println("-> UPDATE Operation:\n\n");

        /// Produs: Inlocuim produsul, avand id = 1 cu noul produs dat ca parametru.

        System.out.println("Produsul cu id-ul = " + 1 + " inainte de modificare:");
        System.out.println(produsDBService.getProdus_byID(1));

        System.out.println("Produsul cu id-ul = " + 1 + " dupa modificare:");
        produsDBService.updateProdus_byID(produse2.getFirst(), 1);
        System.out.println(produsDBService.getProdus_byID(1) + "\n");


        /// Promotie: Inlocuim promotia, avand id = 3 cu noua promotie.

        System.out.println("Promotia cu id-ul = " + 3 + " inainte de modificare:");
        System.out.println(promotieDBService.getPromotie_byID(3));

        System.out.println("Promotia cu id-ul = " + 3 + " dupa modificare:");
        promotieDBService.updatePromotie_byID(new Promotie("Reducere la Pepperoni Pizza!", 0.12), 3);
        System.out.println(promotieDBService.getPromotie_byID(3) + "\n");


        /// Sofer: Inlocuim soferul, avand id = 2 cu noul sofer.

        System.out.println("Soferul cu id-ul = " + 2 + " inainte de modificare:");
        System.out.println(soferDBService.getSofer_byID(2));

        System.out.println("Soferul cu id-ul = " + 2 + " dupa modificare:");
        soferDBService.updateSofer_byID(new Sofer(new DatePersonale("Mironescu", "Miruna", "000111222333"), "Masina"), 2);
        System.out.println(soferDBService.getSofer_byID(2) + "\n");


        /// Utilizator: Inlocuim utilizatorul (simplu sau premium), avand id = 1 cu noul utilizator (simplu sau premium).

        System.out.println("Utilizatorul cu id-ul = " + 1 + " inainte de modificare:");
        System.out.println(utilizatorDBService.getUtilizator_byID(1));

        System.out.println("Utilizatorul cu id-ul = " + 1 + " dupa modificare:");
        utilizatorDBService.updateUtilizator_byID(new UtilizatorPremium(new DatePersonale("Voicu", "Matei", "426745237"), "Strada Crinilor Nr.1", 0.01), 1);
        System.out.println(utilizatorDBService.getUtilizator_byID(1) + "\n");


        /// 5. Stergem linii din tabele:

        System.out.println("-> DELETE Operation:\n\n");

        /// Produs: Stergem produsul, avand id = 3:

        System.out.println("Produsul cu id-ul = " + 3 + " inainte sa fie sters:");
        System.out.println(produsDBService.getProdus_byID(3));
        produsDBService.deleteProdus_byID(3);


        /// Promotie: Stergem promotia, avand id = 3:

        System.out.println("Promotia cu id-ul = " + 3 + " inainte sa fie stearsa:");
        System.out.println(promotieDBService.getPromotie_byID(3));
        promotieDBService.deletePromotie_byID(3);


        /// Sofer: Stergem soferul, avand id = 1:

        System.out.println("Soferul cu id-ul = " + 1 + " inainte sa fie sters:");
        System.out.println(soferDBService.getSofer_byID(1));
        soferDBService.deleteSofer_byID(1);


        /// Utilizator: Stergem utilizatorul (simplu sau premium), avand id = 2:

        System.out.println("Utilizatorul cu id-ul = " + 2 + " inainte sa fie sters:");
        System.out.println(utilizatorDBService.getUtilizator_byID(2));
        utilizatorDBService.deleteUtilizator_byID(2);

        System.out.println("\n///////////////////////////////////////\n");
    }
}
