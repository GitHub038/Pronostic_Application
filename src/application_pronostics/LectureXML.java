/*
 * LectureXML.java
 *
 * Created on 5 janvier 2006, 18:26
 *
 * Lecture d'un document XML et transformation en instances Java
 */
package application_pronostics;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * Lecture d'un document XML et transformation en instances Java.
 *
 * @author promayon
 */
public class LectureXML {

    /// nom du document XML a analyser
    private String nomFichier;
    private String joueur;
    private final static String repBase = "src/donnees/";
  
    
    public LectureXML(String joueur) {
        this.joueur=joueur;     
    }
 
    public BaseDeDonnee getJ() {
       
        BaseDeDonnee baseCourante = null;
        String donneesCourantes = "";
        String nomCourant = "";
        String prenomCourant = "";
        String mailCourant = "";
        String numTCourant = "";
        String mdpCourant = "";
        
        
        try {
            // instanciation du parser
            InputStream in = new FileInputStream(repBase + joueur);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader parser = factory.createXMLStreamReader(in);

            for (int event = parser.next(); event != XMLStreamConstants.END_DOCUMENT; event = parser.next()) {
                
                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        if (parser.getLocalName().equals("dossiers")) {
                            baseCourante = new BaseDeDonnee();
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                       if (parser.getLocalName().equals("joueur")) {
                            Joueur j = new Joueur(nomCourant, prenomCourant, mailCourant,numTCourant, mdpCourant);
                           
                           baseCourante.ajouterJ(j);
                        }
                        if (parser.getLocalName().equals("nom")) {
                            nomCourant = donneesCourantes;
                        }

                        if (parser.getLocalName().equals("prenom")) {
                            prenomCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("mail")) {
                            mailCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("numero")) {
                            numTCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("mdp")) {
                            mdpCourant = donneesCourantes;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        donneesCourantes = parser.getText();
                        break;
                } 
            } 
            parser.close();
        } catch (XMLStreamException ex) {
            System.out.println("Exception de type 'XMLStreamException' lors de la lecture du fichier : " + nomFichier);
            System.out.println("Details :");
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println("Exception de type 'IOException' lors de la lecture du fichier : " + nomFichier);
            System.out.println("Verifier le chemin.");
            System.out.println(ex.getMessage());
        }
        return baseCourante;
    }
}
