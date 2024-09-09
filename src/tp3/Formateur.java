/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3;

/**
 *
 * @author josep
 */
public class Formateur {

    private int largeur;

    public Formateur(int largeur) {
        this.largeur = largeur;
    }

    public String genererString(String chaine) {
        String resultat = chaine;
        int i = 0;
        while (resultat.length() < largeur) {
            if (i % 2 == 0) {
                resultat += " ";

            } else {
                resultat = " " + resultat;
            }
            i++;
        }
        return resultat;
    }

    public String genererInt(int valeur) {
        return genererString(Integer.toString(valeur));
    }
}
