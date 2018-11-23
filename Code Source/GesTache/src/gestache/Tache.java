/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestache;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author ESSO Dissirama
 */
public class Tache {

    private static Scanner sc = new Scanner(System.in);
    public static List<Tache> listeTache = new ArrayList<>();
    public static int rep = 0;
    private int id;
    private String libelle;
    private String description;
    private Date datedebut;
    private Date datefin;
    private int etatTache;
    private Membre membre;

    public int getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getDescription() {
        return description;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public int getEtatTache() {
        return etatTache;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public void setEtatTache(int etatTache) {
        this.etatTache = etatTache;
    }

    public void setMembre(Membre pMembre) {
        this.membre = pMembre;
    }

    public Membre getMembre() {
        return membre;
    }

    public Tache() {
    }

    public Tache(int id, String libelle, String description, Date datedebut, Date datefin, int etatTache) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.etatTache = etatTache;
    }

    public Tache(int id, String libelle, String description, Date datedebut, Date datefin, int etatTache, Membre membre) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.etatTache = etatTache;
        this.setMembre(membre);
    }

    private static int genererId() {
        int id = 0;
        if (listeTache.isEmpty()) {
            id = 1;
        } else {
            id = 2;
            for (Tache tache : listeTache) {
                if (id <= tache.getId()) {
                    id = tache.getId() + 1;
                }
            }
        }
        return id;
    }

    private static boolean dateValide(String textDate) {
        if (textDate.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
            return true;
        } else {
            return false;
        }
    }

    private static String etatTache(int etatCode) {
        String etat;
        switch (etatCode) {
            case 0:
                etat = "Initial";
                break;
            case 1:
                etat = "En cours";
                break;
            case 2:
                etat = "Fini";
                break;
            case 3:
                etat = "Abandonné";
                break;
            default:
                etat = "Initial";
        }
        return etat;

    }

    private static Date ConvertirDate(String textDate) {
        Date dateConverti = null;
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dateConverti = df.parse(textDate);

        } catch (Exception e) {
            System.out.println("Erreur lors de l'analyse de la date");
        }

        return dateConverti;

    }

    public static String afficheDate(Date date) {
        String date1;
        if (date == null) {
            date1 = "";
        } else {
            SimpleDateFormat df2 = new SimpleDateFormat("EEEE, d MMM yyyy", Locale.FRENCH);
            date1 = df2.format(date);
        }

        return date1;
    }

    public static void creerTache() {
        System.out.format("---------------------------------------------------------------------------------------------------------------------%n");
        System.out.print("+-------------------------------------+\n");
        System.out.print("|        CREATION D'UNE TACHE         |\n");
        System.out.print("+-------------------------------------+\n");
        Date dateDebut = null;
        Date dateFin = null;
        int id = genererId();
        System.out.print("IDENTIFIANT DE LA TACHE : " + id + " \n");
        System.out.print("_______INFORMATION DE LA TACHE_____ : \n");
        System.out.print("Libelle  : \n");
        String libelle = sc.nextLine();
        while (gestache.Membre.champVide(libelle)) {
            System.out.print("Le Nom est obligatoire!\n NOM    : \n");
            libelle = sc.nextLine();
        }
        System.out.print("DESCRIPTION : \n");
        String description = sc.nextLine();
        while (gestache.Membre.champVide(description)) {
            System.out.print("La description est obligatoire!\n NOM    : \n");
            description = sc.nextLine();
        }
        //verifer si la personne existe deja
        System.out.print("Date du debut: (JJ/MM/AAAA) \n");
        String dateDebutSaisi = sc.nextLine();

        while (!dateValide(dateDebutSaisi)) {
            System.out.print("Le format n'est pas correct!\n Date  debut    : \n");
            dateDebutSaisi = sc.nextLine();
        }

        dateDebut = ConvertirDate(dateDebutSaisi);

        System.out.print("Date de la Fin: (JJ/MM/AAAA) \n");
        String dateFinSaisi = sc.nextLine();
        if (!gestache.Membre.champVide(dateFinSaisi)) {
            boolean formatDate = false;
            boolean positionDate = false;
            while (!formatDate && !positionDate) {
                if (dateValide(dateFinSaisi)) {
                    formatDate = true;
                    dateFin = ConvertirDate(dateFinSaisi);
                    if (!dateDebut.before(dateFin)) {
                        positionDate = false;
                        System.out.print("La date debut doit etre inférieure a la date de la fin!\n Date de la Fin    : \n");
                        dateFinSaisi = sc.nextLine();
                        formatDate = false;
                    } else {
                        positionDate = true;
                    }
                } else {
                    formatDate = false;
                    System.out.print("Le format n'est pas correct!\n Date de la Fin    : \n");
                    dateFinSaisi = sc.nextLine();
                }

            };

        }
        System.out.format("---------------------------------------------------------------------------------------------------------------------%n");
        System.out.print("Voulez-vous enregistrer cette tache?\t1 : OUI\t2 : NON\n");
        rep = gestache.Membre.verifierLaSaisie(1, 2);
        System.out.format("---------------------------------------------------------------------------------------------------------------------%n");
        
        if (rep == 1) {

            Tache nouveauTache = new Tache(id, libelle, description, dateDebut, dateFin, 0);
            listeTache.add(nouveauTache);
            System.out.print(Color.GREEN + " ~~~~L'enregistrement validé!! ~~~~\n");
            System.out.println(Color.RESET);
            System.out.format("-------------------------------------------------------------------------------%n");
            System.out.print("Voulez-vous ajouter un autre membre?\t1 : OUI\t2 : NON\n");
            rep = Membre.verifierLaSaisie(1, 2);
            System.out.format("-------------------------------------------------------------------------------%n");

            if (rep == 1) {
                creerTache();
            } else {
                GesTache.menuGererTache();
            }
        } else {
            GesTache.menuGeneral();
        }

    }

    public static List tacheRechercherByLibelle(String libelle) {
        if (listeTache.isEmpty()) {
            return null;
        } else {
            List<Tache> resultat = new ArrayList<>();
            for (Tache tacheP : listeTache) {
                if (tacheP.getLibelle().toLowerCase().contains(libelle.toLowerCase())) {
                    resultat.add(tacheP);
                }
            }
            if (resultat.isEmpty()) {
                return null;
            } else {
                return resultat;
            }
        }
    }

    public static List tacheNonAssigneRechercherByLibelle(String libelle) {
        if (listeTache.isEmpty()) {
            return null;
        } else {
            List<Tache> resultat = new ArrayList<>();
            for (Tache tacheP : listeTache) {
                if (tacheP.getLibelle().toLowerCase().contains(libelle.toLowerCase()) && tacheP.getEtatTache() == 0) {
                    resultat.add(tacheP);
                }
            }
            if (resultat.isEmpty()) {
                return null;
            } else {
                return resultat;
            }
        }
    }

    public static List tacheAssigneRechercherByLibelle(String libelle) {
        if (listeTache.isEmpty()) {
            return null;
        } else {
            List<Tache> resultat = new ArrayList<>();
            for (Tache tacheP : listeTache) {
                if (tacheP.getLibelle().toLowerCase().contains(libelle.toLowerCase()) && tacheP.getEtatTache() == 1) {
                    resultat.add(tacheP);
                }
            }
            if (resultat.isEmpty()) {
                return null;
            } else {
                return resultat;
            }
        }
    }

    public static List tacheAssigneRechercher() {
        if (listeTache.isEmpty()) {
            return null;
        } else {
            List<Tache> resultat = new ArrayList<>();
            for (Tache tacheP : listeTache) {
                if (tacheP.getEtatTache() != 0) {
                    resultat.add(tacheP);
                }
            }
            if (resultat.isEmpty()) {
                return null;
            } else {
                return resultat;
            }
        }
    }

    public static Tache tacheRechercherById(int identifiant, List<Tache> listeTache) {
        if (listeTache.isEmpty()) {
            return null;
        } else {
            for (Tache pTache : listeTache) {
                if (identifiant == pTache.getId()) {
                    return pTache;
                }
            }
        }
        return null;
    }

    public static void modifierTache() {
        System.out.format("---------------------------------------------------------------------------------------------------------------------%n");
        System.out.print("+-------------------------------------+\n");
        System.out.print("|      MODIFICATION D'UNE TACHE       |\n");
        System.out.print("+-------------------------------------+\n");
        Date newDateDebut = null;
        Date newDateFin = null;
        System.out.print("\n_______TACHE A MODIFIER_____ : \n");
        System.out.print("Libellé:\n");
        String libelle = sc.nextLine();
        while (gestache.Membre.champVide(libelle)) {
            System.out.print("Le libelle est obligatoire!\n Libellé:\n");
            libelle = sc.nextLine();
        }
        List<Tache> tacheTrouve = new ArrayList();
        Tache tacheRecherche = new Tache();
        tacheTrouve = tacheRechercherByLibelle(libelle);
        if (tacheTrouve == null) {
            System.out.print("Désolé. Le libelle qui vous avez donné n'existe pas dans notre repertoire ou aucune tache n'est enregistré. Veuillez réessayer!! \n");
            modifierTache();
        }
        if (tacheTrouve.size() == 1) {
            tacheRecherche = tacheTrouve.get(0);
        } else if (tacheTrouve.size() > 1) {
            System.out.print("~~~Vous devez choisir la tache parmi cette liste!~~~\n");
            String leftAlignFormat = "| %-25s | %-18d |%n";
            System.out.format("+-------------------------------+------------------+%n");
            System.out.format("| Libelle                       | Numero           |%n");
            System.out.format("+-------------------------------+------------------+%n");
            for (Tache tTrouve : tacheTrouve) {
                System.out.format(leftAlignFormat, tTrouve.getLibelle(), tTrouve.getId());
                System.out.format("+-------------------------------+------------------+%n");
            }
            System.out.print("~~~Veuillez choisir le numero de la tache à modifier!~~~\n");
            System.out.print("Numéro:\n");
            sc.nextLine();
            int numero = sc.nextInt();
            while (tacheRechercherById(numero, tacheTrouve) != null) {
                System.out.print("Ce numero n'est pas correct! Ressayez s'il vous plait: \n");
                numero = sc.nextInt();

            }
            tacheRecherche = tacheRechercherById(numero, tacheTrouve);
        }
        System.out.print("~~~Veuillez  modifier la tache!~~~\n");
        System.out.print("NB: FAITES ENTRER  POUR GARDER LES VALEURS ANCIENNES~~~\n");
        System.out.print("_______INFO DU TACHEE_____ : \n");
        System.out.print("ANCIEN LIBELLE:  " + tacheRecherche.getLibelle() + " \n");
        System.out.print("NOUVEAU LIBELLE: \n");
        String newLibelle = sc.nextLine();
        if (gestache.Membre.champVide(newLibelle)) {
            newLibelle = tacheRecherche.getLibelle();
        }
        System.out.print("ANCIENNE DESCRIPTION: " + tacheRecherche.getDescription() + " \n");
        System.out.print("NOUVELLE DESCRIPTION: \n");
        String newDescription = sc.nextLine();
        if (gestache.Membre.champVide(newDescription)) {
            newDescription = tacheRecherche.getDescription();
        }
        System.out.print("ANCIENNE DATE DE DEBUT: " + afficheDate(tacheRecherche.getDatedebut()) + " \n");
        System.out.print("NOUVELLE DATE DE DEBUT: \n");
        String newDateDebutSaisi = sc.nextLine();
        if (gestache.Membre.champVide(newDateDebutSaisi)) {
            newDateDebut = tacheRecherche.getDatedebut();
        } else {
            while (!dateValide(newDateDebutSaisi)) {
                System.out.print("Le format n'est pas correct!\n Date  debut    : \n");
                newDateDebutSaisi = sc.nextLine();
            }

            newDateDebut = ConvertirDate(newDateDebutSaisi);
        }
        System.out.print("ANCIENNE DATE FIN: " + afficheDate(tacheRecherche.getDatefin()) + " \n");
        System.out.print("NOUVELLE DATE FIN: \n");
        String newDateFinSaisi = sc.nextLine();
        if (gestache.Membre.champVide(newDateFinSaisi)) {
            newDateFin = tacheRecherche.getDatefin();
        } else {
            boolean formatDate = false;
            boolean positionDate = false;
            while (!formatDate && !positionDate) {
                if (dateValide(newDateFinSaisi)) {
                    formatDate = true;
                    newDateFin = ConvertirDate(newDateFinSaisi);
                    if (!newDateDebut.before(newDateFin)) {
                        positionDate = false;
                        System.out.print("La date debut doit etre inférieure a la date de la fin!\n Date de la Fin    : \n");
                        newDateFinSaisi = sc.nextLine();
                        formatDate = false;
                    } else {
                        positionDate = true;
                    }
                } else {
                    formatDate = false;
                    System.out.print("Le format n'est pas correct!\n Date de la Fin    : \n");
                    newDateFinSaisi = sc.nextLine();
                }

            };

        }

        System.out.print("Voulez-vous modifier cette tache?\t1 : OUI\t2 : NON\n");
        rep = gestache.Membre.verifierLaSaisie(1, 2);
        if (rep == 1) {
            listeTache.set(listeTache.indexOf(tacheRecherche), new Tache(tacheRecherche.getId(), newLibelle, newDescription, newDateDebut, newDateFin, tacheRecherche.getEtatTache()));
            tacheTrouve.clear();
            System.out.format("---------------------------------------------------------------------------------------------------------------------%n");
            System.out.print(Color.GREEN + "Félicitations!!! La modification est reussie!!\n");
            System.out.print(Color.RESET + "\n");
            System.out.format("---------------------------------------------------------------------------------------------------------------------%n");

        } else {
            GesTache.menuGererTache();
        }

        GesTache.menuGererTache();

    }

    public static void assignerTache() {
        System.out.format("---------------------------------------------------------------------------------------------------------------------%n");
        System.out.print("+-------------------------------------+\n");
        System.out.print("|       ASSIGNATION D'UNE TACHE       |\n");
        System.out.print("+-------------------------------------+\n");
        System.out.print("\n_______TACHE A ASSIGNER_____ : \n");
        System.out.print("libelle: \n");
        String libelle = sc.nextLine();
        while (gestache.Membre.champVide(libelle)) {
            System.out.print("Le libellé est obligatoire!\n Libelle: \n");
            libelle = sc.nextLine();
        }
        List<Tache> tacheTrouve = new ArrayList();
        Tache tacheRecherche = new Tache();

        tacheTrouve = tacheNonAssigneRechercherByLibelle(libelle);
        if (tacheTrouve == null) {
            System.out.print("Désolé. Cette tache n'existe pas dans notre repertoire ou a été déjà assignée. Veuillez réessayer!! \n");
            assignerTache();
        } else {
            if (tacheTrouve.size() == 1) {
                tacheRecherche = tacheTrouve.get(0);
            } else if (tacheTrouve.size() > 1) {
                System.out.print("~~~Vous devez choisir la tache parmi cette liste!~~~\n");
                String leftAlignFormat = "| %-25s | %-18d |%n";
                System.out.format("+-------------------------------+------------------+%n");
                System.out.format("| Libelle                       | Numero           |%n");
                System.out.format("+-------------------------------+------------------+%n");
                for (Tache tTrouve : tacheTrouve) {
                    System.out.format(leftAlignFormat, tTrouve.getLibelle(), tTrouve.getId());
                    System.out.format("+-------------------------------+------------------+%n");
                }
                System.out.print("\n~~~Veuillez choisir le numero de la tache à assigner!~~~\n");
                System.out.print("Numéro:\n");
                // sc.nextLine();
                int numero = sc.nextInt();
                while (tacheRechercherById(numero, tacheTrouve) != null) {
                    System.out.print(Color.RED+"Ce numero n'est pas correct! Ressayez s'il vous plait: \n");
                    System.out.println(Color.RESET);
                    numero = sc.nextInt();
                }
                tacheRecherche = tacheRechercherById(numero, tacheTrouve);
            }
            System.out.print("_______INFO DE LA TACHEE A ASSIGNER_____ : \n");
            System.out.print("LIBELLE:  " + tacheRecherche.getLibelle() + " \n");
            System.out.print("DESCRIPTION: " + tacheRecherche.getDescription() + " \n");
            System.out.print("_______MEMBRE A CHOISIR_____ : \n");
            System.out.print("NOM: \n");
            String nom = sc.nextLine();
            while(Membre.champVide(nom)) {
                System.out.print("Le Nom est obligatoire!\n NOM    : \n");
                nom = sc.nextLine();
            }
            List<Membre> membreTrouve = new ArrayList();
            Membre membreRecherche = new Membre();
            membreTrouve = Membre.membreRechercherByNom(nom);

            if(membreTrouve == null) {
                System.out.print("Désolé. Le nom qui vous avez donné n'existe pas dans notre repertoire. Veuillez réessayer!! \n");
                GesTache.menuGererAssignation();
            } else if(membreTrouve.size() == 1) {
                membreRecherche = membreTrouve.get(0);
            } else if (membreTrouve.size() > 1) {

                System.out.print("~~~Vous devez choisir le membre parmi cette liste!~~~\n");
                String leftAlignFormat = "| %-25s | %-18d |%n";
                System.out.format("+-------------------------------+------------------+%n");
                System.out.format("| Nom et Prenom                 | Numero           |%n");
                System.out.format("+-------------------------------+------------------+%n");
                for (Membre membTrouve : membreTrouve) {

                    System.out.format(leftAlignFormat, membTrouve.getNom() + " " + membTrouve.getPrenom(), membTrouve.getId());
                    System.out.format("+---------------------------+--------------------+%n");

                }
                System.out.print("~~~Veuillez choisir le numero du membre à modifier!~~~\n");
                System.out.print("Numéro:\n");
                sc.nextLine();
                int numero = sc.nextInt();
                while (gestache.Membre.membreRechercherById(numero, membreTrouve) != null) {
                    System.out.print("Ce numero n'est pas correct! Ressayez s'il vous plait: \n");
                    numero = sc.nextInt();

                }
                membreRecherche = gestache.Membre.membreRechercherById(numero, membreTrouve);
            }
            System.out.print("_______INFO DU MEMBRE_____ : \n");
            System.out.print("NOM & PRENOM : " + membreRecherche.getNom() + " " + membreRecherche.getPrenom() + " \n");
            System.out.print("Voulez-vous assigner cette tache?\t1 : OUI\t2 : NON\n");
            rep = gestache.Membre.verifierLaSaisie(1, 2);
            if (rep == 1) {
                tacheRecherche.setMembre(membreRecherche);
                tacheRecherche.setEtatTache(1);
                listeTache.set(listeTache.indexOf(tacheRecherche), tacheRecherche);
                tacheTrouve.clear();
                membreTrouve.clear();
                System.out.format("---------------------------------------------------------------------------------------------------------------------%n");
                System.out.print(Color.GREEN+"Félicitations!!! L'assignation est reussie!!\n");
                System.out.print(Color.RESET+"\n");
                System.out.format("---------------------------------------------------------------------------------------------------------------------%n");
        
            } else {
                GesTache.menuGererAssignation();
            }

            GesTache.menuGererAssignation();
        }

    }

    public static void reassignerTache() {
        System.out.format("---------------------------------------------------------------------------------------------------------------------%n");
        System.out.print("+-------------------------------------+\n");
        System.out.print("|       REASSIGNATION D'UNE TACHE       |\n");
        System.out.print("+-------------------------------------+\n");
        System.out.print("\n_______TACHE A REASSIGNER_____ : \n");
        System.out.print("libelle: \n");
        String libelle = sc.nextLine();
        while (gestache.Membre.champVide(libelle)) {
            System.out.print("Le libelle est obligatoire!\n Libelle: \n");
            libelle = sc.nextLine();
        }
        List<Tache> tacheTrouve = new ArrayList();
        Tache tacheRecherche = new Tache();

        tacheTrouve = tacheAssigneRechercherByLibelle(libelle);
        if (tacheTrouve == null) {
            System.out.print("Désolé. Cette tache n'existe pas dans notre repertoire ou n'a pas été assignée. Veuillez réessayer!! \n");
            assignerTache();
        } else {
            if (tacheTrouve.size() == 1) {
                tacheRecherche = tacheTrouve.get(0);
            } else if (tacheTrouve.size() > 1) {
                System.out.print("~~~Vous devez choisir la tache parmi cette liste!~~~\n");
                String leftAlignFormat = "| %-25s | %-18d |%n";
                System.out.format("+-------------------------------+------------------+%n");
                System.out.format("| Libelle                       | Numero           |%n");
                System.out.format("+-------------------------------+------------------+%n");
                for (Tache tTrouve : tacheTrouve) {
                    System.out.format(leftAlignFormat, tTrouve.getLibelle(), tTrouve.getId());
                    System.out.format("+-------------------------------+------------------+%n");
                }
                System.out.print("~~~Veuillez choisir le numero de la tache à assigner!~~~\n");
                System.out.print("Numéro:\n");
                // sc.nextLine();
                int numero = sc.nextInt();
                while (tacheRechercherById(numero, tacheTrouve) != null) {
                    System.out.print("Ce numero n'est pas correct! Ressayez s'il vous plait: \n");
                    numero = sc.nextInt();
                }
                tacheRecherche = tacheRechercherById(numero, tacheTrouve);
            }
            System.out.print("_______INFO DE LA TACHEE A ASSIGNER_____ : \n");
            System.out.print("LIBELLE:  " + tacheRecherche.getLibelle() + " \n");
            System.out.print("DESCRIPTION: " + tacheRecherche.getDescription() + " \n");
            System.out.print("_______MEMBRE A CHOISIR_____ : \n");
            System.out.print("NOM: \n");
            String nom = sc.nextLine();
            while (gestache.Membre.champVide(nom)) {
                System.out.print("Le Nom est obligatoire!\n NOM    : \n");
                nom = sc.nextLine();
            }
            List<Membre> membreTrouve = new ArrayList();
            Membre membreRecherche = new Membre();
            membreTrouve = gestache.Membre.membreRechercherByNom(nom);

            if (membreTrouve == null) {
                System.out.print("Désolé. Le nom qui vous avez donné n'existe pas dans notre repertoire. Veuillez réessayer!! \n");
                gestache.GesTache.menuGererAssignation();
            } else if (membreTrouve.size() == 1) {
                membreRecherche = membreTrouve.get(0);
                if (tacheRecherche.getMembre().equals(membreRecherche)) {
                    gestache.GesTache.menuGererAssignation();
                }
            } else if (membreTrouve.size() > 1) {
                boolean tableauNonVide = false;
                for (Membre membTrouve : membreTrouve) {
                    if (!tacheRecherche.getMembre().equals(membTrouve)) {
                        tableauNonVide = true;
                    }
                }
                if (tableauNonVide) {
                    System.out.print("Tous les membres de ce nom ont deja cette tache!\n");
                    gestache.GesTache.menuGererAssignation();
                } else {
                    System.out.print("~~~Vous devez choisir le membre parmi cette liste!~~~\n");
                    String leftAlignFormat = "| %-25s | %-18d |%n";
                    System.out.format("+-------------------------------+------------------+%n");
                    System.out.format("| Nom et Prenom                 | Numero           |%n");
                    System.out.format("+-------------------------------+------------------+%n");
                    for (Membre membTrouve : membreTrouve) {
                        if (!tacheRecherche.getMembre().equals(membTrouve)) {
                            System.out.format(leftAlignFormat, membTrouve.getNom() + " " + membTrouve.getPrenom(), membTrouve.getId());
                            System.out.format("+---------------------------+--------------------+%n");
                        }
                    }
                    System.out.print("~~~Veuillez choisir le numero du membre à modifier!~~~\n");
                    System.out.print("Numéro:\n");
                    sc.nextLine();
                    int numero = sc.nextInt();
                    while (gestache.Membre.membreRechercherById(numero, membreTrouve) != null) {
                        System.out.print("Ce numero n'est pas correct! Ressayez s'il vous plait: \n");
                        numero = sc.nextInt();

                    }
                    membreRecherche = gestache.Membre.membreRechercherById(numero, membreTrouve);
                    System.out.print("_______INFO DU MEMBRE_____ : \n");
                    System.out.print("NOM & PRENOM : " + membreRecherche.getNom() + " " + membreRecherche.getPrenom() + " \n");
                    System.out.print("Voulez-vous assigner cette tache?\t1 : OUI\t2 : NON\n");
                    rep = gestache.Membre.verifierLaSaisie(1, 2);
                    if (rep == 1) {
                        tacheRecherche.setMembre(membreRecherche);
                        tacheRecherche.setEtatTache(1);
                        listeTache.set(listeTache.indexOf(tacheRecherche), tacheRecherche);
                        tacheTrouve.clear();
                        membreTrouve.clear();
                        System.out.format("---------------------------------------------------------------------------------------------------------------------%n");
                        System.out.print(Color.GREEN+"Felicitations!!! L'assignation est reussie!!\n");
                        System.out.print(Color.RESET+"\n");
                        System.out.format("---------------------------------------------------------------------------------------------------------------------%n");
        
                    } else {
                        GesTache.menuGererAssignation();
                    }
                }

            }

            GesTache.menuGererAssignation();
        }

    }

    public static void supprimerTache() {
        System.out.format("---------------------------------------------------------------------------------------------------------------------%n");
        System.out.print("+-------------------------------------+\n");
        System.out.print("|       SUPPRESSION D'UNE TACHE       |\n");
        System.out.print("+-------------------------------------+\n");
        System.out.print("\n_______TACHE A SUPPRIMER_____ : \n");
        System.out.print("Libelle: \n");
        String libelle = sc.nextLine();
        while (gestache.Membre.champVide(libelle)) {
            System.out.print("Le libelle est obligatoire!\n Libelle: \n");
            libelle = sc.nextLine();
        }
        List<Tache> tacheTrouve = new ArrayList();
        Tache tacheRecherche = new Tache();
        tacheTrouve = tacheRechercherByLibelle(libelle);
        if (tacheTrouve == null) {
            System.out.print("Désolé. Le libelle qui vous avez donné n'existe pas dans notre repertoire. Veuillez réessayer!! \n");
            supprimerTache();
        } else {
            System.out.print("~~~Vous devez choisir la tache parmi cette liste!~~~\n");
            String leftAlignFormat = "| %-25s | %-18d |%n";
            System.out.format("+-------------------------------+------------------+%n");
            System.out.format("| Libelle                       | Numero           |%n");
            System.out.format("+-------------------------------+------------------+%n");
            for (Tache tTrouve : tacheTrouve) {
                System.out.format(leftAlignFormat, tTrouve.getLibelle(), tTrouve.getId());
                System.out.format("+-------------------------------+------------------+%n");
            }
            System.out.print("~~~Veuillez choisir le numero de la tache à supprimer!~~~\n");
            System.out.print("Numéro:\n");
            int numero = sc.nextInt();
            while (tacheRechercherById(numero, tacheTrouve) == null) {
                System.out.print("Ce numero n'est pas correct! Ressayez s'il vous plait: \n");
                numero = sc.nextInt();
            }
            tacheRecherche = tacheRechercherById(numero, tacheTrouve);
        }
        System.out.format("---------------------------------------------------------------------------------------------------------------------%n");
        System.out.print("Voulez-vous supprimer cette tache?\t1 : OUI\t2 : NON\n");
        rep = gestache.Membre.verifierLaSaisie(1, 2);
        System.out.format("---------------------------------------------------------------------------------------------------------------------%n");
        if (rep == 1) {
            listeTache.remove(listeTache.indexOf(tacheRecherche));
            tacheTrouve.clear();
            System.out.format("---------------------------------------------------------------------------------------------------------------------%n");
            System.out.print(Color.GREEN+"Felicitation! La suppression est reussie!\n");
            System.out.print(Color.RESET+"\n");
            System.out.format("---------------------------------------------------------------------------------------------------------------------%n");
        } else {
            GesTache.menuGererTache();
        }
        GesTache.menuGererTache();

    }
    public static void rechercherTache() {
        System.out.format("---------------------------------------------------------------------------------------------------------------------%n");
        System.out.print("+-------------------------------------+\n");
        System.out.print("|       RESULTAT DE LA RECHERCHE      |\n");
        System.out.print("+-------------------------------------+\n");
        System.out.print("\n_______TACHE A RECHERCHER_____ : \n");
        System.out.print("Libelle: \n");
        String libelle = sc.nextLine();
        while (gestache.Membre.champVide(libelle)) {
            System.out.print("Le libelle est obligatoire!\n Libelle: \n");
            libelle = sc.nextLine();
        }
        List<Tache> tacheTrouve = new ArrayList();
        Tache tacheRecherche = new Tache();
        tacheTrouve = tacheRechercherByLibelle(libelle);
        if (tacheTrouve == null) {
            System.out.print("Désolé. Aucune Tache retrouvée!! Réessayer!! \n");
            rechercherTache();
        } else {
            System.out.print("~~~Vous devez choisir la tache parmi cette liste!~~~\n");
            String leftAlignFormat = "| %-25s | %-18d |%n";
            System.out.format("+-------------------------------+------------------+%n");
            System.out.format("| Libelle                       | Numero           |%n");
            System.out.format("+-------------------------------+------------------+%n");
            for (Tache tTrouve : tacheTrouve) {
                System.out.format(leftAlignFormat, tTrouve.getLibelle(), tTrouve.getId());
                System.out.format("+-------------------------------+------------------+%n");
            }
            System.out.print("~~~Veuillez choisir le numero de la tache à supprimer!~~~\n");
            System.out.print("Numéro:\n");
            sc.nextLine();
            int numero = sc.nextInt();
            while (tacheRechercherById(numero, tacheTrouve) != null) {
                System.out.print("Ce numero n'est pas correct! Ressayez s'il vous plait: \n");
                numero = sc.nextInt();

            }
            tacheRecherche = tacheRechercherById(numero, tacheTrouve);
        }
        System.out.format("---------------------------------------------------------------------------------------------------------------------%n");
        System.out.print("Voulez-vous supprimer cette tache?\t1 : OUI\t2 : NON\n");
        rep = gestache.Membre.verifierLaSaisie(1, 2);
        System.out.format("---------------------------------------------------------------------------------------------------------------------%n");
        
        if(rep == 1) {
            listeTache.remove(listeTache.indexOf(tacheRecherche));
            tacheTrouve.clear();
            System.out.print(Color.GREEN+"Felicitation! La suppression est reussie?\n");
            System.out.print(Color.RESET+"\n");
            System.out.format("---------------------------------------------------------------------------------------------------------------------%n");
        
        } else {
            GesTache.menuGererTache();
        }
        GesTache.menuGererTache();

    }

    public static void cloturerTache() {
        System.out.format("---------------------------------------------------------------------------------------------------------------------%n");
        System.out.print("+-------------------------------------+\n");
        System.out.print("|       SUPPRESSION D'UNE TACHE       |\n");
        System.out.print("+-------------------------------------+\n");
        Date newDateDebut = null;
        Date newDateFin = null;
        System.out.print("\n_______TACHE A SUPPRIMER_____ : \n");
        System.out.print("Libelle: \n");
        String libelle = sc.nextLine();
        while (gestache.Membre.champVide(libelle)) {
            System.out.print("Le libelle est obligatoire!\n Libelle: \n");
            libelle = sc.nextLine();
        }
        List<Tache> tacheTrouve = new ArrayList();
        Tache tacheRecherche = new Tache();
        tacheTrouve = tacheRechercherByLibelle(libelle);
        if (tacheTrouve == null) {
            System.out.print("Désolé. Le libelle qui vous avez donné n'existe pas dans notre repertoire. Veuillez réessayer!! \n");
            supprimerTache();
        } else {
            System.out.print("~~~Vous devez choisir la tache parmi cette liste!~~~\n");
            String leftAlignFormat = "| %-25s | %-18d |%n";
            System.out.format("+-------------------------------+------------------+%n");
            System.out.format("| Libelle                       | Numero           |%n");
            System.out.format("+-------------------------------+------------------+%n");
            for (Tache tTrouve : tacheTrouve) {
                System.out.format(leftAlignFormat, tTrouve.getLibelle(), tTrouve.getId());
                System.out.format("+-------------------------------+------------------+%n");
            }
            System.out.print("~~~Veuillez choisir le numero de la tache à supprimer!~~~\n");
            System.out.print("Numéro:\n");
            sc.nextLine();
            int numero = sc.nextInt();
            while (tacheRechercherById(numero, tacheTrouve) != null) {
                System.out.print("Ce numero n'est pas correct! Ressayez s'il vous plait: \n");
                numero = sc.nextInt();

            }
            tacheRecherche = tacheRechercherById(numero, tacheTrouve);
        }
        System.out.format("---------------------------------------------------------------------------------------------------------------------%n");
        System.out.print("Voulez-vous supprimer cette tache?\t1 : OUI\t2 : NON\n");
        rep = gestache.Membre.verifierLaSaisie(1, 2);
        System.out.format("---------------------------------------------------------------------------------------------------------------------%n");
        
        if (rep == 1) {
            listeTache.remove(listeTache.indexOf(tacheRecherche));
            tacheTrouve.clear();
            System.out.print(Color.GREEN+"Felicitation! La suppression est reussie?\n");
            System.out.print(Color.RESET+"\n");
            System.out.format("---------------------------------------------------------------------------------------------------------------------%n");
        
        } else {
            GesTache.menuGererTache();
        }
        GesTache.menuGererTache();

    }

    public static void listeTache() {
        System.out.format("---------------------------------------------------------------------------------------------------------------------%n");
        System.out.print("+-------------------------------------+\n");
        System.out.print("|           LISTE DES TACHES          |\n");
        System.out.print("+-------------------------------------+\n");
        if (listeTache.isEmpty()) {
            System.out.print("~~~Aucune tache enregistrée~~~\n");

        } else {
            String leftAlignFormat = "| %-29s | %-29s | %-26s | %-26s | %-12s |%n";
            System.out.format("+-------------------------------+-------------------------------+----------------------------+----------------------------+--------------------+%n");
            System.out.format("| Libellé                       | Description                   | Date debut                 | Date fin                   | Etat               |%n");
            System.out.format("+-------------------------------+-------------------------------+----------------------------+----------------------------+--------------------+%n");
            for (Tache tache : listeTache) {
                System.out.format(leftAlignFormat, tache.getLibelle(), tache.getDescription(), afficheDate(tache.getDatedebut()), afficheDate(tache.getDatefin()), etatTache(tache.getEtatTache()));
                System.out.format("+-------------------------------+-------------------------------+----------------------------+----------------------------+--------------------+%n");
            }

        }
        GesTache.menuGererTache();
    }

    public static void listeTacheAssigne() {
        System.out.format("---------------------------------------------------------------------------------------------------------------------%n");
        System.out.print("+----------------------------------------------+\n");
        System.out.print("|           LISTE DES TACHES ASSIGNEES         |\n");
        System.out.print("+----------------------------------------------+\n");
        List<Tache> tacheTrouve = new ArrayList();
        tacheTrouve = tacheAssigneRechercher();
        if (tacheTrouve.isEmpty()) {
            System.out.print("~~~Aucune tache assignée~~~\n");

        } else {
            String leftAlignFormat = "| %-29s | %-29s | %-26s | %-26s | %-12s |%n";
            System.out.format("+-------------------------------+-------------------------------+----------------------------+----------------------------+--------------------+%n");
            System.out.format("| Libellé                       | Description                   | Date debut                 | Date fin                   | Etat               |%n");
            System.out.format("+-------------------------------+-------------------------------+----------------------------+----------------------------+--------------------+%n");
            for (Tache tache : listeTache) {
                System.out.format(leftAlignFormat, tache.getLibelle(), tache.getDescription(), afficheDate(tache.getDatedebut()), afficheDate(tache.getDatefin()), etatTache(tache.getEtatTache()));
                System.out.format("+-------------------------------+-------------------------------+--------------------+--------------------+--------------------+%n");
            }

        }
        GesTache.menuGererTache();
    }
}
