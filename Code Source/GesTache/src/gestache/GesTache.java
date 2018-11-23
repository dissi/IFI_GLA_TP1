/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestache;

import static gestache.Membre.rep;
import java.util.Scanner;

/**
 *
 * @author ESSO Dissirama
 */
public class GesTache {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        entete();
        choixMenuPrincipal();
    }

    private static void entete() {
        System.out.format(Color.BLUE_BOLD + "##############################################################%n");
        System.out.format(Color.BLUE_BOLD + "#                                                            #%n");
        System.out.format(Color.BLUE_BOLD + "#  MODULE : GENIE LOGICIEL AVANCE                            #%n");
        System.out.format(Color.BLUE_BOLD + "#  TP1 : GESTION DES TACHES                                  #%n");
        System.out.format(Color.BLUE_BOLD + "#  Réalisé par ESSO DISSIRAMA && YEKPLE DJILAN KOFFI AGBAKA  #%n");
        System.out.format(Color.BLUE_BOLD + "#                                                            #%n");
        System.out.format(Color.BLUE_BOLD + "##############################################################%n");
        System.out.println(Color.RESET);
    }

    public static void menuGeneral() {
        String leftAlignFormat = "| %-17s | %-5d |%n";
        System.out.print(Color.RED_BOLD + "MENU PRINCIPAL DE GESTION DES TACHES\n");
        System.out.println(Color.RESET);

        System.out.format("+-------------------+-------+%n");
        System.out.format("| RUBRIQUES         | CHOIX |%n");
        System.out.format("+-------------------+-------+%n");

        System.out.format(leftAlignFormat, "Gerer les membres", 1);
        System.out.format(leftAlignFormat, "Gerer les taches", 2);
        System.out.format(leftAlignFormat, "Assignation", 3);
        System.out.format(leftAlignFormat, "FIN", 4);

        System.out.format("+-------------------+-------+%n");
        System.out.print("\n Faite votre Choix : ");
    }

    public static void menuGererMembre() {
        System.out.format(Color.BLUE + "*****************************************%n");
        System.out.print(Color.BLUE + "**     SOUS MENU GERER LES MEMBRES     **\n");
        System.out.format(Color.BLUE + "*****************************************%n");

        System.out.format(Color.MAGENTA_BOLD +"********************************************************************************%n");
        System.out.print(Color.MAGENTA_BOLD +"SOUS MENU GERER LES MEMBRES\n");
        System.out.format(Color.MAGENTA_BOLD +"********************************************************************************%n");
        System.out.print(Color.RESET);
        System.out.format("+---------+------------+-------------+--------------+-----------------+-----------------------+%n");
        System.out.format("| CREER: 1| MODIFIER: 2| SUPPRIMER: 3| RECHERCHER: 4| LISTES TOTALE: 5| SORTIR DE SOUS MENU: 6|%n");
        System.out.format("+---------+------------+-------------+--------------+-----------------+-----------------------+%n");
        
        System.out.format("-------------------------------------------------------------------------------%n");
        System.out.print("Faite votre Choix : ");

        rep = Membre.verifierLaSaisie(1, 6);
        switch (rep) {
            case 1:
                rep = 1;
                Membre.creerMembre();
                break;
            case 2:
                rep = 2;
                Membre.modifierMembre();
                break;
            case 3:
                rep = 3;
                Membre.supprimerMembre();
                break;
            case 4:
                rep = 4;
                Membre.rechercherMembre();
                break;
            case 5:
                rep = 5;
                Membre.listeMembre();
                break;
            case 6:
                rep = 6;
                choixMenuPrincipal();
                break;
            
        }

    }

    public static void menuGererTache() {
        System.out.format(Color.BLUE + "*****************************************%n");
        System.out.format(Color.BLUE + "**     SOUS MENU GERER LES TACHES      **%n");
        System.out.format(Color.BLUE + "*****************************************%n");
       System.out.println(Color.RESET);
        System.out.format("+---------+------------+-------------+--------------+-----------------+----------------------+-----------------------+%n");
        System.out.format("| CREER: 1| MODIFIER: 2| SUPPRIMER: 3| RECHERCHER: 4| LISTES TOTALE: 5| LISTE DES ASSIGNES: 6| SORTIR DE SOUS MENU: 7|%n");
        System.out.format("+---------+------------+-------------+--------------+-----------------+----------------------+-----------------------+%n");
        System.out.format("---------------------------------------------------------------------------------------------------------------------%n");
        System.out.print("Faite votre Choix : ");

        rep = Membre.verifierLaSaisie(1, 7);
        switch (rep) {
            case 1:
                rep = 1;
                Tache.creerTache();
                break;
            case 2:
                rep = 2;
                Tache.modifierTache();
                break;
            case 3:
                rep = 3;
                Tache.supprimerTache();
                break;
            case 4:
                rep = 4;
                Tache.rechercherTache();
                break;
            case 5:
                rep = 5;
                Tache.listeTache();
                break;
            case 6:
                rep = 6;
                Tache.listeTacheAssigne();
                break;
            case 7:
                rep = 7;
                choixMenuPrincipal();
                break;
        }

    }

    public static void menuGererAssignation() {
        System.out.format(Color.BLUE+"*****************************************%n");
        System.out.format(Color.BLUE +"**  SOUS MENU ASSIGNATION DES TACHES   **%n");
        System.out.format(Color.BLUE + "*****************************************%n");
        System.out.println(Color.RESET);

        System.out.format("+------------+--------------+---------------------+-------------------------------+-----------------------+%n");
        System.out.format("| ASSIGNER: 1| REASSIGNER: 2| CLOTURER LA TACHE: 3| LISTES DES TACHES ASSIGNEES: 4| SORTIR DE SOUS MENU: 5|%n");
        System.out.format("+------------+--------------+---------------------+-------------------------------+-----------------------+%n");
        
        System.out.format("---------------------------------------------------------------------------------------------------------------------%n");
        System.out.print("Faite votre Choix : ");
        rep = Membre.verifierLaSaisie(1, 5);
        System.out.format("---------------------------------------------------------------------------------------------------------------------%n");
        
        switch (rep) {
            case 1:
                rep = 1;
                Tache.assignerTache();
                break;
            case 2:
                rep = 2;
                 Tache.reassignerTache();
                break;
            case 3:
                rep = 3;
                 Tache.cloturerTache();
                break;
            case 4:
                rep = 4;
                Tache.listeTacheAssigne();
                break;
            case 5:
                rep = 5;
                choixMenuPrincipal();
                break;
        }

    }

    public static void sortirProgramme() {
        System.out.format("---------------------------------------------------------------------------------------------------------------------%n");
        System.out.println(Color.MAGENTA+"\nIN DU PROGRAMME \n");
        System.out.format("---------------------------------------------------------------------------------------------------------------------%n");
        System.exit(0);

    }

    private static void choixMenuPrincipal() {
        boolean choixJuste = false;
        Scanner lit = new Scanner(System.in);
        String str;
        int choix = 0;
        do {
            menuGeneral();
            str = lit.nextLine();
            try {
                choix = Integer.parseInt(str);
                if (choix < 5 & choix >= 1) {
                    choixJuste = true;
                } else {
                    System.out.println("Vous devriez faire le choix entre 0 et 3, essayez encore !");
                }
                // si ton exécution arrive jusque là, ça signifie qu'il n'y a aucune exception lancée et que ta saisie est donc bien un chiffre
            } catch (NumberFormatException e) {
                System.out.println("Cette valeur n'est pas un chiffre, essayez encore !");
            }
        } while (choixJuste != true);
        switch (choix) {
            case 1:
                choix = 1;
                menuGererMembre();
                break;
            case 2:
                choix = 2;
                menuGererTache();
                break;
            case 3:
                choix = 3;
                menuGererAssignation();
                break;
            case 4:
                choix = 4;
                sortirProgramme();
                break;

        }

    }

}
