package Food_Delivery.application;

import Food_Delivery.domain.*;
import Food_Delivery.services.ComandaService;
import Food_Delivery.services.LocalService;
import Food_Delivery.services.PromotieService;
import Food_Delivery.services.UtilizatorService;

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
    }
}
