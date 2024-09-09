/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author josep
 */
public class Ligue {
    //Voici nos attributs
    private String entete;
    private String[] enteteTableau;
    private Equipe[] equipes;
    private String titre;
    private Formateur formateur;
    private int infoLargeurFormat;

    public Ligue(String nomFichier) {
        // TODO code application logic here
        FileInputStream fichier = ouvrirFichierLecture(nomFichier);
        Scanner lecteurFichier = new Scanner(fichier);
        //Lecture de fichier et assigner les informations aux attributs
        titre = lecteurFichier.nextLine();
        entete = lecteurFichier.nextLine();
        enteteTableau = entete.split(";");
        String infoLargeurFormat = lecteurFichier.nextLine();
        formateur = new Formateur(Integer.parseInt(infoLargeurFormat));
        for (int i = 0; i < enteteTableau.length; i++) {
            enteteTableau[i] = formateur.genererString(enteteTableau[i]);
        }
        String infoPoints = lecteurFichier.nextLine();
        String infoMoyenne = lecteurFichier.nextLine();
        int nbEquipes = Integer.parseInt(lecteurFichier.nextLine());
        equipes = new Equipe[nbEquipes];
        for (int i = 0; i < equipes.length; i++) {
            String infoEquipe = lecteurFichier.nextLine();
            equipes[i] = new Equipe(infoEquipe, infoPoints, infoMoyenne, infoLargeurFormat);
        }
        this.infoLargeurFormat = Integer.parseInt(infoLargeurFormat);
        formateur = new Formateur(this.infoLargeurFormat);
        fermerFichier(fichier);
    }

    private static FileInputStream ouvrirFichierLecture(String nomFichier) {
        FileInputStream fichier = null;
        try {
            fichier = new FileInputStream(nomFichier);
        } catch (FileNotFoundException ex) {
            System.out.println("Erreur d’ouverture de fichier");
        }
        return fichier;
    }

    private static void fermerFichier(Closeable fichier) {
        try {
            fichier.close();
        } catch (IOException ex) {
            System.out.println("Erreur de fermeture de fichier");
        }
    }
    //Voici des méthodes du tri
    private void trierEquipes() {
        Equipe[] copie = new Equipe[equipes.length];

        for (int i = 0; i < equipes.length; i++) {
            copie[i] = equipes[i];
        }
        int j = 0;
        for (int i = 0; i < equipes.length; i++) {
            int pos = trouverPositionDuPlusGrand(copie);
            equipes[j] = copie[pos];
            j++;
            copie[pos] = null;
        }
    }

    private int trouverPositionDuPlusGrand(Equipe[] equipe) {
        int pos = 0;
        while (equipe[pos] == null) {
            pos++;
        }

        int total = equipe[pos].getPoints();
        for (int i = 0; i < equipe.length; i++) {
            if (equipe[i] != null) {
                if (equipe[i].getPoints() > total) {
                    pos = i;
                    total = equipe[i].getPoints();
                }
            }
        }
        return pos;
    }

    String dessiner() {
        String chaineARetourner = "";
        Formateur formateurTitre = new Formateur(infoLargeurFormat*enteteTableau.length);
        String reponse = formateurTitre.genererString(titre);
        Formateur reste = new Formateur(infoLargeurFormat);
        reponse += "\n" + enteteTableau[0];
        for (int i = 1; i < enteteTableau.length; i++) {
            reponse += reste.genererString(enteteTableau[i]);
        }
        for (int i = 0; i < enteteTableau.length; i++) {
            chaineARetourner += enteteTableau[i];
        }
        chaineARetourner += "\n";
        for (int i = 0; i < equipes.length; i++) {
            chaineARetourner += equipes[i].dessiner() + "\n";
            trierEquipes();
        }
        return formateurTitre.genererString(titre) + "\n" + "--------------------------------------------------------------------------------------" + "\n" + chaineARetourner;
    }

}
