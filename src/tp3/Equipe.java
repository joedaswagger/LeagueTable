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
public class Equipe {
    // TODO code application logic here
    /**
     * Voici nos attributs.
     */
    private Formateur formateur;
    private String[] infoEquipe;
    private int[] infoEquipeTabInt;
    private String[] infoPoints;
    private int[] infoPointsTabInt;
    private String[] infoMoyenne;
    private int[] infoMoyenneTabInt;
    private int infoLargeurFormat;
    private int matchs;
    private int diffs;
    private int moyennes;
    private int points;

    public Equipe(String infoEquipe, String infoPoints, String infoMoyenne, String infoLargeurFormat) {
        // TODO code application logic here
        // Lecture de fichier et assigner les informations aux attributs en sortant les ";"
        this.infoEquipe = infoEquipe.split(";");
        this.infoPoints = infoPoints.split(";");
        this.infoMoyenne = infoMoyenne.split(";");

        this.infoLargeurFormat = Integer.parseInt(infoLargeurFormat);
        
        formateur = new Formateur(this.infoLargeurFormat);

        infoEquipeTabInt = new int[this.infoEquipe.length - 1];
        for (int i = 0; i < infoEquipeTabInt.length; i++) {
            infoEquipeTabInt[i] = Integer.parseInt(this.infoEquipe[i + 1]);
        }
        infoPointsTabInt = new int[this.infoPoints.length];
        for (int i = 0; i < infoPointsTabInt.length; i++) {
            infoPointsTabInt[i] = Integer.parseInt(this.infoPoints[i]);
        }
        infoMoyenneTabInt = new int[this.infoMoyenne.length];
        for (int i = 0; i < infoMoyenneTabInt.length; i++) {
            infoMoyenneTabInt[i] = Integer.parseInt(this.infoMoyenne[i]);
        }
        // Calcul des autres attributs
        matchs = infoEquipeTabInt[0] + infoEquipeTabInt[1] + infoEquipeTabInt[2];
        diffs = infoEquipeTabInt[4] - infoEquipeTabInt[3];
        moyennes = (infoEquipeTabInt[0] * infoPointsTabInt[0] + infoEquipeTabInt[1] * infoPointsTabInt[1] + infoEquipeTabInt[2] * infoPointsTabInt[2]) / (infoEquipeTabInt[0] + infoEquipeTabInt[1] + infoEquipeTabInt[2]);
        points = infoEquipeTabInt[0] * infoMoyenneTabInt[0] + infoEquipeTabInt[1] * infoMoyenneTabInt[1] + infoEquipeTabInt[2] * infoMoyenneTabInt[2];
        
    }

    public int getPoints() {
        return points;
    }
    
    
    
    String dessiner() {
        String chaineInfoEquipe = formateur.genererString(infoEquipe[0]) + formateur.genererInt(matchs);
        for (int i = 1; i < infoEquipe.length; i++) {
            chaineInfoEquipe += formateur.genererString(infoEquipe[i]);
        }
        chaineInfoEquipe += formateur.genererInt(diffs) + formateur.genererInt(moyennes) + formateur.genererInt(points);
        return chaineInfoEquipe;
    }



}
