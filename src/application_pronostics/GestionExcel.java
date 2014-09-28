/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application_pronostics;

import interface_graphique.Accueil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/*
 *
 * @author Salim 
 * 
 */


/*************************************************
* Classe qui s'occupe de la gestion des fichiers
* @return 
*************************************************/
public class GestionExcel {

    public static final int NOMBRE_DE_JOUEUR = 7;
    
    /***********************************************
     * Fonction qui gere la sauvegarde des pronostics
     * @return 
     **********************************************/
    public int saveAs(String[] score, String path) {
        // creation de l'objet File("pronostics.csv");
        String pathSave = path; 
        File f = new File(pathSave);
            
        String[] scores;
        scores = score;

        int i; 
        int j=0;
        try {
            // creation du fichier
            f.createNewFile();
            FileWriter fw = new FileWriter(f);

            try {
                for (i = 0; i < scores.length; i++) {
                    fw.write(scores[i]);
                    fw.write(',');               
                    
                
                }
                return 1; 

            } finally {
                // Quoiqu'il arrive on ferme le fichier
                fw.close();
            }

        } catch (Exception e) {
            System.out.print("Impossible de creer le fichier");
            return -1; 
        }
    }

    
    /******************************************************************
     * Fonction qui gere la lecture des matchs de la prochaine journee 
     * @return 
     ******************************************************************/
    public String[] lireMatchsEtClassement(String FilePath) {

        String Path = FilePath;
        // lecture du fichier texte
        try {
            BufferedReader br = new BufferedReader(new FileReader(Path));
            String chaine = "";
            
            while ((chaine = br.readLine()) != null) {
            
                String[] tabChaine = chaine.split(",");

                for (int i = 0; i < (19+2*NOMBRE_DE_JOUEUR); i++) {
                    System.out.println(tabChaine[i]);

                }
                return tabChaine;
            }
            br.close();

        } catch (Exception e) {
            System.out.println("Le fichier est introuvable !");

        }
        return null;
    }

    
    /**********************************************************
     * Fonction qui gere la lecture des pronostics des joueurs    
     * @return 
     **********************************************************/    
    public String[] lireProno(String FilePath, int NombreDePronostics) {
        
        int nombreDePronostics = NombreDePronostics;
        int tailleFichierPronostic; 
        tailleFichierPronostic = 40 * nombreDePronostics; 
        String Path = FilePath;
        // lecture du fichier texte
        try {

            Scanner scanner = new Scanner(new File(Path));

            String[] tabChaine2 = new String[40];
            String[] pronostic = new String[tailleFichierPronostic];
            int offset = 0;
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                tabChaine2 = line.split(",");

                for (int j = 0; j < 40; j++) {
                    int k = j + offset;
                    pronostic[k] = tabChaine2[j];
                }
                offset = offset + 40;
            }
            for (int i = 0; i < 200; i++) {
                System.out.println(pronostic[i]);
            }
            return pronostic;

            //scanner.close();  
        } catch (Exception e) {
            System.out.println("Le fichier est introuvable !");

        }
        return null;
    }

    
    /**********************************************************
     * Fonction qui gere la lecture des classements des joueurs  
     * @return 
     **********************************************************/    
    public String[] lireClassement(String FilePath) {
        
        String Path = FilePath;
        // lecture du fichier texte
        try {
           
            BufferedReader br = new BufferedReader(new FileReader(Path));
            String chaine = "";
            
            while ((chaine = br.readLine()) != null) {
            
                String[] classement = chaine.split(",");

                for (int i = 0; i < (NOMBRE_DE_JOUEUR*2); i++) {
                    System.out.println(classement[i]);

                }
                return classement;
            }
            br.close();
            
        } catch (Exception e) {
            System.out.println("Le fichier est introuvable !");

        }
        return null;
    }
      
    
    
    
    
    
    /***************************************************
     * Fonction qui gere l'ouverture des fichiers 
     * @return 
     ***************************************************/  
    public static String open() {
        JFileChooser dialogue = new JFileChooser(new File("."));
        PrintWriter sortie = null;
        File fichier = null;

        if (dialogue.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            fichier = dialogue.getSelectedFile();
            try {
                sortie = new PrintWriter(new FileWriter(fichier.getPath(), true));
            } catch (IOException ex) {
                Logger.getLogger(Accueil.class.getName()).log(Level.SEVERE, null, ex);
            }
            sortie.close();
        }
        return fichier.getPath();
    }
    
    
    public int calculPoints(String[] pronosticsJoueur, String[] resultatsJournee) {

        int points = 0;
        int nbrPronoExact = 0;
        String[] resultats = resultatsJournee;
        String[] pronostic = pronosticsJoueur;

        int resultatDom;
        int resultatExt;
        int pronosticDom;
        int pronosticExt;

        for (int i = 1; i <= 37; i = i + 4) {

            resultatDom = Integer.parseInt(resultats[i]);
            pronosticDom = Integer.parseInt(pronostic[i]);
            resultatExt = Integer.parseInt(resultats[i + 1]);
            pronosticExt = Integer.parseInt(pronostic[i + 1]);
            
            /**** BON 1 N 2 ****/
            if ((((resultatDom - resultatExt) > 0) && (pronosticDom - pronosticExt) > 0) || (((resultatDom - resultatExt) == 0) && ((pronosticDom - pronosticExt) == 0)) || (((resultatDom - resultatExt) < 0) && (pronosticDom - pronosticExt) < 0)) {
                points = points + 1;

                /**** ECART DE BUTS ****/                
                if (((resultatDom - resultatExt) == (pronosticDom - pronosticExt)) && (resultatDom != resultatExt)) {
                    points = points + 1;
                }

                /**** SCORE EXACT SANS MATCH NUL ****/
                if ((resultatDom == pronosticDom) && (resultatExt == pronosticExt) && (resultatDom != resultatExt)) {
                    points = points + 1;
                    nbrPronoExact = nbrPronoExact + 1;
                   
                }
                /**** SCORE EXACT EN CAS DE MATCH NUL ****/
                else if ((resultatDom == pronosticDom) && (resultatExt == pronosticExt) && (resultatDom == resultatExt)){
                    points = points + 2;
                    nbrPronoExact = nbrPronoExact + 1;
                }
                else{
                    
                }
            
            /**** MAUVAIS PRONO ****/
            } else {
                points = points + 0;
            }
        }

        return points;

    }
    
    
    
    
    
}
