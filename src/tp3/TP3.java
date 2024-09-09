/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;


/**
 *
 * @author josep
 */
public class TP3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Ligue ligue = new Ligue("classement.txt");
        String resultat1 = ligue.dessiner();
        System.out.println(resultat1);
        ligue = new Ligue("premierleague.txt");
        String resultat2 = ligue.dessiner();
        System.out.println(resultat2);

        FileOutputStream fichier = ouvrirFichierEcriture("Sortie.txt");
        PrintStream sortie = new PrintStream(fichier);
        sortie.println(resultat1);
        sortie.println(resultat2);
        fermerFichier(fichier);

    }

    private static FileOutputStream ouvrirFichierEcriture(String nomFic) {
        FileOutputStream ficSortie = null;
        try {
            ficSortie = new FileOutputStream(nomFic);
        } catch (FileNotFoundException ex) {
            System.out.println("Erreur de création de fichier");
        }
        return ficSortie;
    }

    private static void fermerFichier(Closeable fichier) {
        try {
            fichier.close();
        } catch (IOException ex) {
            System.out.println("Erreur de fermeture de fichier");
        }
    }
}

