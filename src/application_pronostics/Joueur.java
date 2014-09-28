package application_pronostics;

public class Joueur {

    private String nom;
    private String prenom;
    private String mail;
    private String numT;
    private String mdp;
    public int Points_ap; // j'ai rajouté ca Sofiane car je vais coder le calcul des points

    public Joueur(String nom, String prenom, String mail, String numT, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.numT = numT;
        this.mdp = mdp;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMdp() {
        return mdp;
    }

    public String getMail() {
        return mail;
    }

    public String getNumT() {
        return numT;
    }

    public void afficher() {
        System.out.println("nom: " + nom);
        System.out.println("prenom: " + prenom);
        System.out.println("mail: " + mail);
        System.out.println("numéro: " + numT);
        System.out.println("mdp: " + mdp);
    }

     public boolean equals(Object o) {
        if (o instanceof Joueur) {
            Joueur p = (Joueur) o;
            return nom.equals(p.nom) && prenom.equals(p.prenom);
        } else {
            return false;
        }
    }
}

    


