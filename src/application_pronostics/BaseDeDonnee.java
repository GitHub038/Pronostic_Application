/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package application_pronostics;

import java.util.List;
import java.util.Vector;

/**
 *
 * 
 */
public class BaseDeDonnee {
    private List<Joueur> joueurs;      
    
    public BaseDeDonnee(){
    joueurs = new Vector<Joueur>();    
    }
   
    public void ajouterJ(Joueur j){
        joueurs.add(j);
    }
   
   
    public void afficherJ() {
        System.out.println("Infos Joueur :");
        System.out.println("-----------------------------");
            for (int i = 0; i < joueurs.size(); i++) {
            Joueur j = joueurs.get(i);
            j.afficher();
            System.out.println("--------------------------------------");
            }
    }
   
    public Vector<Joueur> getLJoueur(){ return (Vector) joueurs;}   
}



