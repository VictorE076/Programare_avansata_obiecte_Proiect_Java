!!! PARCURS - Etapa 1

# Programare_avansata_obiecte_Proiect_Java
Platforma Food Delivery ( localuri, comenzi, șoferi, useri )


2 Etape:

Etapa 1:

-> Definire Sistem

-> Implementare


Etapa 2:

-> Baza de date relationala si JDBC

-> Serviciu de audit

1.DEFINIRE SISTEM:

Proiectul este o aplicatie Java de Food Delivery care gestioneaza un sistem de comenzi intre utilizatori si localurile de unde acestia isi pot comanda mancarea.
Utilizatorii pot vizualiza o lista de locaruri disponibile, pot plasa comenzi pentru diferite produse oferite de aceste localuri si pot primi actualizari cu privire la starea comenzilor lor.
Aplicatia foloseste mai multe tipuri de colectii pentru gestionarea datelor (atat in clasele din pachetul "domain", cat si in cele 4 clase "Service", utilizate pentru a expune operatiile aplicatiei).
Clasele principale includ "Local" pentru reprezentarea localurilor si "Comanda" pentru reprezentarea comenzilor, iar metodele de serviciu din clasele "Service" asociate acestor clase ofera functionalitati precum adaugarea si stergerea localurilor, plasarea si actualizarea comenzilor etc.
Desigur, aplicatia va permite informarea si stocarea unor mesaje promotionale unice la anumite produse, intr-o anumita perioada.
De asemenea, aplicatia permite interactiunea simpla si intuitiva intre 2 tipuri de utilizatori (normali sau premium) si localuri, facilitand procesul de comanda si livrare a mancarii intr-un mod organizat si eficient.


Avem 11 tipuri de obiecte (clase definite in domain) + 4 tipuri de obiecte (clase "Service" asociate unor clase din domain, oferind totodata diferite functionalitati aplicatiei):

DOMAIN
-> DatePersonale (atribute: nume, prenume, cnp)
-> Utilizator (atribute: datePersonale, adresa)
-> UtilizatorPremium [extinsa din clasa "Utilizator"] (atribute noi: discount)
-> Sofer (atribute: datePersonale, vehicul)
-> Promotie (atribute: descriere, reducere)
-> Produs (atribute: denumire, pret)
-> Pizza [extinsa din clasa "Produs"] (atribute noi: ingrediente - lista)
-> Burger [extinsa din clasa "Produs"] (atribute noi: tipCarne)
-> Meniu (atribute: produse - lista)
-> Local (atribute: denumire, adresa, meniu)
-> Comanda (atribute: utilizator, local, sofer, stadiuLivrare, suma, metodaPlata)

SERVICES
-> ComandaService (atribute: comenzi - lista finala)
-> LocalService (atribute: localuri - lista finala)
-> PromotieService (atribute: promotii - set final)
-> UtilizatorService (atribute: utilizatori - lista finala)



