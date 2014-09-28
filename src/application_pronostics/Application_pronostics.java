/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application_pronostics;
//aaaaaaaaaaaaaaaaaaaaaaaaaabbbbbnn

/*
 *
 * @author Salim 
 *  
 */
import interface_graphique.Identification;

public class Application_pronostics {

    /*
     *
     * @param args the command line arguments
     * 
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LectureXML test = new LectureXML("donnees.xml");
        BaseDeDonnee bdJ = test.getJ();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Identification().setVisible(true);
            }
        });

    }
}
