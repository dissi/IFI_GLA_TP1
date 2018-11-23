/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestache;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ESSO Dissirama
 */
public class Membre {

    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String mail;
    private static Scanner sc = new Scanner(System.in);
    public static List<Membre> listeMembre = new ArrayList<>();

    public static int rep = 0;

    public int getId() {
        return id;

    }

    public String getNom() {
        return nom;

    }

    public String getPrenom() {
        return prenom;

    }

    public String getAdresse() {
        return adresse;

    }

    public String getTelephone() {
        return telephone;
    }

    public String getMail() {
        return mail;

    }

    public void setNom(String pNom) {
        nom = pNom;
    }

    public void setPrenom(String pPrenom) {
        nom = pPrenom;
    }

    public void setAdresse(String pAdresse) {
        adresse = pAdresse;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setMail(String pMail) {
        mail = pMail;
    }

    public Membre() {
    }

    public Membre(int id, String nom, String prenom, String adresse, String telephone, String mail) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.mail = mail;
    }

    //Rechercher un membre
    public static Membre membreRechercherById(int identifiant,List<Membre> listeMembre) {
        if (listeMembre.isEmpty()) {
            return null;
        } else {
            for (Membre memb : listeMembre) {
                if (identifiant == memb.getId()) {
                    return memb;
                }
            }
        }
        return null;
    }
    //Rechercher un membre par nom

    /**
     *
     * @param nom
     * @return
     */
    public static List membreRechercherByNom(String nom) {
        if(listeMembre.isEmpty()) {
            return null;
        } else {
            List<Membre> resultat = new ArrayList<>();
            for (Membre memb : listeMembre) {
                if(nom.equalsIgnoreCase(memb.getNom())) {
                    resultat.add(memb);
                }
            }
            if(resultat.isEmpty()) {
                return null;
            } else {
                return resultat;
            }
        }
    }

    public static boolean monNumeroEstValide(String monNumero) {
       
        return monNumero.matches("[0-9]*");
    }

    public static boolean monMailEstValide(String monMail) {
        
        return monMail.matches("^[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)+$");
    }
    public static boolean champVide(String valeur) {
        return valeur.isEmpty();
    }
    

    private static int genererId() {
        int id = 0;
        if (listeMembre.isEmpty()) {
            id = 1;
        } else {
            id=2;
            for (Membre memb : listeMembre) {
                //System.out.println("memb"+memb.getId());
                if (id <= memb.getId()) {
                    id = memb.getId()+1;
                }

            }
        }

        return id;
    }

    public static int verifierLaSaisie(int borneInferieure, int borneSuperieure) {
        boolean choixJuste = false;
        String str;
        int choix = 0;
        System.out.print("Veillez choisir un numero !!!\nChoix :");
        do {
            str = sc.nextLine();
            try {
                rep = Integer.parseInt(str);
                if (rep <= borneSuperieure & rep >= borneInferieure) {
                    choixJuste = true;
                } else {
                    System.out.println("Vous devriez faire le choix entre " + borneInferieure + " et " + borneSuperieure + ", réessayez svp!");
                    System.out.print("Choix :");
                }
                // si ton exécution arrive jusque là, ça signifie qu'il n'y a aucune exception lancée et que ta saisie est donc bien un chiffre
            } catch (NumberFormatException e) {
                System.out.println("Cette valeur n'est pas un chiffre,réessayez svp!");
            }
        } while (choixJuste != true);
        return rep;
    }

    public static void creerMembre() {
        System.out.format("-------------------------------------------------------------------------------%n");
        System.out.print("+------------------------------+\n");
        System.out.print("|    CREATION D'UN MEMBRE      |\n");
        System.out.print("+------------------------------+\n");
        int id = genererId();
        System.out.print("IDENTIFIANT DU MEMBRE : "+ id +" \n");
        System.out.print("_______INFO DU MEMBRE_____ : \n");
        System.out.print("NOM    : \n");
        String nom = sc.nextLine();
        while(champVide(nom)){
            System.out.print("Le Nom est obligatoire!\n NOM    : \n");
            nom = sc.nextLine();         
        }
        System.out.print("PRENOM : \n");
        String prenom = sc.nextLine();
        while (champVide(prenom)) {
            System.out.print("Le Prenom est obligatoire!\n NOM    : \n");
            prenom = sc.nextLine();
        }
        System.out.print("Adresse: \n");
        String adresse = sc.nextLine();
        while(champVide(adresse)){
            System.out.print("L'adresse est obligatoire!\n NOM    : \n");
            adresse = sc.nextLine();         
        }
        System.out.print("Téléphone: \n");
        String telephone = sc.nextLine();
        while (!monNumeroEstValide(telephone)) {
            System.out.print("Vous devriez saisir un numero de téléphone valide! Ressayez s'il vous plait: \n");
            telephone = sc.nextLine();
        }
        System.out.print("Mail: \n");
        String mail = sc.nextLine();
        while (!monMailEstValide(mail)) {
            System.out.print("Vous devriez saisir un mail valide! Ressayez s'il vous plait: \n");
            mail = sc.nextLine();
        }
        System.out.format("-------------------------------------------------------------------------------%n");
        System.out.print("Voulez-vous enregistrer ce membre?\t1 : OUI\t2 : NON\n");
        rep = verifierLaSaisie(1, 2);
        System.out.format("-------------------------------------------------------------------------------%n");

        if (rep == 1) {
            Membre nouveauMembre = new Membre(id, nom, prenom, adresse, telephone, mail);
            listeMembre.add(nouveauMembre);
            System.out.print(Color.GREEN+" ~~~~L'enregistrement validé!! ~~~~\n");
            System.out.println(Color.RESET);
            System.out.format("-------------------------------------------------------------------------------%n");
            System.out.print("Voulez-vous ajouter un autre membre?\t1 : OUI\t2 : NON\n");
            rep = verifierLaSaisie(1, 2);
            System.out.format("-------------------------------------------------------------------------------%n");
            if (rep == 1) {
                creerMembre();
            } else {
                GesTache.menuGererMembre();
            }
        } else {
            GesTache.menuGeneral();
        }

    }

    public static void listeMembre() {
        System.out.format("-------------------------------------------------------------------------------%n");
        System.out.print("+------------------------------+\n");
        System.out.print("|       LISTE D'UN MEMBRE      |\n");
        System.out.print("+------------------------------+\n");
       if (listeMembre.isEmpty()) {
            System.out.print("~~~Aucun membre enregistré~~~\n");

        } else {
            String leftAlignFormat = "| %-29s | %-16s | %-12s | %-18s |%n";
            System.out.format("+-------------------------------+------------------+------------+--------------------+%n");
            System.out.format("| Nom et Prenom                 | Adresse          | Téléphone  | Email              |%n");
            System.out.format("+-------------------------------+------------------+------------+--------------------+%n");
            for (Membre memb : listeMembre) {
                System.out.format(leftAlignFormat, memb.getNom() + " " + memb.getPrenom(), memb.getAdresse(), memb.getTelephone(), memb.getMail());
                System.out.format("+-------------------------------+------------------+--------- --+--------------------+%n");
            }

        }
        GesTache.menuGererMembre();
    }

    public static void rechercherMembre() {
        System.out.format("-------------------------------------------------------------------------------%n");
        System.out.print("+-------------------------------------+\n");
        System.out.print("|       RESULTAT DE LA RECHERCHE      |\n");
        System.out.print("+-------------------------------------+\n");
        System.out.print("_______MEMBRE A MODIFIER_____ : \n");
        System.out.print("NOM    : \n");
        String nom = sc.nextLine();
        while (champVide(nom)) {
            System.out.print("Le Nom est obligatoire!\nNOM    : \n");
            nom = sc.nextLine();
        }
        List<Membre> membreTrouve = new ArrayList();
        Membre membreRecherche = new Membre();
        membreTrouve = membreRechercherByNom(nom);
        if (membreTrouve == null) {
            System.out.print("Désolé. Le nom qui vous avez donné n'existe pas dans notre repertoire.!! \n");
            GesTache.menuGererMembre();
        }else {
            String leftAlignFormat = "| %-29s | %-16s | %-12s | %-18s |%n";
            System.out.format("+-------------------------------+------------------+------------+--------------------+%n");
            System.out.format("| Nom et Prenom                 | Adresse          | Téléphone  | Email              |%n");
            System.out.format("+-------------------------------+------------------+------------+--------------------+%n");
            for (Membre memb : membreTrouve) {
                System.out.format(leftAlignFormat, memb.getNom() + " " + memb.getPrenom(), memb.getAdresse(), memb.getTelephone(), memb.getMail());
                System.out.format("+-------------------------------+------------------+--------- --+--------------------+%n");
            }

        }
        GesTache.menuGererMembre();
    }

    public static void modifierMembre() {
        System.out.format("--------------------------------------------------------%n");
        System.out.print("+----------------------------------+\n");
        System.out.print("|     MODIFICATION D'UN MEMBRE     |\n");
        System.out.print("+----------------------------------+\n");
        System.out.print("_______MEMBRE A MODIFIER_____ : \n");
        System.out.print("NOM    : \n");
        String nom = sc.nextLine();
        while(champVide(nom)){
            System.out.print("Le Nom est obligatoire!\nNOM    : \n");
            nom = sc.nextLine();         
        }
        List<Membre> membreTrouve = new ArrayList();
        Membre membreRecherche=new Membre();
        membreTrouve = membreRechercherByNom(nom);
        if(membreTrouve == null) {
            System.out.print("Désolé. Le nom qui vous avez donné n'existe pas dans notre repertoire. Veuillez réessayer!! \n");
            GesTache.menuGererMembre();
        }
        if(membreTrouve.size() == 1){
            membreRecherche=membreTrouve.get(0);
        }else if (membreTrouve.size() > 1) {
            System.out.print("~~~Vous devez choisir le membre parmi cette liste!~~~\n");
            String leftAlignFormat = "| %-25s | %-18d |%n";
            System.out.format("+-------------------------------+------------------+%n");
            System.out.format("| Nom et Prenom                 | Numero           |%n");
            System.out.format("+-------------------------------+------------------+%n");
            for (Membre membTrouve : membreTrouve) {
                System.out.format(leftAlignFormat, membTrouve.getNom() + " " + membTrouve.getPrenom(), membTrouve.getId());
                System.out.format("+-------------------------------+------------------+%n");
            }
            System.out.print("~~~Veuillez choisir le numero du membre à modifier!~~~\n");
            System.out.print("Numéro:\n");
            sc.nextLine();
            int numero = sc.nextInt();
            while(membreRechercherById(numero,membreTrouve)!=null ) {
               System.out.print("Ce numero n'est pas correct! Ressayez s'il vous plait: \n");
               numero = sc.nextInt();
                 
            }
            membreRecherche=membreRechercherById(numero,membreTrouve);
        }
            
        System.out.print("~~~Veuillez  modifier le membre!~~~\n");
        System.out.print(Color.CYAN+"NB: FAITES ENTRER  POUR GARDER LES VALEURS ANCIENNES~~~\n");
        System.out.println(Color.RESET);
        System.out.print("\n_______INFO DU MEMBRE_____ : \n");
        System.out.print("ANCIEN NOM    : "+membreRecherche.getNom()+" \n");
        System.out.print("NOUVEAU NOM : \n");
        String newNom = sc.nextLine();
        if(champVide(newNom)) newNom=membreRecherche.getNom();
        System.out.print("ANCIEN PRENOM : "+membreRecherche.getPrenom()+" \n");
        System.out.print("NOUVEAU PRENOM: \n");
        String newPrenom = sc.nextLine();
        if(champVide(newPrenom)) newPrenom=membreRecherche.getPrenom();
        System.out.print("ANCIENNE ADRESSE: "+membreRecherche.getAdresse()+" \n");
        System.out.print("NOUVELLE ADRESSE: \n");
        String newAdresse = sc.nextLine();
        if(champVide(newAdresse)) newAdresse=membreRecherche.getAdresse();
        System.out.print("ANCIEN TELEPHONE: "+membreRecherche.getAdresse()+" \n");
        System.out.print("NOUVEAU TELEPHONE: \n");
        String newTelephone = sc.nextLine();
        if(champVide(newTelephone)) {
            newTelephone=membreRecherche.getTelephone();
        }else{
            while (!monNumeroEstValide(newTelephone)) {
                System.out.print("Vous devriez saisir un numero de téléphone valide! Ressayez s'il vous plait: \n");
                newTelephone = sc.nextLine();
            }
        }
        System.out.print("ANCIEN MAIL: " + membreRecherche.getMail() + " \n");
        System.out.print("NOUVEAU MAIL: \n");
        String newMail = sc.nextLine();
        if (champVide(newMail)) {
            newMail = membreRecherche.getMail();
        } else {
            while (!monMailEstValide(newMail)) {
                System.out.print("Vous devriez saisir un mail valide! Ressayez s'il vous plait: \n");
                newMail = sc.nextLine();
            }
        }
        System.out.format("--------------------------------------------------------%n");    
        System.out.print("Voulez-vous modifier ce membre?\t1 : OUI\t2 : NON\n");
        rep = verifierLaSaisie(1, 2);
        System.out.format("--------------------------------------------------------%n");
        if (rep == 1) {
            listeMembre.set(listeMembre.indexOf(membreRecherche),new Membre(membreRecherche.getId(),newNom,newPrenom,newAdresse,newTelephone,newMail));
            membreTrouve.clear();
            System.out.print(Color.GREEN+"Felicitations!!! La modification est reussie!!\n");
            System.out.println(Color.RESET);
            System.out.format("--------------------------------------------------------%n");
        } else {
            GesTache.menuGererMembre();
        }
               
        GesTache.menuGererMembre();
    }
    
    
        public static void supprimerMembre() {
            System.out.format("--------------------------------------------------------%n");
            System.out.print("+----------------------------------+\n");
            System.out.print("|      SUPPRESSION D'UN MEMBRE     |\n");
            System.out.print("+----------------------------------+\n");
        System.out.print("\n_______MEMBRE A SUPPRIMER_____ : \n");
        System.out.print("NOM    : \n");
        String nom = sc.nextLine();
        while (champVide(nom)) {
            System.out.print("Le Nom est obligatoire!\nNOM    : \n");
            nom = sc.nextLine();
        }
        List<Membre> membreTrouve = new ArrayList();
        Membre membreRecherche = new Membre();
        membreTrouve = membreRechercherByNom(nom);
        if(membreTrouve == null) {
            System.out.print("Désolé. Le nom qui vous avez donné n'existe pas dans notre repertoire. Veuillez réessayer!! \n");
            GesTache.menuGererMembre();
        }
        if (membreTrouve.size() == 1) {
            membreRecherche = membreTrouve.get(0);
        } else if (membreTrouve.size() > 1) {
            System.out.print("~~~Vous devez choisir le membre parmi cette liste!~~~\n");
            String leftAlignFormat = "| %-25s | %-18d |%n";
            System.out.format("+-------------------------------+------------------+%n");
            System.out.format("| Nom et Prenom                 | Numero           |%n");
            System.out.format("+-------------------------------+------------------+%n");
            for (Membre membTrouve : membreTrouve) {
                System.out.format(leftAlignFormat, membTrouve.getNom() + " " + membTrouve.getPrenom(), membTrouve.getId());
                System.out.format("+---------------------------+------------------+--------- --+--------------------+%n");
            }
            System.out.print("~~~Veuillez choisir le numero du membre à modifier!~~~\n");
            System.out.print("Numéro:\n");
            sc.nextLine();
            int numero = sc.nextInt();
            while (membreRechercherById(numero, membreTrouve) != null) {
                System.out.print("Ce numero n'est pas correct! Ressayez s'il vous plait: \n");
                numero = sc.nextInt();

            }
            membreRecherche = membreRechercherById(numero, membreTrouve);
        }
        System.out.format("--------------------------------------------------------%n");
        System.out.print("Voulez-vous supprimer ce membre?\t1 : OUI\t2 : NON\n");
        rep = verifierLaSaisie(1, 2);
        System.out.format("--------------------------------------------------------%n");
        if (rep == 1) {
            listeMembre.remove(listeMembre.indexOf(membreRecherche));
            membreTrouve.clear();
            System.out.print(Color.GREEN+"Felicitation! La suppression est reussie?\n");
            System.out.println(Color.RESET);
            System.out.format("--------------------------------------------------------%n");
        } else {
            GesTache.menuGererMembre();
        }
        GesTache.menuGererMembre();
    }


}
